package be.unamur.info.infom227.interpreter

import be.unamur.info.infom227.ast.*
import scala.util.{Failure, Success, Try}

object Interpreter {
  def run(programAst: Program, environment: Environment = new Environment()): Try[Unit] = {
    val interpreter = new Interpreter
    interpreter.visitProgram(programAst, environment)
  }
}

class Interpreter extends
  SequenceVisitor[Try[Unit], Environment],
  StatementVisitor[Try[Unit], Environment],
  ExpressionVisitor[Try[Any], Environment] {

  // Sequence Visitor
  override def visitProgram(node: Program, environment: Environment): Try[Unit] = {
    visitScope(node.scope, environment)
  }

  override def visitScope(node: Scope, environment: Environment): Try[Unit] = {
    node.statements.foldLeft(Try(())) { (_, statement) =>
      statement.accept(this, environment)
    }
  }

  override def visitStatements(node: Statements, environment: Environment): Try[Unit] = {
    node.statements.foldLeft(Try(())) { (_, statement) =>
      statement.accept(this, environment)
    }
  }

  // Statement Visitor
  override def visitDeclareStatement(node: DeclareStatement, environment: Environment): Try[Unit] = {
    node.Type match {
      case Int => environment.define(node.name, 0)
      case Bool => environment.define(node.name, false)
      case Array(Int, Some(size: Int)) => environment.define(node.name, Array.fill(size)(0))
      case _ => Failure(new IllegalArgumentException(s"Unsupported type: ${node.Type}"))
    }
  }

  override def visitAssignStatement(node: AssignStatement, environment: Environment): Try[Unit] = {
    node.expression.accept(this, environment).flatMap {
      case value: Int => environment.set(node.variable, value)
      case value: Boolean => environment.set(node.variable, value)
      case _ => Failure(new IllegalArgumentException("Invalid assignment value"))
    }
  }

  override def visitPrintStatement(node: PrintStatement, environment: Environment): Try[Unit] = {
    node.expression.accept(this, environment).map(value => println(value))
  }

  override def visitIfStatement(node: IfStatement, environment: Environment): Try[Unit] = {
    node.condition.accept(this, environment).flatMap {
      case condition: Boolean =>
        if (condition) visitStatements(node.ifStatements, environment)
        else visitStatements(node.elseStatements, environment)
      case _ => Failure(new IllegalArgumentException("If condition must be a boolean"))
    }
  }

  override def visitWhileStatement(node: WhileStatement, environment: Environment): Try[Unit] = {
    def loop(): Try[Unit] = {
      node.condition.accept(this, environment).flatMap {
        case condition: Boolean =>
          if (condition) {
            visitStatements(node.statements, environment).flatMap(_ => loop())
          } else Success(())
        case _ => Failure(new IllegalArgumentException("While condition must be a boolean"))
      }
    }
    loop()
  }

  // Expression Visitor
  override def visitIntegerConstant(node: IntegerConstant, environment: Environment): Try[Int] = {
    Success(node.value)
  }

  override def visitIntegerVariable(node: IntegerVariable, environment: Environment): Try[Int] = {
    environment.get(node.name).map(_.asInstanceOf[Int])
  }

  override def visitIntegerUnaryOperation(node: IntegerUnaryOperation, environment: Environment): Try[Int] = {
    node.value.accept(this, environment).map {
      case intValue: Int =>
        node.operator match {
          case IntegerUnaryOperator.Pos => intValue
          case IntegerUnaryOperator.Neg => -intValue
        }
    }
  }

  override def visitIntegerBinaryOperation(node: IntegerBinaryOperation, environment: Environment): Try[Int] = {
    for {
      left <- node.left.accept(this, environment).map(_.asInstanceOf[Int])
      right <- node.right.accept(this, environment).map(_.asInstanceOf[Int])
    } yield node.operator match {
      case IntegerBinaryOperator.Add => left + right
      case IntegerBinaryOperator.Sub => left - right
      case IntegerBinaryOperator.Mul => left * right
      case IntegerBinaryOperator.Div => left / right
    }
  }

  override def visitIntegerComparisonOperation(node: IntegerComparisonOperation, environment: Environment): Try[Boolean] = {
    for {
      left <- node.left.accept(this, environment).map(_.asInstanceOf[Int])
      right <- node.right.accept(this, environment).map(_.asInstanceOf[Int])
    } yield node.operator match {
      case IntegerComparisonOperator.Lt => left < right
      case IntegerComparisonOperator.Gt => left > right
      case IntegerComparisonOperator.Lte => left <= right
      case IntegerComparisonOperator.Gte => left >= right
    }
  }

  override def visitIntegerEqualComparisonOperation(node: IntegerEqualComparisonOperation, environment: Environment): Try[Boolean] = {
    for {
      left <- node.left.accept(this, environment).map(_.asInstanceOf[Int])
      right <- node.right.accept(this, environment).map(_.asInstanceOf[Int])
    } yield node.operator match {
      case EqualComparisonOperator.Eq => left == right
      case EqualComparisonOperator.Ne => left != right
    }
  }

  override def visitBooleanConstant(node: BooleanConstant, environment: Environment): Try[Boolean] = {
    Success(node.value)
  }

  override def visitBooleanVariable(node: BooleanVariable, environment: Environment): Try[Boolean] = {
    environment.get(node.name).map(_.asInstanceOf[Boolean])
  }

  override def visitBooleanUnaryOperation(node: BooleanUnaryOperation, environment: Environment): Try[Boolean] = {
    node.value.accept(this, environment).map {
      case boolValue: Boolean => node.operator match {
        case BooleanUnaryOperator.Neg => !boolValue
      }
    }
  }

  override def visitBooleanBinaryOperation(node: BooleanBinaryOperation, environment: Environment): Try[Boolean] = {
    for {
      left <- node.left.accept(this, environment).map(_.asInstanceOf[Boolean])
      right <- node.right.accept(this, environment).map(_.asInstanceOf[Boolean])
    } yield node.operator match {
      case BooleanBinaryOperator.And => left && right
      case BooleanBinaryOperator.Or => left || right
    }
  }

  override def visitBooleanEqualComparisonOperation(node: BooleanEqualComparisonOperation, environment: Environment): Try[Boolean] = {
    for {
      left <- node.left.accept(this, environment).map(_.asInstanceOf[Boolean])
      right <- node.right.accept(this, environment).map(_.asInstanceOf[Boolean])
    } yield node.operator match {
      case EqualComparisonOperator.Eq => left == right
      case EqualComparisonOperator.Ne => left != right
    }
  }

  override def visitArrayAccess(node: ArrayAccess, environment: Environment): Try[Int] = {
    for {
      index <- node.index.accept(this, environment).map(_.asInstanceOf[Int])
      value <- environment.getArrayElement(node.arrayName, index)
    } yield value
  }

  override def visitArrayAssign(node: ArrayAssign, environment: Environment): Try[Unit] = {
    for {
      index <- node.index.accept(this, environment).map(_.asInstanceOf[Int])
      value <- node.value.accept(this, environment).map(_.asInstanceOf[Int])
      _ <- environment.setArrayElement(node.arrayName, index, value)
    } yield ()
  }
}
