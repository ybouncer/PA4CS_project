package be.unamur.info.infom227.ast

sealed trait Statement {
  def accept[T, E](visitor: StatementVisitor[T, E], environment: E): T

  def lineNumber: Int
}

case class DeclareStatement(lineNumber: Int, name: String, Type: Type) extends Statement {
  override def accept[T, E](visitor: StatementVisitor[T, E], environment: E): T = {
    visitor.visitDeclareStatement(this, environment)
  }
}

sealed trait NonDeclareStatement extends Statement

case class AssignStatement(lineNumber: Int, variable: String, scope: Scope, expression: Expression) extends NonDeclareStatement {
  override def accept[T, E](visitor: StatementVisitor[T, E], environment: E): T = {
    visitor.visitAssignStatement(this, environment)
  }
}

case class PrintStatement(lineNumber: Int, expression: Expression) extends NonDeclareStatement {
  override def accept[T, E](visitor: StatementVisitor[T, E], environment: E): T = {
    visitor.visitPrintStatement(this, environment)
  }
}

case class IfStatement(lineNumber: Int, condition: BooleanExpression, ifStatements: Statements, elseStatements: Statements) extends NonDeclareStatement {
  override def accept[T, E](visitor: StatementVisitor[T, E], environment: E): T = {
    visitor.visitIfStatement(this, environment)
  }
}

case class WhileStatement(lineNumber: Int, condition: BooleanExpression, statements: Statements) extends NonDeclareStatement {
  override def accept[T, E](visitor: StatementVisitor[T, E], environment: E): T = {
    visitor.visitWhileStatement(this, environment)
  }
}


case class StatementAdapter(expression: Expression) extends Statement {
  override def accept[T, E](visitor: StatementVisitor[T, E], environment: E): T = {
    expression.accept(visitor.asInstanceOf[ExpressionVisitor[T, E]], environment)
  }
  override def lineNumber: Int = -1 // Ligne fictive ou ajustez selon les besoins
}
