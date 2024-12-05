package be.unamur.info.infom227.ast

trait ExampleTypeVisitor[T, E] {
  def visitExampleInt(exampleInt: ExampleInt.type, environment: E): T
  def visitExampleBool(exampleBool: ExampleBool.type, environment: E): T
  def visitExampleArray(exampleArray: ExampleArray, environment: E): T
}