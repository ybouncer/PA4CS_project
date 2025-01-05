package be.unamur.info.infom227.cfg

import be.unamur.info.infom227.ast.Statement

case class ProgramPoint(statement: Statement) {
  override def toString: String = s"PP(${statement.lineNumber.toString})"
}
