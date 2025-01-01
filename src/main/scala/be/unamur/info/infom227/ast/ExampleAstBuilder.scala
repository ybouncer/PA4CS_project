package be.unamur.info.infom227.ast

import be.unamur.info.infom227.cst.{ExampleGrammarBaseVisitor, ExampleGrammarParser}
import be.unamur.info.infom227.util.TryIterator.*

import scala.jdk.CollectionConverters.*
import scala.util.{Failure, Success, Try}

object ExampleAstBuilder {
  def build(programCst: ExampleGrammarParser.ProgramContext, symbolTable: Option[ExampleSymbolTable] = None): Try[ExampleProgram] = {
    ExampleAstBuilder(symbolTable).visitProgram(programCst)
  }
}

private class ExampleAstBuilder(private var symbolTable: Option[ExampleSymbolTable] = None) extends ExampleGrammarBaseVisitor[Try[ExampleSequence | ExampleStatement | ExampleExpression | ExampleType]] {

  private def withScope[T](function: ExampleSymbolTable => Try[T]): Try[T] = {
    symbolTable match
      case Some(symbolTable) => function(symbolTable)
      case None => Failure(MissingScopeException("No symbol table"))
  }

  private def newScope[T](function: ExampleSymbolTable => Try[T]): Try[T] = {
    val oldSymbolTable = symbolTable
    symbolTable = Some(ExampleSymbolTable(oldSymbolTable))
    val result = withScope(function)
    symbolTable = oldSymbolTable
    result
  }


  // Programs

  override def visitProgram(ctx: ExampleGrammarParser.ProgramContext): Try[ExampleProgram] = {
    for {
      scope <- newScope(_ => visitScope(ctx.scope))
    } yield ExampleProgram(scope)
  }

  override def visitScope(ctx: ExampleGrammarParser.ScopeContext): Try[ExampleScope] = {
    println("DEBUG: Entering a new scope")
    withScope(symbolTable =>
      for {
        declareStatements <- ctx
          .declareStatement
          .asScala
          .iterator
          .map(stmt => visitDeclareStatement(stmt))
          .toTry
        exampleStatements <- visitStatements(ctx.statements)
        _ = println(s"DEBUG: Statements in scope: ${exampleStatements.statements}")
      } yield ExampleScope(symbolTable.variables, declareStatements.toList ++ exampleStatements.statements *)
    )
  }

  override def visitStatements(ctx: ExampleGrammarParser.StatementsContext): Try[ExampleStatements] = {
    for {
      statements <- ctx
        .statement
        .asScala
        .iterator
        .map(stmt => visitStatement(stmt))
        .toTry
    } yield ExampleStatements(statements.toList *)
  }


  // Statements

  override def visitStatement(ctx: ExampleGrammarParser.StatementContext): Try[ExampleNonDeclareStatement] = {
    if (ctx.assignStatement != null) {
      visitAssignStatement(ctx.assignStatement)
    } else if (ctx.printStatement != null) {
      visitPrintStatement(ctx.printStatement)
    } else if (ctx.ifStatement != null) {
      visitIfStatement(ctx.ifStatement)
    } else if (ctx.whileStatement != null) {
      visitWhileStatement(ctx.whileStatement)
    } else {
      Failure(UnsupportedRuleException(s"Unsupported statement : ${ctx.getText}", ctx, null))
    }
  }

  override def visitDeclareStatement(ctx: ExampleGrammarParser.DeclareStatementContext): Try[ExampleDeclareStatement] = {
    val name = ctx.IDENTIFIER.getText
    for {
      exampleType <- if (ctx.LBRACE != null && ctx.RBRACE != null) {
        // Gérer la déclaration d'un tableau
        val size = ctx.NUMBER.getText.toInt
        if (size > 0) {
          Success(ExampleArray(baseType = ExampleInt, size = Some(size)))
        } else {
          Failure(CannotBuildAstException("Array size must be positive", ctx))
        }
      } else {
        visitType(ctx.`type`) // Gérer une variable simple
      }
      _ <- withScope(_.define(name, exampleType))
        .recover(exception => Failure(CannotBuildAstException(s"Variable $name is already defined", ctx, exception)))
    } yield ExampleDeclareStatement(ctx.getStart.getLine, name, exampleType)
  }


  override def visitAssignStatement(ctx: ExampleGrammarParser.AssignStatementContext): Try[ExampleAssignStatement] = {
    val name = ctx.IDENTIFIER.getText
    println(s"DEBUG: Visiting assign statement for variable $name")

    withScope { symbolTable =>
      for {
        exampleType <- symbolTable.get(name, recursive = true)
        _ = println(s"DEBUG: Type of variable $name is $exampleType")
        (expression, scope) <- if (ctx.scope() != null) { // Gestion des scopes locaux
          println(s"DEBUG: Detected scope in assignment for $name")

          visitScope(ctx.scope()) match {
            case Success(localScope) =>
              println(s"DEBUG: Local scope created for $name with variables: ${localScope.variables}")
              for {
                expression <- visitExpression(ctx.expression(0))
              } yield (expression, localScope)
            case Failure(exception) =>
              Failure(CannotBuildAstException(s"Error visiting scope in assignment for $name", ctx, exception))
          }
        } else if (ctx.expression.size > 1) { // Gestion des tableaux (nouvelle branche)
          println("DEBUG: Detected potential array assignment")
          exampleType match {
            case arrayType: ExampleArray =>
              val indexExpression = visitExpression(ctx.expression(0))
              val valueExpression = visitExpression(ctx.expression(1))
              for {
                index <- indexExpression
                value <- valueExpression if value.exampleType == arrayType.baseType
              } yield (ExampleArrayAssign(name, index, value), ExampleScope(Map.empty, Seq.empty: _*))
            case _ =>
              Failure(CannotBuildAstException("Variable is not an array", ctx))
          }
        } else { // Cas d'une assignation scalaire simple
          println("DEBUG: Detected scalar assignment")
          for {
            expression <- visitExpression(ctx.expression(0))
          } yield (expression, ExampleScope(Map.empty, Seq.empty: _*))
        }
      } yield {
        println(s"DEBUG: Final scope for variable $name: ${scope.variables}")
        ExampleAssignStatement(ctx.getStart.getLine, name, scope, expression)
      }
    }
  }






  override def visitPrintStatement(ctx: ExampleGrammarParser.PrintStatementContext): Try[ExamplePrintStatement] = {
    for {
      expression <- visitExpression(ctx.expression())
    } yield {
      println(s"DEBUG: Print statement with expression: $expression")
      ExamplePrintStatement(ctx.getStart.getLine, expression)
    }
  }

  override def visitIfStatement(ctx: ExampleGrammarParser.IfStatementContext): Try[ExampleIfStatement] = {
    for {
      condition <- visitExpression(ctx.expression).flatMap {
        case expression: ExampleBooleanExpression => Success(expression)
        case _ => Failure(CannotBuildAstException(s"If condition ${ctx.expression.getText} is not a boolean", ctx, null))
      }
      ifStatements <- visitStatements(ctx.if_)
      elseStatements <- visitStatements(ctx.else_)
    } yield ExampleIfStatement(ctx.getStart.getLine, condition, ifStatements, elseStatements)
  }

  override def visitWhileStatement(ctx: ExampleGrammarParser.WhileStatementContext): Try[ExampleWhileStatement] = {
    for {
      condition <- visitExpression(ctx.expression).flatMap {
        case expression: ExampleBooleanExpression => Success(expression)
        case _ => Failure(CannotBuildAstException(s"While condition ${ctx.expression.getText} is not a boolean", ctx, null))
      }
      statements <- visitStatements(ctx.statements)
    } yield ExampleWhileStatement(ctx.getStart.getLine, condition, statements)
  }


  // Types

  override def visitType(ctx: ExampleGrammarParser.TypeContext): Try[ExampleType] = {
    if (ctx.INT != null) {
      Success(ExampleInt)
    } else if (ctx.BOOL != null) {
      Success(ExampleBool)
    } else {
      Failure(UnsupportedRuleException(s"Unsupported type : ${ctx.getText}", ctx, null))
    }
  }


  // Expressions

  override def visitExpression(ctx: ExampleGrammarParser.ExpressionContext): Try[ExampleExpression] = {
    visitDisjunction(ctx.disjunction)
  }

  override def visitDisjunction(ctx: ExampleGrammarParser.DisjunctionContext): Try[ExampleExpression] = {
    if (ctx.passthrough != null) {
      visitConjunction(ctx.passthrough)
    } else {
      ctx
        .conjunction
        .asScala
        .iterator
        .map(conjunction =>
          visitConjunction(conjunction).flatMap {
            case expression: ExampleBooleanExpression => Success(expression)
            case _ => Failure(CannotBuildAstException(s"The expression must be a boolean expression : ${conjunction.getText}", conjunction))
          }
        )
        .toTry
        .map(_.reduce((left, right) =>
          ExampleBooleanBinaryOperation(ExampleBooleanBinaryOperator.Or, left, right)
        ))
    }
  }

  override def visitConjunction(ctx: ExampleGrammarParser.ConjunctionContext): Try[ExampleExpression] = {
    if (ctx.passthrough != null) {
      visitInversion(ctx.passthrough)
    } else {
      ctx
        .inversion
        .asScala
        .iterator
        .map(inversion =>
          visitInversion(inversion).flatMap {
            case expression: ExampleBooleanExpression => Success(expression)
            case _ => Failure(CannotBuildAstException(s"The expression must be a boolean expression : ${inversion.getText}", inversion))
          }
        )
        .toTry
        .map(_.reduce((left, right) =>
          ExampleBooleanBinaryOperation(ExampleBooleanBinaryOperator.And, left, right)
        ))
    }
  }

  override def visitInversion(ctx: ExampleGrammarParser.InversionContext): Try[ExampleExpression] = {
    if (ctx.comparison != null) {
      visitComparison(ctx.comparison)
    } else {
      visitInversion(ctx.inversion).flatMap {
        case expression: ExampleBooleanExpression => Success(ExampleBooleanUnaryOperation(ExampleBooleanUnaryOperator.Neg, expression))
        case _ => Failure(CannotBuildAstException(s"Invalid negation in expression : ${ctx.getText}", ctx))
      }
    }
  }

  override def visitComparison(ctx: ExampleGrammarParser.ComparisonContext): Try[ExampleExpression] = {
    if (ctx.passthrough != null) {
      visitSum(ctx.passthrough)
    } else {
      (visitComparison(ctx.left), visitSum(ctx.right)) match
        case (Success(left: ExampleIntegerExpression), Success(right: ExampleIntegerExpression)) =>
          if (ctx.LESS != null) {
            Success(ExampleIntegerComparisonOperation(ExampleIntegerComparisonOperator.Lt, left, right))
          } else if (ctx.GREATER != null) {
            Success(ExampleIntegerComparisonOperation(ExampleIntegerComparisonOperator.Gt, left, right))
          } else if (ctx.EQUAL != null) {
            Success(ExampleIntegerEqualComparisonOperation(ExampleEqualComparisonOperator.Eq, left, right))
          } else if (ctx.DIFFERENT != null) {
            Success(ExampleIntegerEqualComparisonOperation(ExampleEqualComparisonOperator.Ne, left, right))
          } else if (ctx.GREATER_EQUAL != null) {
            Success(ExampleIntegerComparisonOperation(ExampleIntegerComparisonOperator.Gte, left, right))
          } else if (ctx.LESS_EQUAL != null) {
            Success(ExampleIntegerComparisonOperation(ExampleIntegerComparisonOperator.Lte, left, right))
          } else {
            Failure(CannotBuildAstException(s"Invalid operator in integer comparison : ${ctx.getText}", ctx))
          }
        case (Success(left: ExampleBooleanExpression), Success(right: ExampleBooleanExpression)) =>
          if (ctx.EQUAL != null) {
            Success(ExampleBooleanEqualComparisonOperation(ExampleEqualComparisonOperator.Eq, left, right))
          } else if (ctx.DIFFERENT != null) {
            Success(ExampleBooleanEqualComparisonOperation(ExampleEqualComparisonOperator.Ne, left, right))
          } else {
            Failure(CannotBuildAstException(s"Invalid operator in boolean comparison : ${ctx.getText}", ctx))
          }
        case (_, Failure(exception)) => Failure(exception)
        case (Failure(exception), _) => Failure(exception)
        case (_, _) => Failure(CannotBuildAstException(s"Invalid comparison : ${ctx.getText}", ctx))
    }
  }

  override def visitSum(ctx: ExampleGrammarParser.SumContext): Try[ExampleExpression] = {
    if (ctx.passthrough != null) {
      visitProduct(ctx.passthrough)
    } else {
      for {
        operator <- if (ctx.ADD != null) {
          Success(ExampleIntegerBinaryOperator.Add)
        } else if (ctx.SUBTRACT != null) {
          Success(ExampleIntegerBinaryOperator.Sub)
        } else {
          Failure(UnsupportedRuleException(s"Unsupported operator in sum : ${ctx.getText}", ctx, null))
        }
        left <- visitSum(ctx.left).flatMap {
          case expression: ExampleIntegerExpression => Success(expression)
          case _ => Failure(CannotBuildAstException(s"Left side of expression must be an integer expression : ${ctx.getText}", ctx))
        }
        right <- visitProduct(ctx.right).flatMap {
          case expression: ExampleIntegerExpression => Success(expression)
          case _ => Failure(CannotBuildAstException(s"Right side of expression must be an integer expression : ${ctx.getText}", ctx))
        }
      } yield ExampleIntegerBinaryOperation(operator, left, right)
    }
  }

  override def visitProduct(ctx: ExampleGrammarParser.ProductContext): Try[ExampleExpression] = {
    if (ctx.passthrough != null) {
      visitFactor(ctx.passthrough)
    } else {
      for {
        operator <- if (ctx.MULTIPLY != null) {
          Success(ExampleIntegerBinaryOperator.Mul)
        } else if (ctx.DIVIDE != null) {
          Success(ExampleIntegerBinaryOperator.Div)
        } else {
          Failure(UnsupportedRuleException(s"Unsupported operator in product : ${ctx.getText}", ctx, null))
        }
        left <- visitProduct(ctx.left).flatMap {
          case expression: ExampleIntegerExpression => Success(expression)
          case _ => Failure(CannotBuildAstException(s"Left side of expression must be an integer expression : ${ctx.getText}", ctx))
        }
        right <- visitFactor(ctx.right).flatMap {
          case expression: ExampleIntegerExpression => Success(expression)
          case _ => Failure(CannotBuildAstException(s"Right side of expression must be an integer expression : ${ctx.getText}", ctx))
        }
      } yield ExampleIntegerBinaryOperation(operator, left, right)
    }
  }

  override def visitFactor(ctx: ExampleGrammarParser.FactorContext): Try[ExampleExpression] = {
    visitAtom(ctx.atom).flatMap {
      case expression: ExampleIntegerExpression =>
        if (ctx.ADD != null) {
          Success(ExampleIntegerUnaryOperation(ExampleIntegerUnaryOperator.Pos, expression))
        } else if (ctx.SUBTRACT != null) {
          Success(ExampleIntegerUnaryOperation(ExampleIntegerUnaryOperator.Neg, expression))
        } else {
          Success(expression)
        }
      case expression: ExampleBooleanExpression =>
        if (ctx.ADD != null) {
          Failure(CannotBuildAstException(s"Cannot use '+' with the boolean expression : ${ctx.atom.getText}", ctx))
        } else if (ctx.SUBTRACT != null) {
          Failure(CannotBuildAstException(s"Cannot use '-' with the boolean expression : ${ctx.atom.getText}", ctx))
        } else {
          Success(expression)
        }
    }
  }

  override def visitAtom(ctx: ExampleGrammarParser.AtomContext): Try[ExampleExpression] = {
    if (ctx.IDENTIFIER != null) {
      val name = ctx.IDENTIFIER.getText
      if (ctx.LBRACE != null) {
        // Gérer un accès au tableau, ex: array[0]
        val indexExpression = visitExpression(ctx.expression)
        withScope(symbolTable =>
          symbolTable.get(name) match {
            case Success(arrayType: ExampleArray) =>
              indexExpression match {
                case Success(index: ExampleIntegerExpression) =>
                  Success(ExampleArrayAccess(name, index))
                case _ =>
                  Failure(CannotBuildAstException("Index must be an integer", ctx))
              }
            case _ =>
              Failure(CannotBuildAstException("Variable is not an array", ctx))
          }
        )
      } else {
        // Gérer une variable simple, ex: a
        withScope(symbolTable =>
          symbolTable.get(name) match {
            case Success(ExampleInt) => Success(ExampleIntegerVariable(name))
            case Success(ExampleBool) => Success(ExampleBooleanVariable(name))
            case Failure(exception) =>
              Failure(CannotBuildAstException(s"Undefined variable: $name", ctx, exception))
          }
        )
      }
    } else if (ctx.NUMBER != null) {
      // Gérer un entier, ex: 42
      val number = ctx.NUMBER.getText.toInt
      Success(ExampleIntegerConstant(number))
    } else if (ctx.TRUE != null) {
      // Gérer une constante booléenne, ex: true
      Success(ExampleBooleanConstant(true))
    } else if (ctx.FALSE != null) {
      // Gérer une constante booléenne, ex: false
      Success(ExampleBooleanConstant(false))
    } else if (ctx.expression != null) {
      // Gérer une sous-expression entre parenthèses, ex: (a + b)
      visitExpression(ctx.expression)
    } else {
      Failure(CannotBuildAstException(s"Unsupported atom: ${ctx.getText}", ctx))
    }
  }
}

