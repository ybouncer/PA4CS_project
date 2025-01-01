// Updated ExampleEnvironment.scala
package be.unamur.info.infom227.interpreter

import java.io.{BufferedWriter, OutputStreamWriter}
import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class ExampleEnvironment private (
  private val stdout: BufferedWriter,
  private val parentEnvironmentOption: Option[ExampleEnvironment] = None
) {

  def this(parentEnvironment: ExampleEnvironment) = {
    this(parentEnvironment.stdout, Some(parentEnvironment))
  }

  def this(stdout: BufferedWriter) = {
    this(stdout, None)
  }

  def this() = {
    this(new BufferedWriter(new OutputStreamWriter(System.out)))
  }

  private val variables = mutable.HashMap[String, Int | Array[Int]]()

  def print(value: String): Try[Unit] = {
    Try {
      stdout.write(value)
      stdout.newLine()
      stdout.flush()
    }
  }

  def get(name: String, recursive: Boolean = true): Try[Int | Array[Int]] = {
    variables.get(name) match {
      case Some(value) => Success(value)
      case None => parentEnvironmentOption match {
        case Some(parentEnvironment) if recursive => parentEnvironment.get(name, recursive)
        case _ => Failure(new NoSuchElementException(s"Undefined variable: $name"))
      }
    }
  }

  def getArrayElement(name: String, index: Int): Try[Int] = {
    get(name).flatMap {
      case array: Array[Int] if index >= 0 && index < array.length => Success(array(index))
      case _: Array[Int] => Failure(new IndexOutOfBoundsException(s"Index $index out of bounds for array $name"))
      case _ => Failure(new IllegalArgumentException(s"Variable $name is not an array"))
    }
  }

  def define(name: String, value: Int | Array[Int]): Try[Unit] = {
    if (variables.contains(name)) {
      Failure(new IllegalArgumentException(s"Variable already defined: $name"))
    } else {
      variables.put(name, value)
      Success(())
    }
  }

  def set(name: String, value: Int | Array[Int]): Try[Unit] = {
    if (variables.contains(name)) {
      variables.put(name, value)
      Success(())
    } else {
      Failure(new NoSuchElementException(s"Undefined variable: $name"))
    }
  }

  def setArrayElement(name: String, index: Int, value: Int): Try[Unit] = {
    get(name).flatMap {
      case array: Array[Int] if index >= 0 && index < array.length =>
        array(index) = value
        Success(())
      case _: Array[Int] => Failure(new IndexOutOfBoundsException(s"Index $index out of bounds for array $name"))
      case _ => Failure(new IllegalArgumentException(s"Variable $name is not an array"))
    }
  }

  override def toString: String = variables.toString()
}


