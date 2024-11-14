package be.unamur.info.infom227.analysis

case class ExampleAbstractEnvironment[V, L](lattice: ExampleLattice[L], returnAbstractValueOption: Option[L] = None, abstractValues: Map[V, L] = Map.empty) {
  def join(other: ExampleAbstractEnvironment[V, L]): Option[ExampleAbstractEnvironment[V, L]] = {
    (abstractValues.keys ++ other.abstractValues.keys).map(variable =>
        (abstractValues.get(variable), other.abstractValues.get(variable)) match
          case (Some(abstractValue), Some(otherAbstractValue)) => variable -> lattice.join(abstractValue, otherAbstractValue)
          case (Some(abstractValue), None) => variable -> Some(abstractValue)
          case (None, Some(otherAbstractValue)) => variable -> Some(otherAbstractValue)
          case (None, None) => variable -> None
      )
      .toMap
      .foldLeft[Option[Map[V, L]]](Some(Map.empty)) {
        case (acc, (variable, abstractValueOption)) =>
          for {
            abstractEnvironment <- acc
            abstractValue <- abstractValueOption
          } yield abstractEnvironment + (variable -> abstractValue)
      }
      .map(ExampleAbstractEnvironment(lattice, returnAbstractValueOption, _))
  }

  def includes(other: ExampleAbstractEnvironment[V, L]): Boolean = {
    (abstractValues.keys ++ other.abstractValues.keys).forall(variable =>
      (abstractValues.get(variable), other.abstractValues.get(variable)) match
        case (Some(abstractValue), Some(otherAbstractValue)) => lattice.includes(abstractValue, otherAbstractValue)
        case (Some(_), None) => true
        case (None, Some(_)) => false
        case (None, None) => false
    )
  }

  def set(variable: V, abstractValue: L): ExampleAbstractEnvironment[V, L] = {
    ExampleAbstractEnvironment(lattice, returnAbstractValueOption, abstractValues + (variable -> abstractValue))
  }

  def set(abstractVariables: Map[V, L]): ExampleAbstractEnvironment[V, L] = {
    ExampleAbstractEnvironment(lattice, returnAbstractValueOption, abstractValues ++ abstractVariables)
  }

  def setReturnAbstractValue(newReturnAbstractValue: L): ExampleAbstractEnvironment[V, L] = {
    ExampleAbstractEnvironment(lattice, Some(newReturnAbstractValue), abstractValues)
  }
}
