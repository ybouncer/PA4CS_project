package be.unamur.info.infom227.ast

import be.unamur
import be.unamur.info
import be.unamur.info.infom227
import be.unamur.info.infom227.*

sealed trait Expression {
  def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T

  def Type: Type
}


sealed trait IntegerExpression extends Expression {
  override def Type: Type = Int
}

case class IntegerConstant(value: Int) extends IntegerExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitIntegerConstant(this, environment)
  }
}

case class IntegerVariable(name: String) extends IntegerExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitIntegerVariable(this, environment)
  }
}

case class IntegerUnaryOperation(operator: IntegerUnaryOperator, value: IntegerExpression) extends IntegerExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitIntegerUnaryOperation(this, environment)
  }
}

case class IntegerBinaryOperation(operator: IntegerBinaryOperator, left: IntegerExpression, right: IntegerExpression) extends IntegerExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitIntegerBinaryOperation(this, environment)
  }
}


sealed trait BooleanExpression extends Expression {
  override def Type: Type = Bool

  def negate: BooleanExpression
}

case class BooleanConstant(value: Boolean) extends BooleanExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitBooleanConstant(this, environment)
  }

  override def negate: BooleanConstant = BooleanConstant(!value)
}

case class BooleanVariable(name: String) extends BooleanExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitBooleanVariable(this, environment)
  }

  override def negate: BooleanExpression = BooleanUnaryOperation(BooleanUnaryOperator.Neg, BooleanVariable(name))
}

case class BooleanUnaryOperation(operator: BooleanUnaryOperator, value: BooleanExpression) extends BooleanExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitBooleanUnaryOperation(this, environment)
  }

  override def negate: BooleanExpression = operator match
    case BooleanUnaryOperator.Neg => value
}

case class BooleanBinaryOperation(operator: BooleanBinaryOperator, left: BooleanExpression, right: BooleanExpression) extends BooleanExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitBooleanBinaryOperation(this, environment)
  }

  override def negate: BooleanExpression = operator match
    case BooleanBinaryOperator.And => BooleanBinaryOperation(BooleanBinaryOperator.Or, left.negate, right.negate)
    case BooleanBinaryOperator.Or => BooleanBinaryOperation(BooleanBinaryOperator.And, left.negate, right.negate)
}

case class BooleanEqualComparisonOperation(operator: EqualComparisonOperator, left: BooleanExpression, right: BooleanExpression) extends BooleanExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitBooleanEqualComparisonOperation(this, environment)
  }

  override def negate: BooleanExpression = operator match
    case EqualComparisonOperator.Eq => BooleanEqualComparisonOperation(EqualComparisonOperator.Ne, left, right)
    case EqualComparisonOperator.Ne => BooleanEqualComparisonOperation(EqualComparisonOperator.Eq, left, right)
}

case class IntegerComparisonOperation(operator: IntegerComparisonOperator, left: IntegerExpression, right: IntegerExpression) extends BooleanExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitIntegerComparisonOperation(this, environment)
  }

  override def negate: BooleanExpression = operator match
    case IntegerComparisonOperator.Gt => IntegerComparisonOperation(IntegerComparisonOperator.Lte, left, right)
    case IntegerComparisonOperator.Lt => IntegerComparisonOperation(IntegerComparisonOperator.Gte, left, right)
    case IntegerComparisonOperator.Gte => IntegerComparisonOperation(IntegerComparisonOperator.Lt, left, right)
    case IntegerComparisonOperator.Lte => IntegerComparisonOperation(IntegerComparisonOperator.Gt, left, right)
}

case class IntegerEqualComparisonOperation(operator: EqualComparisonOperator, left: IntegerExpression, right: IntegerExpression) extends BooleanExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitIntegerEqualComparisonOperation(this, environment)
  }

  override def negate: BooleanExpression = operator match
    case EqualComparisonOperator.Eq => IntegerEqualComparisonOperation(EqualComparisonOperator.Ne, left, right)
    case EqualComparisonOperator.Ne => IntegerEqualComparisonOperation(EqualComparisonOperator.Eq, left, right)
}

case class ArrayAccess(arrayName: String, index: IntegerExpression) extends IntegerExpression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitArrayAccess(this, environment)
  }
}

object ArrayAccess {
  def unapply(access: ArrayAccess): Option[(String, IntegerExpression)] = {
    Some((access.arrayName, access.index))
  }
}

case class ArrayAssign(arrayName: String, index: Expression, value: Expression) extends Expression {
  override def accept[T, E](visitor: ExpressionVisitor[T, E], environment: E): T = {
    visitor.visitArrayAssign(this, environment)
  }

  override def Type: Type = value.Type
}
