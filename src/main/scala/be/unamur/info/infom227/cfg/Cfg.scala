package be.unamur.info.infom227.cfg

import be.unamur.info.infom227.ast.{BooleanExpression, Type}

case class Cfg(variables: Map[String, Type], edges: Map[(ProgramPoint, ProgramPoint), BooleanExpression]) {

  def succ(programPoint: ProgramPoint): Set[ProgramPoint] = {
    edges.keys.filter(_._1 == programPoint).map(_._2).toSet
  }

  def pred(programPoint: ProgramPoint): Set[ProgramPoint] = {
    edges.keys.filter(_._2 == programPoint).map(_._1).toSet
  }

  def cond(startProgramPoint: ProgramPoint, endProgramPoint: ProgramPoint): Option[BooleanExpression] = {
    edges.get((startProgramPoint, endProgramPoint))
  }

  def entryPoint: Option[ProgramPoint] = {
    edges.keys.map(_._1).find(pred(_).isEmpty)
  }

  def programPoints: Set[ProgramPoint] = {
    edges.keys.flatMap((startProgramPoint, endProgramPoint) => Set(startProgramPoint, endProgramPoint)).toSet
  }
}
