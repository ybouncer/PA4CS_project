package be.unamur.info.infom227.interpreter

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class Environment(private val parentEnvironment: Option[Environment] = None) {

  private val variables = mutable.Map[String, Any]()

  def define(name: String, value: Any): Try[Unit] = {
    if (variables.contains(name)) {
      Failure(new IllegalArgumentException(s"Variable already defined: $name"))
    } else {
      variables.put(name, value)
      Success(())
    }
  }

  def get(name: String): Try[Any] = {
    variables.get(name) match {
      case Some(value) => Success(value)
      case None =>
        parentEnvironment match {
          case Some(parent) => parent.get(name)
          case None => Failure(new NoSuchElementException(s"Undefined variable: $name"))
        }
    }
  }

  def set(name: String, value: Any): Try[Unit] = {
    if (variables.contains(name)) {
      variables.put(name, value)
      Success(())
    } else {
      parentEnvironment match {
        case Some(parent) => parent.set(name, value)
        case None => Failure(new NoSuchElementException(s"Undefined variable: $name"))
      }
    }
  }

  def getArrayElement(name: String, index: Int): Try[Int] = {
    get(name) match {
      case Success(array: Array[Int]) if index >= 0 && index < array.length =>
        Success(array(index))
      case Success(_) => Failure(new IllegalArgumentException(s"Variable $name is not an array of integers"))
      case Failure(exception) => Failure(exception)
    }
  }

  def setArrayElement(name: String, index: Int, value: Int): Try[Unit] = {
    get(name) match {
      case Success(array: Array[Int]) if index >= 0 && index < array.length =>
        array(index) = value
        Success(())
      case Success(_) => Failure(new IllegalArgumentException(s"Variable $name is not an array of integers"))
      case Failure(exception) => Failure(exception)
    }
  }
}



