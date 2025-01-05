package be.unamur.info.infom227

import be.unamur.info.infom227.analysis.{SignAnalysisResultsInterpreter, SignAnalysisWorklist}
import be.unamur.info.infom227.ast.{CannotBuildAstException, AstBuilder}
import be.unamur.info.infom227.cfg.CfgBuilder
import be.unamur.info.infom227.cst.Parser
import be.unamur.info.infom227.interpreter.Interpreter
import org.antlr.v4.runtime.CharStreams

import scala.util.{Try, Success, Failure}

@main def main(action: String, file: String): Unit = {
  action match {
    /*
    case "run" =>
      val tryResult = for {
        charStream <- Try(CharStreams.fromFileName(file))
        cst <- Parser.parse(charStream)
        ast <- AstBuilder.build(cst)
        result <- Interpreter.run(ast)
      } yield result

      tryResult.recover {
        case exception: CannotBuildAstException => println(s"Compilation Error:\n${exception.getMessage}")
        case exception: Throwable => println(s"Fatal error:\n${exception.getMessage}")
      }*/
    case "sign-analysis" =>
      val startTime = System.nanoTime()

      val tryMessages = for {
        charStream <- Try(CharStreams.fromFileName(file))
        cst <- Parser.parse(charStream)
        ast <- AstBuilder.build(cst)
        //_ = println(s"Abstract Syntax Tree (AST):\n$ast") // Affichage de l'AST
        cfg = CfgBuilder.build(ast)
        //_ = println(s"Control Flow Graph (CFG):\n$cfg") // Affichage du CFG
        results <- SignAnalysisWorklist.worklist(cfg)
        //_ = println(s"Worklist Results:\n$results") // Affichage des résultats de la Worklist
        messages <- SignAnalysisResultsInterpreter.interpret(cfg, results)
      } yield messages

      val endTime = System.nanoTime()
      val elapsedTime = (endTime - startTime) / 1_000_000 // Convert to milliseconds
      println(s"Analysis completed in $elapsedTime ms")


      tryMessages match
        case Success(messages) =>
          for ((lineNumber, messageType, message) <- messages) {
            println(s"[$messageType] Line $lineNumber : $message")
          }
        case Failure(exception: CannotBuildAstException) => println(s"Compilation Error:\n${exception.getMessage}")
        case Failure(exception: Throwable) => println(s"${exception.getMessage}")
    case action => println(f"Unknown action: $action")
  }
}
