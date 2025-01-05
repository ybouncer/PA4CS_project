package be.unamur.info.infom227.analysis

case class AbstractEnvironment[V, L](
    lattice: Lattice[L],
    returnAbstractValueOption: Option[L] = None,
    abstractValues: Map[V, L] = Map.empty
) {
  def join(other: AbstractEnvironment[V, L]): Option[AbstractEnvironment[V, L]] = {
    (abstractValues.keys ++ other.abstractValues.keys)
      .foldLeft[Option[Map[V, L]]](Some(Map.empty)) {
        case (acc, variable) =>
          for {
            abstractEnvironment <- acc
            abstractValue <- (abstractValues.get(variable), other.abstractValues.get(variable)) match {
              case (Some(abstractValue), Some(otherAbstractValue)) => lattice.join(abstractValue, otherAbstractValue)
              case (Some(abstractValue), None) => Some(abstractValue)
              case (None, Some(otherAbstractValue)) => Some(otherAbstractValue)
              case _ => None
            }
          } yield abstractEnvironment + (variable -> abstractValue)
      }
      .map(AbstractEnvironment(lattice, returnAbstractValueOption, _))
  }

  def includes(other: AbstractEnvironment[V, L]): Boolean = {
    (abstractValues.keys ++ other.abstractValues.keys).forall(variable =>
      (abstractValues.get(variable), other.abstractValues.get(variable)) match {
        case (Some(abstractValue), Some(otherAbstractValue)) => lattice.includes(abstractValue, otherAbstractValue)
        case (Some(_), None) => true
        case (None, Some(_)) => false
        case _ => false
      }
    )
  }

  def set(variable: V, abstractValue: L): AbstractEnvironment[V, L] = {
    //println(s"Updating environment: Setting $variable -> $abstractValue")
    //println(s"Current environment: $abstractValues")
    val updatedEnv = AbstractEnvironment(lattice, returnAbstractValueOption, abstractValues + (variable -> abstractValue))
    //println(s"Updated environment: ${updatedEnv.abstractValues}")
    updatedEnv
  }


  def set(abstractVariables: Map[V, L]): AbstractEnvironment[V, L] = {
    //println(s"Updating environment with variables: $abstractVariables")
    //println(s"Current environment: $abstractValues")
    val updatedEnv = AbstractEnvironment(lattice, returnAbstractValueOption, abstractValues ++ abstractVariables)
    //println(s"Updated environment: ${updatedEnv.abstractValues}")
    updatedEnv
  }


  def setReturnAbstractValue(newReturnAbstractValue: L): AbstractEnvironment[V, L] = {
    AbstractEnvironment(lattice, Some(newReturnAbstractValue), abstractValues)
  }
}
