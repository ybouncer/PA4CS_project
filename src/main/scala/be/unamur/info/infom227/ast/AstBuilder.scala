package be.unamur.info.infom227.ast

import be.unamur.info.infom227.cst.{GrammarBaseVisitor, GrammarParser}
import be.unamur.info.infom227.util.TryIterator.*

import scala.jdk.CollectionConverters.*
import scala.util.{Failure, Success, Try}

object AstBuilder {
  def build(programCst: GrammarParser.ProgramContext, symbolTable: Option[SymbolTable] = None): Try[Program] = {
    AstBuilder(symbolTable).visitProgram(programCst)
  }
}

private class AstBuilder(private var symbolTable: Option[SymbolTable] = None) extends GrammarBaseVisitor[Try[Sequence | Statement | Expression | Type]] {

  private def withScope[T](function: SymbolTable => Try[T]): Try[T] = {
    symbolTable match
      case Some(symbolTable) => function(symbolTable)
      case None => Failure(MissingScopeException("No symbol table"))
  }

  private def newScope[T](function: SymbolTable => Try[T]): Try[T] = {
    val oldSymbolTable = symbolTable
    symbolTable = Some(SymbolTable(oldSymbolTable))
    val result = withScope(function)
    symbolTable = oldSymbolTable
    result
  }


  // Programs

  override def visitProgram(ctx: GrammarParser.ProgramContext): Try[Program] = {
    for {
      scope <- newScope(_ => visitScope(ctx.scope))
    } yield Program(scope)
  }

  override def visitScope(ctx: GrammarParser.ScopeContext): Try[Scope] = {
    withScope(symbolTable =>
      for {
        declareStatements <- ctx
          .declareStatement
          .asScala
          .iterator
          .map(stmt => visitDeclareStatement(stmt))
          .toTry
        Statements <- visitStatements(ctx.statements)
      } yield Scope(symbolTable.variables, declareStatements.toList ++ Statements.statements *)
    )
  }

  override def visitStatements(ctx: GrammarParser.StatementsContext): Try[Statements] = {
    for {
      statements <- ctx
        .statement
        .asScala
        .iterator
        .map(stmt => visitStatement(stmt))
        .toTry
    } yield Statements(statements.toList *)
  }


  // Statements

  override def visitStatement(ctx: GrammarParser.StatementContext): Try[NonDeclareStatement] = {
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

  override def visitDeclareStatement(ctx: GrammarParser.DeclareStatementContext): Try[DeclareStatement] = {
    val name = ctx.IDENTIFIER.getText
    for {
      Type <- if (ctx.LBRACE != null && ctx.RBRACE != null) {
        // Gérer la déclaration d'un tableau
        val size = ctx.NUMBER.getText.toInt
        if (size > 0) {
          Success(newArray(baseType = Int, size = Some(size)))
        } else {
          Failure(CannotBuildAstException("Array size must be positive", ctx))
        }
      } else {
        visitType(ctx.`type`) // Gérer une variable simple
      }
      _ <- withScope(_.define(name, Type))
        .recover(exception => Failure(CannotBuildAstException(s"Variable $name is already defined", ctx, exception)))
    } yield DeclareStatement(ctx.getStart.getLine, name, Type)
  }


  override def visitAssignStatement(ctx: GrammarParser.AssignStatementContext): Try[AssignStatement] = {
    val name = ctx.IDENTIFIER.getText

    withScope { symbolTable =>
      for {
        Type <- symbolTable.get(name, recursive = true)
        (expression, scope) <- if (ctx.scope() != null) { // Gestion des scopes locaux

          visitScope(ctx.scope()) match {
            case Success(localScope) =>
              for {
                expression <- visitExpression(ctx.expression(0))
              } yield (expression, localScope)
            case Failure(exception) =>
              Failure(CannotBuildAstException(s"Error visiting scope in assignment for $name", ctx, exception))
          }
        } else if (ctx.expression.size > 1) { // Gestion des tableaux (nouvelle branche)
          Type match {
            case arrayType: newArray =>
              val indexExpression = visitExpression(ctx.expression(0))
              val valueExpression = visitExpression(ctx.expression(1))
              for {
                index <- indexExpression
                value <- valueExpression if value.Type == arrayType.baseType
              } yield (ArrayAssign(name, index, value), Scope(Map.empty, Seq.empty: _*))
            case _ =>
              Failure(CannotBuildAstException("Variable is not an array", ctx))
          }
        } else { // Cas d'une assignation scalaire simple
          for {
            expression <- visitExpression(ctx.expression(0))
          } yield (expression, Scope(Map.empty, Seq.empty: _*))
        }
      } yield {
        AssignStatement(ctx.getStart.getLine, name, scope, expression)
      }
    }
  }

  override def visitPrintStatement(ctx: GrammarParser.PrintStatementContext): Try[PrintStatement] = {
    for {
      expression <- visitExpression(ctx.expression())
    } yield {
      PrintStatement(ctx.getStart.getLine, expression)
    }
  }

  override def visitIfStatement(ctx: GrammarParser.IfStatementContext): Try[IfStatement] = {
    for {
      condition <- visitExpression(ctx.expression).flatMap {
        case expression: BooleanExpression => Success(expression)
        case _ => Failure(CannotBuildAstException(s"If condition ${ctx.expression.getText} is not a boolean", ctx, null))
      }
      ifStatements <- visitStatements(ctx.if_)
      elseStatements <- visitStatements(ctx.else_)
    } yield IfStatement(ctx.getStart.getLine, condition, ifStatements, elseStatements)
  }

  override def visitWhileStatement(ctx: GrammarParser.WhileStatementContext): Try[WhileStatement] = {
    for {
      condition <- visitExpression(ctx.expression).flatMap {
        case expression: BooleanExpression => Success(expression)
        case _ => Failure(CannotBuildAstException(s"While condition ${ctx.expression.getText} is not a boolean", ctx, null))
      }
      statements <- visitStatements(ctx.statements)
    } yield WhileStatement(ctx.getStart.getLine, condition, statements)
  }


  // Types

  override def visitType(ctx: GrammarParser.TypeContext): Try[Type] = {
    if (ctx.INT != null) {
      Success(Int)
    } else if (ctx.BOOL != null) {
      Success(Bool)
    } else {
      Failure(UnsupportedRuleException(s"Unsupported type : ${ctx.getText}", ctx, null))
    }
  }


  // Expressions

  override def visitExpression(ctx: GrammarParser.ExpressionContext): Try[Expression] = {
    visitDisjunction(ctx.disjunction)
  }

  override def visitDisjunction(ctx: GrammarParser.DisjunctionContext): Try[Expression] = {
    if (ctx.passthrough != null) {
      visitConjunction(ctx.passthrough)
    } else {
      ctx
        .conjunction
        .asScala
        .iterator
        .map(conjunction =>
          visitConjunction(conjunction).flatMap {
            case expression: BooleanExpression => Success(expression)
            case _ => Failure(CannotBuildAstException(s"The expression must be a boolean expression : ${conjunction.getText}", conjunction))
          }
        )
        .toTry
        .map(_.reduce((left, right) =>
          BooleanBinaryOperation(BooleanBinaryOperator.Or, left, right)
        ))
    }
  }

  override def visitConjunction(ctx: GrammarParser.ConjunctionContext): Try[Expression] = {
    if (ctx.passthrough != null) {
      visitInversion(ctx.passthrough)
    } else {
      ctx
        .inversion
        .asScala
        .iterator
        .map(inversion =>
          visitInversion(inversion).flatMap {
            case expression: BooleanExpression => Success(expression)
            case _ => Failure(CannotBuildAstException(s"The expression must be a boolean expression : ${inversion.getText}", inversion))
          }
        )
        .toTry
        .map(_.reduce((left, right) =>
          BooleanBinaryOperation(BooleanBinaryOperator.And, left, right)
        ))
    }
  }

  override def visitInversion(ctx: GrammarParser.InversionContext): Try[Expression] = {
    if (ctx.comparison != null) {
      visitComparison(ctx.comparison)
    } else {
      visitInversion(ctx.inversion).flatMap {
        case expression: BooleanExpression => Success(BooleanUnaryOperation(BooleanUnaryOperator.Neg, expression))
        case _ => Failure(CannotBuildAstException(s"Invalid negation in expression : ${ctx.getText}", ctx))
      }
    }
  }

  override def visitComparison(ctx: GrammarParser.ComparisonContext): Try[Expression] = {
    if (ctx.passthrough != null) {
      visitSum(ctx.passthrough)
    } else {
      (visitComparison(ctx.left), visitSum(ctx.right)) match
        case (Success(left: IntegerExpression), Success(right: IntegerExpression)) =>
          if (ctx.LESS != null) {
            Success(IntegerComparisonOperation(IntegerComparisonOperator.Lt, left, right))
          } else if (ctx.GREATER != null) {
            Success(IntegerComparisonOperation(IntegerComparisonOperator.Gt, left, right))
          } else if (ctx.EQUAL != null) {
            Success(IntegerEqualComparisonOperation(EqualComparisonOperator.Eq, left, right))
          } else if (ctx.DIFFERENT != null) {
            Success(IntegerEqualComparisonOperation(EqualComparisonOperator.Ne, left, right))
          } else if (ctx.GREATER_EQUAL != null) {
            Success(IntegerComparisonOperation(IntegerComparisonOperator.Gte, left, right))
          } else if (ctx.LESS_EQUAL != null) {
            Success(IntegerComparisonOperation(IntegerComparisonOperator.Lte, left, right))
          } else {
            Failure(CannotBuildAstException(s"Invalid operator in integer comparison : ${ctx.getText}", ctx))
          }
        case (Success(left: BooleanExpression), Success(right: BooleanExpression)) =>
          if (ctx.EQUAL != null) {
            Success(BooleanEqualComparisonOperation(EqualComparisonOperator.Eq, left, right))
          } else if (ctx.DIFFERENT != null) {
            Success(BooleanEqualComparisonOperation(EqualComparisonOperator.Ne, left, right))
          } else {
            Failure(CannotBuildAstException(s"Invalid operator in boolean comparison : ${ctx.getText}", ctx))
          }
        case (_, Failure(exception)) => Failure(exception)
        case (Failure(exception), _) => Failure(exception)
        case (_, _) => Failure(CannotBuildAstException(s"Invalid comparison : ${ctx.getText}", ctx))
    }
  }

  override def visitSum(ctx: GrammarParser.SumContext): Try[Expression] = {
    if (ctx.passthrough != null) {
      visitProduct(ctx.passthrough)
    } else {
      for {
        operator <- if (ctx.ADD != null) {
          Success(IntegerBinaryOperator.Add)
        } else if (ctx.SUBTRACT != null) {
          Success(IntegerBinaryOperator.Sub)
        } else {
          Failure(UnsupportedRuleException(s"Unsupported operator in sum : ${ctx.getText}", ctx, null))
        }
        left <- visitSum(ctx.left).flatMap {
          case expression: IntegerExpression => Success(expression)
          case _ => Failure(CannotBuildAstException(s"Left side of expression must be an integer expression : ${ctx.getText}", ctx))
        }
        right <- visitProduct(ctx.right).flatMap {
          case expression: IntegerExpression => Success(expression)
          case _ => Failure(CannotBuildAstException(s"Right side of expression must be an integer expression : ${ctx.getText}", ctx))
        }
      } yield IntegerBinaryOperation(operator, left, right)
    }
  }

  override def visitProduct(ctx: GrammarParser.ProductContext): Try[Expression] = {
    if (ctx.passthrough != null) {
      visitFactor(ctx.passthrough)
    } else {
      for {
        operator <- if (ctx.MULTIPLY != null) {
          Success(IntegerBinaryOperator.Mul)
        } else if (ctx.DIVIDE != null) {
          Success(IntegerBinaryOperator.Div)
        } else {
          Failure(UnsupportedRuleException(s"Unsupported operator in product : ${ctx.getText}", ctx, null))
        }
        left <- visitProduct(ctx.left).flatMap {
          case expression: IntegerExpression => Success(expression)
          case _ => Failure(CannotBuildAstException(s"Left side of expression must be an integer expression : ${ctx.getText}", ctx))
        }
        right <- visitFactor(ctx.right).flatMap {
          case expression: IntegerExpression => Success(expression)
          case _ => Failure(CannotBuildAstException(s"Right side of expression must be an integer expression : ${ctx.getText}", ctx))
        }
      } yield IntegerBinaryOperation(operator, left, right)
    }
  }

  override def visitFactor(ctx: GrammarParser.FactorContext): Try[Expression] = {
    visitAtom(ctx.atom).flatMap {
      case expression: IntegerExpression =>
        if (ctx.ADD != null) {
          Success(IntegerUnaryOperation(IntegerUnaryOperator.Pos, expression))
        } else if (ctx.SUBTRACT != null) {
          Success(IntegerUnaryOperation(IntegerUnaryOperator.Neg, expression))
        } else {
          Success(expression)
        }
      case expression: BooleanExpression =>
        if (ctx.ADD != null) {
          Failure(CannotBuildAstException(s"Cannot use '+' with the boolean expression : ${ctx.atom.getText}", ctx))
        } else if (ctx.SUBTRACT != null) {
          Failure(CannotBuildAstException(s"Cannot use '-' with the boolean expression : ${ctx.atom.getText}", ctx))
        } else {
          Success(expression)
        }
    }
  }

  override def visitAtom(ctx: GrammarParser.AtomContext): Try[Expression] = {
    if (ctx.IDENTIFIER != null) {
      val name = ctx.IDENTIFIER.getText
      if (ctx.LBRACE != null) {
        // Gérer un accès au tableau, ex: array[0]
        val indexExpression = visitExpression(ctx.expression)
        withScope(symbolTable =>
          symbolTable.get(name) match {
            case Success(arrayType: newArray) =>
              indexExpression match {
                case Success(index: IntegerExpression) =>
                  Success(ArrayAccess(name, index))
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
            case Success(Int) => Success(IntegerVariable(name))
            case Success(Bool) => Success(BooleanVariable(name))
            case Failure(exception) =>
              Failure(CannotBuildAstException(s"Undefined variable: $name", ctx, exception))
          }
        )
      }
    } else if (ctx.NUMBER != null) {
      // Gérer un entier, ex: 42
      val number = ctx.NUMBER.getText.toInt
      Success(IntegerConstant(number))
    } else if (ctx.TRUE != null) {
      // Gérer une constante booléenne, ex: true
      Success(BooleanConstant(true))
    } else if (ctx.FALSE != null) {
      // Gérer une constante booléenne, ex: false
      Success(BooleanConstant(false))
    } else if (ctx.expression != null) {
      // Gérer une sous-expression entre parenthèses, ex: (a + b)
      visitExpression(ctx.expression)
    } else {
      Failure(CannotBuildAstException(s"Unsupported atom: ${ctx.getText}", ctx))
    }
  }
}

