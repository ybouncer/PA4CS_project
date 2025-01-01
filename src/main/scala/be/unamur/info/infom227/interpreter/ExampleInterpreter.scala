// Updated ExampleInterpreter.scala
package be.unamur.info.infom227.interpreter

import be.unamur.info.infom227.ast.*
import be.unamur.info.infom227.util.TryIterator.*

import scala.util.{Success, Failure, Try}

object ExampleInterpreter {
  def run(programAst: ExampleProgram, environment: ExampleEnvironment = ExampleEnvironment()): Try[Unit] = {
    ExampleInterpreter().visitExampleProgram(programAst, environment)
  }
}


private class ExampleInterpreter extends
  ExampleSequenceVisitor[Try[Unit], ExampleEnvironment],
  ExampleStatementVisitor[Try[Unit], ExampleEnvironment],
  ExampleExpressionVisitor[Try[Int], ExampleEnvironment] {

  override def visitExampleProgram(node: ExampleProgram, environment: ExampleEnvironment): Try[Unit] = {
    visitExampleScope(node.scope, environment)
  }

  override def visitExampleScope(node: ExampleScope, environment: ExampleEnvironment): Try[Unit] = {
    node.statements.iterator.map(_.accept(this, environment)).toTry.map(_ => ())
  }

  override def visitExampleDeclareStatement(node: ExampleDeclareStatement, environment: ExampleEnvironment): Try[Unit] = {
    val result = node.exampleType match {
      case ExampleInt => Success(0) // Initialise une variable entière à 0
      case ExampleArray(ExampleInt, Some(size)) => Success(Array.fill(size)(0)) // Initialise un tableau d'entiers
      case _ => Failure(new IllegalArgumentException("Unsupported type"))
    }

    result.flatMap(value => environment.define(node.name, value))
  }


  override def visitExampleAssignStatement(node: ExampleAssignStatement, environment: ExampleEnvironment): Try[Unit] = {
    val scopeEnvironment = if (node.scope.statements.isEmpty) environment else ExampleEnvironment(environment)

    for {
      _ <- visitExampleScope(node.scope, scopeEnvironment)
      case value: (Int | Boolean | Array[Int] | Array[Boolean]) <- node.expression.accept(this, scopeEnvironment)
      _ <- environment.set(node.variable, value)
    } yield ()
  }

  override def visitExamplePrintStatement(node: ExamplePrintStatement, environment: ExampleEnvironment): Try[Unit] = {
    for {
      case value: (Int | Boolean) <- node.expression.accept(this, environment)
      _ <- environment.print(
        value match {
          case intValue: Int => intValue.toString
          case boolValue: Boolean => if (boolValue) "True" else "False"
        }
      )
    } yield ()
  }

  override def visitExampleIfStatement(node: ExampleIfStatement, environment: ExampleEnvironment): Try[Unit] = {
    for {
      case condition: Boolean <- node.condition.accept(this, environment)
      _ <- if (condition) {
        node.ifStatements.accept(this, environment)
      } else {
        node.elseStatements.accept(this, environment)
      }
    } yield ()
  }

  override def visitExampleWhileStatement(node: ExampleWhileStatement, environment: ExampleEnvironment): Try[Unit] = {
    for {
      case condition: Boolean <- node.condition.accept(this, environment)
      _ <- if (condition) {
        for {
          _ <- node.statements.accept(this, environment)
          _ <- visitExampleWhileStatement(node, environment)
        } yield ()
      } else {
        Success(())
      }
    } yield ()
  }

  override def visitExampleIntegerConstant(node: ExampleIntegerConstant, environment: ExampleEnvironment): Try[Int] = {
    Success(node.value)
  }

  override def visitExampleIntegerVariable(node: ExampleIntegerVariable, environment: ExampleEnvironment): Try[Int] = {
    for {
      case value: Int <- environment.get(node.name)
    } yield value
  }

  override def visitExampleArrayAccess(node: ExampleArrayAccess, environment: ExampleEnvironment): Try[Int] = {
    for {
      index <- node.index.accept(this, environment).flatMap {
        case intValue: Int => Success(intValue)
        case _ => Failure(new IllegalArgumentException("Index must be an integer"))
      }
      array <- environment.get(node.arrayName).flatMap {
        case array: Array[Int] => Success(array)
        case _ => Failure(new IllegalArgumentException(s"${node.arrayName} is not an array of integers"))
      }
      value <- if (index >= 0 && index < array.length) {
        Success(array(index)) // Retourne l'élément à l'indice donné
      } else {
        Failure(new IndexOutOfBoundsException(s"Index $index out of bounds for array ${node.arrayName}"))
      }
    } yield value
  }



  override def visitExampleArrayAssign(node: ExampleArrayAssign, environment: ExampleEnvironment): Try[Int] = {
    for {
      index <- node.index.accept(this, environment).flatMap {
        case intValue: Int => Success(intValue)
        case _ => Failure(new IllegalArgumentException("Index must be an integer"))
      }
      value <- node.value.accept(this, environment).flatMap {
        case intValue: Int => Success(intValue)
        case _ => Failure(new IllegalArgumentException("Assigned value must be an integer"))
      }
      array <- environment.get(node.arrayName).flatMap {
        case array: Array[Int] => Success(array)
        case _ => Failure(new IllegalArgumentException(s"${node.arrayName} is not an array of integers"))
      }
      _ <- if (index >= 0 && index < array.length) {
        array(index) = value // Modifie l'élément à l'indice donné
        Success(())
      } else {
        Failure(new IndexOutOfBoundsException(s"Index $index out of bounds for array ${node.arrayName}"))
      }
    } yield value
  }


  override def visitExampleStatements(node: ExampleStatements, environment: ExampleEnvironment): Try[Unit] = {
    node.statements.iterator.map(_.accept(this, environment)).toTry.map(_ => ())
  }




  override def visitExampleIntegerUnaryOperation(node: ExampleIntegerUnaryOperation, environment: ExampleEnvironment): Try[Int] = {
    for {
      case value: Int <- node.value.accept(this, environment)
    } yield node.operator match {
      case ExampleIntegerUnaryOperator.Pos => value
      case ExampleIntegerUnaryOperator.Neg => -value
    }
  }

  override def visitExampleIntegerBinaryOperation(node: ExampleIntegerBinaryOperation, environment: ExampleEnvironment): Try[Int] = {
    for {
      case leftValue: Int <- node.left.accept(this, environment)
      case rightValue: Int <- node.right.accept(this, environment)
    } yield node.operator match {
      case ExampleIntegerBinaryOperator.Add => leftValue + rightValue
      case ExampleIntegerBinaryOperator.Sub => leftValue - rightValue
      case ExampleIntegerBinaryOperator.Mul => leftValue * rightValue
      case ExampleIntegerBinaryOperator.Div => leftValue / rightValue
    }
  }

  override def visitExampleBooleanConstant(node: ExampleBooleanConstant, environment: ExampleEnvironment): Try[Boolean] = {
    Success(node.value)
  }

  override def visitExampleBooleanVariable(node: ExampleBooleanVariable, environment: ExampleEnvironment): Try[Boolean] = {
    for {
      case value: Boolean <- environment.get(node.name)
    } yield value
  }

  override def visitExampleBooleanUnaryOperation(node: ExampleBooleanUnaryOperation, environment: ExampleEnvironment): Try[Boolean] = {
    for {
      case value: Boolean <- node.value.accept(this, environment)
    } yield node.operator match {
      case ExampleBooleanUnaryOperator.Neg => !value
    }
  }

  override def visitExampleBooleanBinaryOperation(node: ExampleBooleanBinaryOperation, environment: ExampleEnvironment): Try[Boolean] = {
    for {
      case leftValue: Boolean <- node.left.accept(this, environment)
      case rightValue: Boolean <- node.right.accept(this, environment)
    } yield node.operator match {
      case ExampleBooleanBinaryOperator.And => leftValue && rightValue
      case ExampleBooleanBinaryOperator.Or => leftValue || rightValue
    }
  }

  override def visitExampleBooleanEqualComparisonOperation(node: ExampleBooleanEqualComparisonOperation, environment: ExampleEnvironment): Try[Boolean] = {
    for {
      case leftValue: Boolean <- node.left.accept(this, environment)
      case rightValue: Boolean <- node.right.accept(this, environment)
    } yield node.operator match {
      case ExampleEqualComparisonOperator.Eq => leftValue == rightValue
      case ExampleEqualComparisonOperator.Ne => leftValue != rightValue
    }
  }

  override def visitExampleIntegerComparisonOperation(node: ExampleIntegerComparisonOperation, environment: ExampleEnvironment): Try[Boolean] = {
    for {
      leftValue <- node.left.accept(this, environment).flatMap {
        case intValue: Int => Success(intValue)
        case _ => Failure(new IllegalArgumentException("Left value must be an integer"))
      }
      rightValue <- node.right.accept(this, environment).flatMap {
        case intValue: Int => Success(intValue)
        case _ => Failure(new IllegalArgumentException("Right value must be an integer"))
      }
    } yield {
      node.operator match {
        case ExampleIntegerComparisonOperator.Lt => (leftValue: Int) < (rightValue: Int)
        case ExampleIntegerComparisonOperator.Gt => (leftValue: Int) > (rightValue: Int)
        case ExampleIntegerComparisonOperator.Lte => (leftValue: Int) <= (rightValue: Int)
        case ExampleIntegerComparisonOperator.Gte => (leftValue: Int) >= (rightValue: Int)
      }
    }
  }


  override def visitExampleIntegerEqualComparisonOperation(node: ExampleIntegerEqualComparisonOperation, environment: ExampleEnvironment): Try[Boolean] = {
    for {
      leftValue <- node.left.accept(this, environment).flatMap {
        case intValue: Int => Success(intValue)
        case _ => Failure(new IllegalArgumentException("Left value must be an integer"))
      }
      rightValue <- node.right.accept(this, environment).flatMap {
        case intValue: Int => Success(intValue)
        case _ => Failure(new IllegalArgumentException("Right value must be an integer"))
      }
    } yield node.operator match {
      case ExampleEqualComparisonOperator.Eq => leftValue == rightValue
      case ExampleEqualComparisonOperator.Ne => leftValue != rightValue
    }
  }
}

