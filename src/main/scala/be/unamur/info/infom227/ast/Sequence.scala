package be.unamur.info.infom227.ast

trait Sequence {
  def accept[T, E](visitor: SequenceVisitor[T, E], environment: E): T
}

case class Program(scope: Scope) extends Sequence {
  override def accept[T, E](visitor: SequenceVisitor[T, E], environment: E): T = {
    visitor.visitProgram(this, environment)
  }
}

case class Scope(variables: Map[String, Type], statements: Statement*) extends Sequence {
  override def accept[T, E](visitor: SequenceVisitor[T, E], environment: E): T = {
    visitor.visitScope(this, environment)
  }
}

case class Statements(statements: NonDeclareStatement*) extends Sequence {
  override def accept[T, E](visitor: SequenceVisitor[T, E], environment: E): T = {
    visitor.visitStatements(this, environment)
  }
}
