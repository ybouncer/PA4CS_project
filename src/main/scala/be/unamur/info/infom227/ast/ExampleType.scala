package be.unamur.info.infom227.ast

import be.unamur.info.infom227.ast.ExampleTypeVisitor

sealed trait ExampleType {
  def accept[T, E](visitor: ExampleTypeVisitor[T, E], environment: E): T
}

case object ExampleInt extends ExampleType {
  override def accept[T, E](visitor: ExampleTypeVisitor[T, E], environment: E): T = {
    visitor.visitExampleInt(this, environment)
  }
}

case object ExampleBool extends ExampleType {
  override def accept[T, E](visitor: ExampleTypeVisitor[T, E], environment: E): T = {
    visitor.visitExampleBool(this, environment)
  }
}

case class ExampleArray(elementType: ExampleType, size: Int) extends ExampleType {
  override def accept[T, E](visitor: ExampleTypeVisitor[T, E], environment: E): T = {
    visitor.visitExampleArray(this, environment)
  }
}