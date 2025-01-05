package be.unamur.info.infom227.analysis

import be.unamur.info.infom227.cfg.{Cfg, ProgramPoint}

import scala.util.Try

enum ErrorMessageType:
  case Error, Warning

abstract class ResultsInterpreter[L](processingRules: (ProgramPoint, Map[ProgramPoint, AbstractEnvironment[String, L]]) => Option[(ErrorMessageType, String)]) {
  def interpret(cfg: Cfg, results: Map[ProgramPoint, AbstractEnvironment[String, L]]): Try[List[(Int, ErrorMessageType, String)]] = {
    Try {
      val entryPoint = cfg.entryPoint.get

      val programPointDone = scala.collection.mutable.Set[ProgramPoint]()
      val programPointToInterpret = scala.collection.mutable.Set[ProgramPoint](entryPoint)
      val messages = scala.collection.mutable.ArrayBuffer[(Int, ErrorMessageType, String)]()

      while (programPointToInterpret.nonEmpty) {
        val programPoint = programPointToInterpret.head
        programPointToInterpret.remove(programPoint)

        processingRules(programPoint, results).map((messageType, message) =>
          messages.append((programPoint.statement.lineNumber, messageType, message))
        )

        programPointDone.add(programPoint)

        programPointToInterpret.addAll(cfg.succ(programPoint).removedAll(programPointDone))
      }

      messages.toList
    }
  }
}