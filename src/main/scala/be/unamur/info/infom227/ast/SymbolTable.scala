package be.unamur.info.infom227.ast

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

class SymbolTable(private val parentSymbolTableOption: Option[SymbolTable] = None) {

  private val symbols = mutable.Map[String, Type]()

  def variables: Map[String, Type] = {
    symbols.toMap
  }

  def get(name: String, recursive: Boolean = true): Try[Type] = {
    symbols.get(name) match {
      case Some(value) => Success(value)
      case None => parentSymbolTableOption match {
        case Some(parentSymbolTable) if recursive => parentSymbolTable.get(name, recursive)
        case _ => Failure(new NoSuchElementException(s"Undefined variable : $name"))
      }
    }
  }

  def define(name: String, Type: Type): Try[Unit] = {
    if (symbols.contains(name)) {
      Failure(new IllegalArgumentException(s"Variable already defined : $name"))
    } else {
      symbols.put(name, Type)
      Success(())
    }
  }

  override def toString: String = symbols.toString()
}
