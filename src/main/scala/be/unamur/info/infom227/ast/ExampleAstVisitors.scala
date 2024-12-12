package be.unamur.info.infom227.ast

import be.unamur.info.infom227.*

trait ExampleSequenceVisitor[T, D] {
  def visitExampleProgram(node: ExampleProgram, data: D): T

  def visitExampleScope(node: ExampleScope, data: D): T

  def visitExampleStatements(node: ExampleStatements, data: D): T
}

trait ExampleStatementVisitor[T, D] {
  def visitExampleDeclareStatement(node: ExampleDeclareStatement, data: D): T

  def visitExampleAssignStatement(node: ExampleAssignStatement, data: D): T

  def visitExamplePrintStatement(node: ExamplePrintStatement, data: D): T

  def visitExampleIfStatement(node: ExampleIfStatement, data: D): T

  def visitExampleWhileStatement(node: ExampleWhileStatement, data: D): T
}

trait ExampleTypeVisitor[T, E] {
  def visitExampleInt(exampleInt: ExampleInt.type, environment: E): T

  def visitExampleBool(exampleBool: ExampleBool.type, environment: E): T

  def visitExampleIntArray(exampleIntArray: ExampleIntArray, environment: E): T

  def visitExampleBoolArray(exampleBoolArray: ExampleBoolArray, environment: E): T
}

trait ExampleExpressionVisitor[T, D] {
  def visitExampleIntegerConstant(node: ExampleIntegerConstant, data: D): T

  def visitExampleIntegerVariable(node: ExampleIntegerVariable, data: D): T

  def visitExampleIntegerUnaryOperation(node: ExampleIntegerUnaryOperation, data: D): T

  def visitExampleIntegerBinaryOperation(node: ExampleIntegerBinaryOperation, data: D): T

  def visitExampleBooleanConstant(node: ExampleBooleanConstant, data: D): T

  def visitExampleBooleanVariable(node: ExampleBooleanVariable, data: D): T

  def visitExampleBooleanUnaryOperation(node: ExampleBooleanUnaryOperation, data: D): T

  def visitExampleBooleanBinaryOperation(node: ExampleBooleanBinaryOperation, data: D): T

  def visitExampleBooleanEqualComparisonOperation(node: ExampleBooleanEqualComparisonOperation, data: D): T

  def visitExampleIntegerComparisonOperation(node: ExampleIntegerComparisonOperation, data: D): T

  def visitExampleIntegerEqualComparisonOperation(node: ExampleIntegerEqualComparisonOperation, data: D): T

  def visitExampleIntegerArrayAccess(exampleIntegerArrayAccess: ExampleIntegerArrayAccess, environment: D): T

  def visitExampleBooleanArrayAccess(exampleBooleanArrayAccess: ExampleBooleanArrayAccess, environment: D): T

}
