package be.unamur.info.infom227.ast

import be.unamur.info.infom227.*

trait SequenceVisitor[T, D] {
  def visitProgram(node: Program, data: D): T

  def visitScope(node: Scope, data: D): T

  def visitStatements(node: Statements, data: D): T
}

trait StatementVisitor[T, D] {
  def visitDeclareStatement(node: DeclareStatement, data: D): T

  def visitAssignStatement(node: AssignStatement, data: D): T

  def visitPrintStatement(node: PrintStatement, data: D): T

  def visitIfStatement(node: IfStatement, data: D): T

  def visitWhileStatement(node: WhileStatement, data: D): T
}

trait TypeVisitor[T, D] {
  def visitInt(node: Int.type, data: D): T

  def visitBool(node: Bool.type, data: D): T

  def visitArray(node: newArray, data: D): T
}

trait ExpressionVisitor[T, D] {
  def visitIntegerConstant(node: IntegerConstant, data: D): T

  def visitIntegerVariable(node: IntegerVariable, data: D): T

  def visitIntegerUnaryOperation(node: IntegerUnaryOperation, data: D): T

  def visitIntegerBinaryOperation(node: IntegerBinaryOperation, data: D): T

  def visitBooleanConstant(node: BooleanConstant, data: D): T

  def visitBooleanVariable(node: BooleanVariable, data: D): T

  def visitBooleanUnaryOperation(node: BooleanUnaryOperation, data: D): T

  def visitBooleanBinaryOperation(node: BooleanBinaryOperation, data: D): T

  def visitBooleanEqualComparisonOperation(node: BooleanEqualComparisonOperation, data: D): T

  def visitIntegerComparisonOperation(node: IntegerComparisonOperation, data: D): T

  def visitIntegerEqualComparisonOperation(node: IntegerEqualComparisonOperation, data: D): T

  def visitArrayAccess(node: ArrayAccess, data: D): T

  def visitArrayAssign(node: ArrayAssign, data: D): T
}
