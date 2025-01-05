package be.unamur.info.infom227.ast

sealed trait Type {
  def accept[T, E](visitor: TypeVisitor[T, E], environment: E): T
}

case object Int extends Type {
  override def accept[T, E](visitor: TypeVisitor[T, E], environment: E): T = {
    visitor.visitInt(this, environment)
  }
}

case object Bool extends Type {
  override def accept[T, E](visitor: TypeVisitor[T, E], environment: E): T = {
    visitor.visitBool(this, environment)
  }
}

case class newArray(baseType: Type, size: Option[Int]) extends Type {
  override def accept[T, E](visitor: TypeVisitor[T, E], environment: E): T = {
    visitor.visitArray(this, environment)
  }
}