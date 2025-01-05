package be.unamur.info.infom227.cfg

import be.unamur.info.infom227.ast.*


object CfgBuilder {
  def build(scopeAst: Program | Scope): Cfg = {
    val (_, edges) = scopeAst.accept(CfgBuilder(), Set.empty)

    Cfg(
      scopeAst match {
        case Program(scope) => scope.variables
        case Scope(variables, _*) => variables
      },
      edges.flatMap((startEdges, endProgramPoint) =>
        startEdges.map((startProgramPoint, condition) =>
          (startProgramPoint, endProgramPoint) -> condition
        )
      ).toMap
    )
  }
}

private type CfgStartEdges = Set[(ProgramPoint, BooleanExpression)]
private type CfgEdges = Set[(CfgStartEdges, ProgramPoint)]

private class CfgBuilder extends
  SequenceVisitor[(CfgStartEdges, CfgEdges), CfgStartEdges],
  StatementVisitor[(CfgStartEdges, CfgEdges), CfgStartEdges],
  ExpressionVisitor[(CfgStartEdges, CfgEdges), CfgStartEdges] {

  override def visitProgram(node: Program, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    visitScope(node.scope, startEdges)
  }

  private def visitSequence(statements: Seq[Statement], startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    statements.foldLeft[(CfgStartEdges, CfgEdges)]((startEdges, Set.empty)) {
      case ((startEdges, edges), statement) =>
        val (newStartEdges, newEdges) = statement.accept(this, startEdges)
        (
          newStartEdges,
          edges ++ newEdges
        )
    }
  }

  override def visitScope(node: Scope, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    visitSequence(node.statements, startEdges)
  }

  override def visitStatements(node: Statements, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    visitSequence(node.statements, startEdges)
  }

  private def visitStatement(node: Statement, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    val programPoint = ProgramPoint(node)

    (
      Set((programPoint, BooleanConstant(true))),
      Set((startEdges, programPoint)),
    )
  }

  override def visitDeclareStatement(node: DeclareStatement, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    visitStatement(node, startEdges)
  }

  override def visitAssignStatement(node: AssignStatement, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    visitStatement(node, startEdges)
  }

  override def visitPrintStatement(node: PrintStatement, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    visitStatement(node, startEdges)
  }

  override def visitIfStatement(node: IfStatement, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    val programPoint = ProgramPoint(node)

    val (ifStartEdges, ifEdges) = visitStatements(node.ifStatements, Set((programPoint, node.condition)))
    val (elseStartEdges, elseEdges) = visitStatements(node.elseStatements, Set((programPoint, node.condition.negate)))

    (
      ifStartEdges ++ elseStartEdges,
      Set((startEdges, programPoint)) ++ ifEdges ++ elseEdges
    )
  }

  override def visitWhileStatement(node: WhileStatement, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    val programPoint = ProgramPoint(node)

    val (whileStartEdges, whileEdges) = visitStatements(node.statements, Set((programPoint, node.condition)))

    (
      Set((programPoint, node.condition.negate)),
      Set((startEdges, programPoint), (whileStartEdges, programPoint)) ++ whileEdges
    )
  }

  override def visitArrayAccess(node: ArrayAccess, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    val programPoint = ProgramPoint(StatementAdapter(node))
    (
      Set((programPoint, BooleanConstant(true))),
      Set((startEdges, programPoint))
    )
  }

  override def visitArrayAssign(node: ArrayAssign, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    val programPoint = ProgramPoint(StatementAdapter(node))
    (
      Set((programPoint, BooleanConstant(true))),
      Set((startEdges, programPoint))
    )
  }

  override def visitBooleanBinaryOperation(node: BooleanBinaryOperation, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les opérations binaires sur les booléens
    (startEdges, Set.empty)
  }

  override def visitBooleanConstant(node: BooleanConstant, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Les constantes booléennes ne modifient pas le CFG
    (startEdges, Set.empty)
  }

  override def visitBooleanEqualComparisonOperation(node: BooleanEqualComparisonOperation, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les comparaisons d'égalité booléennes
    (startEdges, Set.empty)
  }

  override def visitBooleanUnaryOperation(node: BooleanUnaryOperation, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les opérations unaires sur les booléens
    (startEdges, Set.empty)
  }

  override def visitBooleanVariable(node: BooleanVariable, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les variables booléennes
    (startEdges, Set.empty)
  }

  override def visitIntegerBinaryOperation(node: IntegerBinaryOperation, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les opérations binaires sur les entiers
    (startEdges, Set.empty)
  }

  override def visitIntegerComparisonOperation(node: IntegerComparisonOperation, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les comparaisons d'entiers
    (startEdges, Set.empty)
  }

  override def visitIntegerConstant(node: IntegerConstant, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Les constantes entières ne modifient pas le CFG
    (startEdges, Set.empty)
  }

  override def visitIntegerEqualComparisonOperation(node: IntegerEqualComparisonOperation, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les comparaisons d'égalité d'entiers
    (startEdges, Set.empty)
  }

  override def visitIntegerUnaryOperation(node: IntegerUnaryOperation, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les opérations unaires sur les entiers
    (startEdges, Set.empty)
  }

  override def visitIntegerVariable(node: IntegerVariable, startEdges: CfgStartEdges): (CfgStartEdges, CfgEdges) = {
    // Traitez les variables entières
    (startEdges, Set.empty)
  }



}
