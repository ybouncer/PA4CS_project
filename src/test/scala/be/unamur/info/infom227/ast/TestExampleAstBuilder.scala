package be.unamur.info.infom227.ast

import be.unamur.info.infom227.cst.ExampleParser
import org.antlr.v4.runtime.CharStreams
import org.scalatest.funsuite.AnyFunSuite

import scala.util.Success

class TestExampleAstBuilder extends AnyFunSuite {

  test("build simple example AST with declarations only") {
    val code =
      """
      int a;
      int b;
      """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map(
            "a" -> ExampleInt,
            "b" -> ExampleInt
          ),
          ExampleDeclareStatement(2, "a", ExampleInt),
          ExampleDeclareStatement(3, "b", ExampleInt)
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }

  test("build simple example AST with two array declarations") {
    val code =
      """
      int array1{2};
      int array2{5};
      """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map(
            "array1" -> ExampleArray(baseType = ExampleInt, size = Some(2)),
            "array2" -> ExampleArray(baseType = ExampleInt, size = Some(5))
          ),
          ExampleDeclareStatement(2, "array1", ExampleArray(baseType = ExampleInt, size = Some(2))),
          ExampleDeclareStatement(3, "array2", ExampleArray(baseType = ExampleInt, size = Some(5)))
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }


  test("build simple example AST") {
    val code =
      """
    int a;
    int b;
    a = 3 * 4 / 5;
    b = -a + b - 7;
    """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map(("a", ExampleInt), ("b", ExampleInt)),
          ExampleDeclareStatement(2, "a", ExampleInt),
          ExampleDeclareStatement(3, "b", ExampleInt),
          ExampleAssignStatement(4, "a", ExampleScope(Map.empty), ExampleIntegerBinaryOperation(
            ExampleIntegerBinaryOperator.Div,
            ExampleIntegerBinaryOperation(
              ExampleIntegerBinaryOperator.Mul,
              ExampleIntegerConstant(3),
              ExampleIntegerConstant(4)
            ),
            ExampleIntegerConstant(5)
          )),
          ExampleAssignStatement(5, "b", ExampleScope(Map.empty), ExampleIntegerBinaryOperation(
            ExampleIntegerBinaryOperator.Sub,
            ExampleIntegerBinaryOperation(
              ExampleIntegerBinaryOperator.Add,
              ExampleIntegerUnaryOperation(
                ExampleIntegerUnaryOperator.Neg,
                ExampleIntegerVariable("a")
              ),
              ExampleIntegerVariable("b")
            ),
            ExampleIntegerConstant(7)
          )),
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }

  test("build simple example AST with scope") {
    val code =
      """
    int a;
    a = {
      int b;
      b = -a + b - 7;
      3 * 4 / b
    };
    """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map(("a", ExampleInt)),
          ExampleDeclareStatement(2, "a", ExampleInt),
          ExampleAssignStatement(
            3,
            "a",
            ExampleScope(
              Map(("b", ExampleInt)),
              ExampleDeclareStatement(4, "b", ExampleInt),
              ExampleAssignStatement(5, "b", ExampleScope(Map.empty), ExampleIntegerBinaryOperation(
                ExampleIntegerBinaryOperator.Sub,
                ExampleIntegerBinaryOperation(
                  ExampleIntegerBinaryOperator.Add,
                  ExampleIntegerUnaryOperation(
                    ExampleIntegerUnaryOperator.Neg,
                    ExampleIntegerVariable("a")
                  ),
                  ExampleIntegerVariable("b")
                ),
                ExampleIntegerConstant(7)
              )),
            ),
            ExampleIntegerBinaryOperation(
              ExampleIntegerBinaryOperator.Div,
              ExampleIntegerBinaryOperation(
                ExampleIntegerBinaryOperator.Mul,
                ExampleIntegerConstant(3),
                ExampleIntegerConstant(4)
              ),
              ExampleIntegerVariable("b")
            )
          ),
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }

  test("build simple example AST with operator priority") {
    val code =
      """
    int a;
    a = 2*2 + 3*3;
    """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map(("a", ExampleInt)),
          ExampleDeclareStatement(2, "a", ExampleInt),
          ExampleAssignStatement(3, "a", ExampleScope(Map.empty), ExampleIntegerBinaryOperation(
            ExampleIntegerBinaryOperator.Add,
            ExampleIntegerBinaryOperation(
              ExampleIntegerBinaryOperator.Mul,
              ExampleIntegerConstant(2),
              ExampleIntegerConstant(2)
            ),
            ExampleIntegerBinaryOperation(
              ExampleIntegerBinaryOperator.Mul,
              ExampleIntegerConstant(3),
              ExampleIntegerConstant(3)
            ),
          ))
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }

  test("build simple example AST with a comparison") {
    val code =
      """
    int a;
    bool b;
    a = 1 + 2;
    b = not (a == 5);
    """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map(("a", ExampleInt), ("b", ExampleBool)),
          ExampleDeclareStatement(2, "a", ExampleInt),
          ExampleDeclareStatement(3, "b", ExampleBool),
          ExampleAssignStatement(4, "a", ExampleScope(Map.empty), ExampleIntegerBinaryOperation(
            ExampleIntegerBinaryOperator.Add,
            ExampleIntegerConstant(1),
            ExampleIntegerConstant(2)
          )),
          ExampleAssignStatement(5, "b", ExampleScope(Map.empty), ExampleBooleanUnaryOperation(
            ExampleBooleanUnaryOperator.Neg,
            ExampleIntegerEqualComparisonOperation(
              ExampleEqualComparisonOperator.Eq,
              ExampleIntegerVariable("a"),
              ExampleIntegerConstant(5)
            )
          )),
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }

  test("build simple example AST with a if statement") {
    val code =
      """
    if (True) {
      print 1;
    } else {
      print 2;
    }
    """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map.empty,
          ExampleIfStatement(
            2,
            ExampleBooleanConstant(true),
            ExampleStatements(
              ExamplePrintStatement(
                3,
                ExampleIntegerConstant(1)
              )
            ),
            ExampleStatements(
              ExamplePrintStatement(
                5,
                ExampleIntegerConstant(2)
              )
            )
          )
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }

  test("build simple example AST with a while statement") {
    val code =
      """
    while (True) {
      print 1;
    }
    """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map.empty,
          ExampleWhileStatement(
            2,
            ExampleBooleanConstant(true),
            ExampleStatements(
              ExamplePrintStatement(
                3,
                ExampleIntegerConstant(1)
              )
            ),
          )
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }

  test("fail to build a simple example AST because of an undefined variable") {
    val code =
      """
    int a;
    a = b * 2;
    """

    val charStream = CharStreams.fromString(code)

    val ast = for {
      tree <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(tree)
    } yield ast

    assert(ast.isFailure)
  }

  test("fail to build a simple example AST because of an assignment from scope") {
    val code =
      """
    int a;
    int b;
    a = {
      b = 2;
      4
    };
    """

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(ast.isFailure)
  }

  test("fail to build a simple example AST because of an access to a variable from a scope outside the scope") {
    val code =
      """
    int a;
    a = {
      int b;
      b = 2;
      4
    };
    a = a + b;
    """

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(ast.isFailure)
  }

  test("fail to build a simple example AST because of an assignment of a boolean expression to an integer variable") {
    val code =
      """
    int a;
    a = True and False;
    """

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(ast.isFailure)
  }

  test("fail to build a simple example AST because the negation of a boolean variable") {
    val code =
      """
    int a;
    a = - True;
    """

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(ast.isFailure)
  }

  test("fail to build a simple example AST because of a boolean in a integer expression") {
    val code =
      """
    int a;
    a = 5 + True;
    """

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(ast.isFailure)
  }

  test("fail to build a simple example AST because of a integer in a boolean expression") {
    val code =
      """
    int a;
    a = 5 and True;
    """

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(ast.isFailure)
  }

  test("build example AST with array declaration and assignments") {
    val code =
      """
      int array{3};
      array{0} = 1;
      array{1} = 2;
      array{2} = 3;
      """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map(
            "array" -> ExampleArray(baseType = ExampleInt, size = Some(3))
          ),
          ExampleDeclareStatement(2, "array", ExampleArray(baseType = ExampleInt, size = Some(3))),
          ExampleAssignStatement(
            3,
            "array",
            ExampleScope(Map.empty),
            ExampleArrayAssign("array", ExampleIntegerConstant(0), ExampleIntegerConstant(1))
          ),
          ExampleAssignStatement(
            4,
            "array",
            ExampleScope(Map.empty),
            ExampleArrayAssign("array", ExampleIntegerConstant(1), ExampleIntegerConstant(2))
          ),
          ExampleAssignStatement(
            5,
            "array",
            ExampleScope(Map.empty),
            ExampleArrayAssign("array", ExampleIntegerConstant(2), ExampleIntegerConstant(3))
          )
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }

  test("build example AST with array access and print statement") {
    val code =
      """
      int array{3};
      int a;
      a = 1;
      array{0} = 1;
      array{1} = 2;
      array{2} = 3;
      print(array{a});
      """

    val expected = Success(
      ExampleProgram(
        ExampleScope(
          Map(
            "array" -> ExampleArray(ExampleInt, Some(3)),
            "a" -> ExampleInt
          ),
          ExampleDeclareStatement(2, "array", ExampleArray(ExampleInt, Some(3))),
          ExampleDeclareStatement(3, "a", ExampleInt),
          ExampleAssignStatement(4, "a", ExampleScope(Map.empty), ExampleIntegerConstant(1)),
          ExampleAssignStatement(5, "array", ExampleScope(Map.empty), ExampleArrayAssign("array", ExampleIntegerConstant(0), ExampleIntegerConstant(1))),
          ExampleAssignStatement(6, "array", ExampleScope(Map.empty), ExampleArrayAssign("array", ExampleIntegerConstant(1), ExampleIntegerConstant(2))),
          ExampleAssignStatement(7, "array", ExampleScope(Map.empty), ExampleArrayAssign("array", ExampleIntegerConstant(2), ExampleIntegerConstant(3))),
          ExamplePrintStatement(8, ExampleArrayAccess("array", ExampleIntegerVariable("a")))
        )
      )
    )

    val charStream = CharStreams.fromString(code)

    val ast = for {
      cst <- ExampleParser.parse(charStream)
      ast <- ExampleAstBuilder.build(cst)
    } yield ast

    assert(expected == ast)
  }




}
