package be.unamur.info.infom227.analysis

import be.unamur.info.infom227.ast.BooleanExpression
import be.unamur.info.infom227.cfg.{Cfg, ProgramPoint}

import scala.util.Try


abstract class Worklist[L](lattice: Lattice[L]) {
  def controlFlowFunction(p: ProgramPoint, abstractEnvironment: AbstractEnvironment[String, L]): Try[AbstractEnvironment[String, L]]

  def conditionUpdateFunction(condition: BooleanExpression, abstractEnvironment: AbstractEnvironment[String, L]): Try[Option[Map[String, L]]]

  def worklist(cfg: Cfg): Try[Map[ProgramPoint, AbstractEnvironment[String, L]]] = {
    Try {
      val entryPoint = cfg.entryPoint.get
      val bottom = lattice.bottom.get

      val abstractEnvironments = cfg.programPoints.map(
        _ -> AbstractEnvironment(lattice, None, cfg.variables.keys.map(_ -> bottom).toMap)
      ).to(scala.collection.mutable.Map)

      val WL = scala.collection.mutable.Set(entryPoint)

      while (WL.nonEmpty) {
        val p = WL.head
        WL.remove(p)

        val res = controlFlowFunction(p, abstractEnvironments(p)).get

        for (r <- cfg.succ(p)) {
          val cond = cfg.cond(p, r).get

          conditionUpdateFunction(cond, res).get match
            case None => // Special case to ignore unreachable code
            case Some(abstractVariables) =>
              val resCond = res.set(abstractVariables)

              if (!abstractEnvironments(r).includes(resCond)) {
                abstractEnvironments(r) = abstractEnvironments(r).join(resCond).get

                WL.add(r)
              }
        }
      }

      abstractEnvironments.toMap
    }
  }
}
