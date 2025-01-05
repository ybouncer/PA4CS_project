package be.unamur.info.infom227.analysis

import be.unamur.info.infom227.ast.*
import be.unamur.info.infom227.cfg.ProgramPoint

import scala.util.{Failure, Success, Try}

enum SignAnalysisAbstractValue:
  case Bottom, B, N, Z, ZP, U

object SignAnalysisAbstractValue {
  val lattice: Lattice[SignAnalysisAbstractValue] = {
    FiniteSizeLattice(
      Set(
        (SignAnalysisAbstractValue.Bottom, SignAnalysisAbstractValue.N),
        (SignAnalysisAbstractValue.Bottom, SignAnalysisAbstractValue.ZP),
        (SignAnalysisAbstractValue.Bottom, SignAnalysisAbstractValue.B),
        (SignAnalysisAbstractValue.Bottom, SignAnalysisAbstractValue.Z),
        (SignAnalysisAbstractValue.N, SignAnalysisAbstractValue.U),
        (SignAnalysisAbstractValue.ZP, SignAnalysisAbstractValue.U),
        (SignAnalysisAbstractValue.B, SignAnalysisAbstractValue.U),
        (SignAnalysisAbstractValue.Z, SignAnalysisAbstractValue.U),
      )
    )
  }
}

class SignAnalysisFlowFunction
    extends StatementVisitor[Try[AbstractEnvironment[String, SignAnalysisAbstractValue]], AbstractEnvironment[String, SignAnalysisAbstractValue]]
    with ExpressionVisitor[Try[SignAnalysisAbstractValue], AbstractEnvironment[String, SignAnalysisAbstractValue]] {

  override def visitDeclareStatement(
      node: DeclareStatement,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[AbstractEnvironment[String, SignAnalysisAbstractValue]] = {
    //println(s"Processing DeclareStatement: $node")
    //println(s"Node type: ${node.Type}")

    node.Type match {
      case Int =>
        //println(s"Matched type: Int for variable '${node.name}'")
        Success(abstractEnvironment.set(node.name, SignAnalysisAbstractValue.Z))

      case Bool =>
        //println(s"Matched type: Bool for variable '${node.name}'")
        Success(abstractEnvironment.set(node.name, SignAnalysisAbstractValue.B))

      case newArray(Int, Some(size)) =>
        val correctedSize = size.asInstanceOf[Int]
        val updatedEnvironment = (0 until correctedSize).foldLeft(abstractEnvironment) { (env, index) =>
          env.set(s"${node.name}{$index}", SignAnalysisAbstractValue.U)
        }
        Success(updatedEnvironment)
      case _ =>
        Failure(new RuntimeException(s"Unsupported type: ${node.Type}"))
    }
  }


  override def visitAssignStatement(
      node: AssignStatement,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[AbstractEnvironment[String, SignAnalysisAbstractValue]] = {
    if (node.scope.statements.nonEmpty) {
      Success(abstractEnvironment.set(node.variable, SignAnalysisAbstractValue.U))
    } else {
      node.expression.accept(this, abstractEnvironment).map(abstractEnvironment.set(node.variable, _))
    }
  }

  override def visitArrayAccess(
      node: ArrayAccess,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = {
  node.index.accept(this, abstractEnvironment).flatMap {
      case other => 
      Success(other)
  }
  }

  override def visitArrayAssign(
      node: ArrayAssign,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = {
      for {
          indexSign <- node.index.accept(this, environment)
          valueSign <- node.value.accept(this, environment)
      } yield {
          valueSign // Retourne le signe de la valeur assignée
      }
  }

  override def visitPrintStatement(
      node: PrintStatement,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[AbstractEnvironment[String, SignAnalysisAbstractValue]] = Success(abstractEnvironment)

  override def visitIfStatement(
      node: IfStatement,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[AbstractEnvironment[String, SignAnalysisAbstractValue]] = Success(abstractEnvironment)

  override def visitWhileStatement(
      node: WhileStatement,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[AbstractEnvironment[String, SignAnalysisAbstractValue]] = Success(abstractEnvironment)

  override def visitIntegerConstant(
      node: IntegerConstant,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = Success(if (node.value > 0) SignAnalysisAbstractValue.ZP else if (node.value == 0) SignAnalysisAbstractValue.Z else SignAnalysisAbstractValue.N)

  override def visitIntegerVariable(
      node: IntegerVariable,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = Try(abstractEnvironment.abstractValues(node.name))

  override def visitIntegerUnaryOperation(
      node: IntegerUnaryOperation,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = {
    node.operator match {
      case IntegerUnaryOperator.Neg => node.value.accept(this, abstractEnvironment).map {
        case SignAnalysisAbstractValue.ZP => SignAnalysisAbstractValue.N
        case SignAnalysisAbstractValue.N => SignAnalysisAbstractValue.ZP
        case other => other
      }
    }
  }

  override def visitIntegerBinaryOperation(
      node: IntegerBinaryOperation,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = {
    for {
      leftSign <- node.left.accept(this, abstractEnvironment)
      rightSign <- node.right.accept(this, abstractEnvironment)
    } yield {
      (node.operator, leftSign, rightSign) match {
        // Addition
        case (IntegerBinaryOperator.Add, SignAnalysisAbstractValue.Z, SignAnalysisAbstractValue.Z) => SignAnalysisAbstractValue.Z
        case (IntegerBinaryOperator.Add, SignAnalysisAbstractValue.Z, other) => other
        case (IntegerBinaryOperator.Add, other, SignAnalysisAbstractValue.Z) => other
        case (IntegerBinaryOperator.Add, SignAnalysisAbstractValue.N, SignAnalysisAbstractValue.N) => SignAnalysisAbstractValue.N
        case (IntegerBinaryOperator.Add, SignAnalysisAbstractValue.ZP, SignAnalysisAbstractValue.ZP) => SignAnalysisAbstractValue.ZP
        case (IntegerBinaryOperator.Add, SignAnalysisAbstractValue.N, SignAnalysisAbstractValue.ZP) => SignAnalysisAbstractValue.U
        case (IntegerBinaryOperator.Add, SignAnalysisAbstractValue.ZP, SignAnalysisAbstractValue.N) => SignAnalysisAbstractValue.U
        case (IntegerBinaryOperator.Add, SignAnalysisAbstractValue.U, _) => SignAnalysisAbstractValue.U
        case (IntegerBinaryOperator.Add, _, SignAnalysisAbstractValue.U) => SignAnalysisAbstractValue.U

        // Multiplication
        case (IntegerBinaryOperator.Mul, SignAnalysisAbstractValue.Z, _) => SignAnalysisAbstractValue.Z
        case (IntegerBinaryOperator.Mul, _, SignAnalysisAbstractValue.Z) => SignAnalysisAbstractValue.Z
        case (IntegerBinaryOperator.Mul, SignAnalysisAbstractValue.N, SignAnalysisAbstractValue.N) => SignAnalysisAbstractValue.ZP
        case (IntegerBinaryOperator.Mul, SignAnalysisAbstractValue.N, SignAnalysisAbstractValue.ZP) => SignAnalysisAbstractValue.N
        case (IntegerBinaryOperator.Mul, SignAnalysisAbstractValue.ZP, SignAnalysisAbstractValue.N) => SignAnalysisAbstractValue.N
        case (IntegerBinaryOperator.Mul, SignAnalysisAbstractValue.ZP, SignAnalysisAbstractValue.ZP) => SignAnalysisAbstractValue.ZP
        case (IntegerBinaryOperator.Mul, SignAnalysisAbstractValue.U, _) => SignAnalysisAbstractValue.U
        case (IntegerBinaryOperator.Mul, _, SignAnalysisAbstractValue.U) => SignAnalysisAbstractValue.U

        // Soustraction
        case (IntegerBinaryOperator.Sub, SignAnalysisAbstractValue.Z, SignAnalysisAbstractValue.Z) => SignAnalysisAbstractValue.Z
        case (IntegerBinaryOperator.Sub, SignAnalysisAbstractValue.Z, other) => other match {
          case SignAnalysisAbstractValue.ZP => SignAnalysisAbstractValue.N
          case SignAnalysisAbstractValue.N => SignAnalysisAbstractValue.ZP
          case other => other
        }
        case (IntegerBinaryOperator.Sub, other, SignAnalysisAbstractValue.Z) => other
        case (IntegerBinaryOperator.Sub, SignAnalysisAbstractValue.N, SignAnalysisAbstractValue.N) => SignAnalysisAbstractValue.U
        case (IntegerBinaryOperator.Sub, SignAnalysisAbstractValue.ZP, SignAnalysisAbstractValue.ZP) => SignAnalysisAbstractValue.U
        case (IntegerBinaryOperator.Sub, SignAnalysisAbstractValue.N, SignAnalysisAbstractValue.ZP) => SignAnalysisAbstractValue.N
        case (IntegerBinaryOperator.Sub, SignAnalysisAbstractValue.ZP, SignAnalysisAbstractValue.N) => SignAnalysisAbstractValue.ZP
        case (IntegerBinaryOperator.Sub, SignAnalysisAbstractValue.U, _) => SignAnalysisAbstractValue.U
        case (IntegerBinaryOperator.Sub, _, SignAnalysisAbstractValue.U) => SignAnalysisAbstractValue.U

        // Default case (non-manageable cases)
        case _ => SignAnalysisAbstractValue.U
      }
    }
  }


  override def visitBooleanConstant(
      node: BooleanConstant,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = Success(if (node.value) SignAnalysisAbstractValue.B else SignAnalysisAbstractValue.U)

  override def visitBooleanVariable(    
      node: BooleanVariable,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = Success(SignAnalysisAbstractValue.B)

  override def visitBooleanUnaryOperation(
      node: BooleanUnaryOperation,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = Success(SignAnalysisAbstractValue.B)

  override def visitBooleanBinaryOperation(
      node: BooleanBinaryOperation,
      abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[SignAnalysisAbstractValue] = {
    for {
      leftSign <- node.left.accept(this, abstractEnvironment)
      rightSign <- node.right.accept(this, abstractEnvironment)
    } yield {
      (node.operator, leftSign, rightSign) match {
        case (BooleanBinaryOperator.And, SignAnalysisAbstractValue.B, SignAnalysisAbstractValue.B) => SignAnalysisAbstractValue.B
        case (BooleanBinaryOperator.Or, SignAnalysisAbstractValue.B, SignAnalysisAbstractValue.B) => SignAnalysisAbstractValue.B
        case _ => SignAnalysisAbstractValue.U
      }
    }
  }
  
  override def visitBooleanEqualComparisonOperation(node: BooleanEqualComparisonOperation, abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]): Try[SignAnalysisAbstractValue] = {
    Success(SignAnalysisAbstractValue.B)
  }

    override def visitIntegerComparisonOperation(node: IntegerComparisonOperation, abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]): Try[SignAnalysisAbstractValue] = {
    Success(SignAnalysisAbstractValue.B)
  }

  override def visitIntegerEqualComparisonOperation(node: IntegerEqualComparisonOperation, abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]): Try[SignAnalysisAbstractValue] = {
    Success(SignAnalysisAbstractValue.B)
  }
}

class ConditionUpdateFunction
    extends ExpressionVisitor[Try[Option[Map[String, SignAnalysisAbstractValue]]], AbstractEnvironment[String, SignAnalysisAbstractValue]] {

  private def checkConstraint(
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue],
      variable: String,
      requiredSign: SignAnalysisAbstractValue
  ): Option[Map[String, SignAnalysisAbstractValue]] = {
    for {
      bottom <- environment.lattice.bottom
      currentSign <- environment.abstractValues.get(variable)
      updatedSign <- if (currentSign == bottom) {
        Some(requiredSign)
      } else {
        environment.lattice.meet(currentSign, requiredSign)
      }
      if updatedSign != bottom
    } yield Map(variable -> updatedSign)
  }

  override def visitIntegerEqualComparisonOperation(
      node: IntegerEqualComparisonOperation,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    (node.operator, node.left, node.right) match {
      case (EqualComparisonOperator.Eq, IntegerVariable(varName), IntegerConstant(value)) =>
        val requiredSign = if (value > 0) SignAnalysisAbstractValue.ZP else if (value == 0) SignAnalysisAbstractValue.Z else SignAnalysisAbstractValue.N
        Success(checkConstraint(environment, varName, requiredSign))

      case (EqualComparisonOperator.Ne, IntegerVariable(varName), IntegerConstant(value)) =>
        val excludedSign = if (value > 0) SignAnalysisAbstractValue.ZP else if (value == 0) SignAnalysisAbstractValue.Z else SignAnalysisAbstractValue.N
        Success(Some(Map(varName -> SignAnalysisAbstractValue.U))) // Mark as unknown for inequality

      case _ => Success(Some(Map.empty))
    }
  }

  override def visitIntegerComparisonOperation(
      node: IntegerComparisonOperation,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    (node.operator, node.left, node.right) match {
      case (IntegerComparisonOperator.Lt, IntegerVariable(varName), IntegerConstant(value)) =>
        if (value <= 0) Success(checkConstraint(environment, varName, SignAnalysisAbstractValue.N))
        else Success(Some(Map.empty))

      case (IntegerComparisonOperator.Gt, IntegerVariable(varName), IntegerConstant(value)) =>
        if (value >= 0) Success(checkConstraint(environment, varName, SignAnalysisAbstractValue.ZP))
        else Success(Some(Map.empty))

      case (IntegerComparisonOperator.Lte, IntegerVariable(varName), IntegerConstant(value)) =>
        if (value < 0) Success(Some(Map(varName -> SignAnalysisAbstractValue.N))) else Success(Some(Map.empty))

      case (IntegerComparisonOperator.Gte, IntegerVariable(varName), IntegerConstant(value)) =>
        if (value > 0) Success(Some(Map(varName -> SignAnalysisAbstractValue.ZP))) else Success(Some(Map.empty))

      case _ => Success(Some(Map.empty))
    }
  }

  override def visitBooleanConstant(
      node: BooleanConstant,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    if (node.value) Success(Some(Map.empty)) else Success(Some(Map.empty))
  }

  override def visitBooleanVariable(
      node: BooleanVariable,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = Success(Some(Map(node.name -> SignAnalysisAbstractValue.B)))

  override def visitBooleanUnaryOperation(
      node: BooleanUnaryOperation,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
      Failure(NotImplementedError())
  }

  override def visitIntegerUnaryOperation(
      node: IntegerUnaryOperation,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
      Failure(NotImplementedError())
  }

  override def visitIntegerVariable(
      node: IntegerVariable,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    Failure(NotImplementedError())
  }

  override def visitIntegerConstant(
      node: IntegerConstant,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    Failure(NotImplementedError())
  }

  override def visitArrayAccess(
      node: ArrayAccess,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    Failure(NotImplementedError())
  }

  override def visitArrayAssign(
      node: ArrayAssign,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    Failure(NotImplementedError())
  }

  override def visitBooleanBinaryOperation(
      node: BooleanBinaryOperation,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
      Success(Some(Map.empty))
  }

  override def visitIntegerBinaryOperation(
      node: IntegerBinaryOperation,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    Failure(NotImplementedError())
  }

  override def visitBooleanEqualComparisonOperation(
      node: BooleanEqualComparisonOperation,
      environment: AbstractEnvironment[String, SignAnalysisAbstractValue]
  ): Try[Option[Map[String, SignAnalysisAbstractValue]]] = Success(Some(Map.empty))
}




object SignAnalysisWorklist extends Worklist(SignAnalysisAbstractValue.lattice) {
  override def controlFlowFunction(p: ProgramPoint, abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]): Try[AbstractEnvironment[String, SignAnalysisAbstractValue]] = {
    p.statement.accept(SignAnalysisFlowFunction(), abstractEnvironment)
  }

  override def conditionUpdateFunction(condition: BooleanExpression, abstractEnvironment: AbstractEnvironment[String, SignAnalysisAbstractValue]): Try[Option[Map[String, SignAnalysisAbstractValue]]] = {
    condition.accept(ConditionUpdateFunction(), abstractEnvironment)
  }
}

object SignAnalysisResultsInterpreter extends ResultsInterpreter[SignAnalysisAbstractValue](
  (p, results) => {
    p.statement match {
      // Cas d'assignation aux tableaux
      case AssignStatement(_, _, _, ArrayAssign(arrayName, IntegerVariable(index), _)) =>
        results
          .get(p)
          .flatMap(_.abstractValues.get(index)) // Récupérer la valeur abstraite de `index`
          .flatMap {
            case SignAnalysisAbstractValue.N =>
              Some(ErrorMessageType.Error, s"Array assignment with negative index detected for '$arrayName'!")
            case SignAnalysisAbstractValue.B =>
              Some(ErrorMessageType.Error, s"Array assignment with boolean index detected for '$arrayName'!")
            case SignAnalysisAbstractValue.U =>
              Some(ErrorMessageType.Warning, s"Array assignment with potentially invalid index for '$arrayName'.")
            case _ =>
              None
          }
      case AssignStatement(_, _, _, ArrayAccess(arrayName, IntegerVariable(index))) =>
        results
          .get(p)
          .flatMap(_.abstractValues.get(index)) // Récupérer la valeur abstraite de `index`
          .flatMap {
            case SignAnalysisAbstractValue.N =>
              Some(ErrorMessageType.Error, s"Array access with negative index detected for '$arrayName'!")
            case SignAnalysisAbstractValue.B =>
              Some(ErrorMessageType.Error, s"Array access with boolean index detected for '$arrayName'!")
            case SignAnalysisAbstractValue.U =>
              Some(ErrorMessageType.Warning, s"Array access with potentially invalid index for '$arrayName'.")
            case _ =>
              None
          }

      // Cas par défaut : aucun message généré
      case _ =>
        None
    }
  }
)





