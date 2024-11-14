package be.unamur.info.infom227.analysis

import be.unamur.info.infom227.ast.{ExampleAstBuilder, ExampleProgram}
import be.unamur.info.infom227.cfg.{ExampleCfgBuilder, ExampleProgramPoint}
import be.unamur.info.infom227.cst.ExampleParser
import be.unamur.info.infom227.cfg.FromLineNumber.pp
import org.antlr.v4.runtime.CharStreams
import org.scalatest.funsuite.AnyFunSuite

import scala.util.{Failure, Success}

class TestExampleZeroAnalysis extends AnyFunSuite {

  test("simple zero analysis") {
    val code =
      """
    int x;
    int y;
    int z;
    x = 5;
    y = 2;
    z = x / y;
    """

    val charStream = CharStreams.fromString(code)

    val tryAst = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    val (cfg, expected) = tryAst match
      case Failure(exception) => fail(exception)
      case Success(program) => (
        ExampleCfgBuilder.build(program),
        Success(
          Map(
            pp(program, 2) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.Bottom, "y" -> ExampleZeroAnalysisAbstractValue.Bottom, "z" -> ExampleZeroAnalysisAbstractValue.Bottom)),
            pp(program, 3) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.Z, "y" -> ExampleZeroAnalysisAbstractValue.Bottom, "z" -> ExampleZeroAnalysisAbstractValue.Bottom)),
            pp(program, 4) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.Z, "y" -> ExampleZeroAnalysisAbstractValue.Z, "z" -> ExampleZeroAnalysisAbstractValue.Bottom)),
            pp(program, 5) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.Z, "y" -> ExampleZeroAnalysisAbstractValue.Z, "z" -> ExampleZeroAnalysisAbstractValue.Z)),
            pp(program, 6) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.NZ, "y" -> ExampleZeroAnalysisAbstractValue.Z, "z" -> ExampleZeroAnalysisAbstractValue.Z)),
            pp(program, 7) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.NZ, "y" -> ExampleZeroAnalysisAbstractValue.NZ, "z" -> ExampleZeroAnalysisAbstractValue.Z)),
          )
        )
      )

    val result = ExampleZeroAnalysisWorklist.worklist(cfg)

    assert(expected === result)
  }

  test("simple zero analysis with if") {
    val code =
      """
    int x;
    int y;
    int z;
    x = 3;
    if (y > 5) {
      y = x + y;
    } else {
      y = 0;
    }
    z = y;
    """

    val charStream = CharStreams.fromString(code)

    val tryAst = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    val (cfg, expected) = tryAst match
      case Failure(exception) => fail(exception)
      case Success(program) => (
        ExampleCfgBuilder.build(program),
        Success(
          Map(
            pp(program, 2) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.Bottom, "y" -> ExampleZeroAnalysisAbstractValue.Bottom, "z" -> ExampleZeroAnalysisAbstractValue.Bottom)),
            pp(program, 3) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.Z, "y" -> ExampleZeroAnalysisAbstractValue.Bottom, "z" -> ExampleZeroAnalysisAbstractValue.Bottom)),
            pp(program, 4) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.Z, "y" -> ExampleZeroAnalysisAbstractValue.Z, "z" -> ExampleZeroAnalysisAbstractValue.Bottom)),
            pp(program, 5) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.Z, "y" -> ExampleZeroAnalysisAbstractValue.Z, "z" -> ExampleZeroAnalysisAbstractValue.Z)),
            pp(program, 6) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.NZ, "y" -> ExampleZeroAnalysisAbstractValue.Z, "z" -> ExampleZeroAnalysisAbstractValue.Z)),
            pp(program, 7) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.NZ, "y" -> ExampleZeroAnalysisAbstractValue.NZ, "z" -> ExampleZeroAnalysisAbstractValue.Z)),
            pp(program, 9) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.NZ, "y" -> ExampleZeroAnalysisAbstractValue.U, "z" -> ExampleZeroAnalysisAbstractValue.Z)),
            pp(program, 11) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("x" -> ExampleZeroAnalysisAbstractValue.NZ, "y" -> ExampleZeroAnalysisAbstractValue.U, "z" -> ExampleZeroAnalysisAbstractValue.Z)),
          )
        )
      )

    val result = ExampleZeroAnalysisWorklist.worklist(cfg)

    assert(expected === result)
  }

  test("simple zero analysis with while") {
    val code =
      """
    int i;
    int sum;
    while (i < 5) {
      sum = sum + i;
      i = i + 1;
    }
    print sum;
    """

    val charStream = CharStreams.fromString(code)

    val tryAst = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    val (cfg, expected) = tryAst match
      case Failure(exception) => fail(exception)
      case Success(program) => (
        ExampleCfgBuilder.build(program),
        Success(
          Map(
            pp(program, 2) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("i" -> ExampleZeroAnalysisAbstractValue.Bottom, "sum" -> ExampleZeroAnalysisAbstractValue.Bottom)),
            pp(program, 3) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("i" -> ExampleZeroAnalysisAbstractValue.Z, "sum" -> ExampleZeroAnalysisAbstractValue.Bottom)),
            pp(program, 4) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("i" -> ExampleZeroAnalysisAbstractValue.U, "sum" -> ExampleZeroAnalysisAbstractValue.U)),
            pp(program, 5) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("i" -> ExampleZeroAnalysisAbstractValue.U, "sum" -> ExampleZeroAnalysisAbstractValue.U)),
            pp(program, 6) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("i" -> ExampleZeroAnalysisAbstractValue.U, "sum" -> ExampleZeroAnalysisAbstractValue.U)),
            pp(program, 8) -> ExampleAbstractEnvironment(ExampleZeroAnalysisAbstractValue.lattice, None, Map("i" -> ExampleZeroAnalysisAbstractValue.NZ, "sum" -> ExampleZeroAnalysisAbstractValue.U)),
          )
        )
      )

    val result = ExampleZeroAnalysisWorklist.worklist(cfg)

    assert(expected === result)
  }

  test("simple zero analysis results interpretation without error") {
    val code =
      """
    int x;
    int y;
    int z;
    x = 5;
    y = 2;
    z = x / y;
    """

    val expected = Success(List.empty)

    val charStream = CharStreams.fromString(code)

    val tryMessages = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
      cfg = ExampleCfgBuilder.build(ast)
      results <- ExampleZeroAnalysisWorklist.worklist(cfg)
      messages <- ExampleZeroAnalysisResultsInterpreter.interpret(cfg, results)
    } yield messages

    assert(expected === tryMessages)
  }

  test("simple zero analysis results interpretation with warning") {
    val code =
      """
    int x;
    int y;
    int z;
    x = 5;
    y = x - x;
    z = x / y;
    """

    val expected = Success(
      List(
        (7, ExampleErrorMessageType.Warning, "Possible division by zero !")
      )
    )

    val charStream = CharStreams.fromString(code)

    val tryMessages = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
      cfg = ExampleCfgBuilder.build(ast)
      results <- ExampleZeroAnalysisWorklist.worklist(cfg)
      messages <- ExampleZeroAnalysisResultsInterpreter.interpret(cfg, results)
    } yield messages

    assert(expected === tryMessages)
  }

  test("simple zero analysis results interpretation with error") {
    val code =
      """
    int x;
    int y;
    int z;
    x = 5;
    y = 0;
    z = x / y;
    """

    val expected = Success(
      List(
        (7, ExampleErrorMessageType.Error, "Division by zero detected !")
      )
    )

    val charStream = CharStreams.fromString(code)

    val tryMessages = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
      cfg = ExampleCfgBuilder.build(ast)
      results <- ExampleZeroAnalysisWorklist.worklist(cfg)
      messages <- ExampleZeroAnalysisResultsInterpreter.interpret(cfg, results)
    } yield messages

    assert(expected === tryMessages)
  }
}
