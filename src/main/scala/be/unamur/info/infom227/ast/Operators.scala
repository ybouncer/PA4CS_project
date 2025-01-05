package be.unamur.info.infom227.ast

enum EqualComparisonOperator {
  case Eq extends EqualComparisonOperator
  case Ne extends EqualComparisonOperator
}

enum IntegerUnaryOperator {
  case Pos extends IntegerUnaryOperator
  case Neg extends IntegerUnaryOperator
}

enum IntegerBinaryOperator {
  case Add extends IntegerBinaryOperator
  case Sub extends IntegerBinaryOperator
  case Mul extends IntegerBinaryOperator
  case Div extends IntegerBinaryOperator
}

enum IntegerComparisonOperator {
  case Gt extends IntegerComparisonOperator
  case Lt extends IntegerComparisonOperator
  case Gte extends IntegerComparisonOperator
  case Lte extends IntegerComparisonOperator
}

enum BooleanUnaryOperator {
  case Neg extends BooleanUnaryOperator
}

enum BooleanBinaryOperator {
  case And extends BooleanBinaryOperator
  case Or extends BooleanBinaryOperator
}
