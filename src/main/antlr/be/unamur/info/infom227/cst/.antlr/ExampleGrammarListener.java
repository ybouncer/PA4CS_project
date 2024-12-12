// Generated from c:/Users/yboun/Desktop/Cyber Security/PA4CS_project/src/main/antlr/be/unamur/info/infom227/cst/ExampleGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExampleGrammarParser}.
 */
public interface ExampleGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ExampleGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ExampleGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(ExampleGrammarParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(ExampleGrammarParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(ExampleGrammarParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(ExampleGrammarParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ExampleGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ExampleGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#declareStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeclareStatement(ExampleGrammarParser.DeclareStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#declareStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeclareStatement(ExampleGrammarParser.DeclareStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(ExampleGrammarParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(ExampleGrammarParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(ExampleGrammarParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(ExampleGrammarParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(ExampleGrammarParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(ExampleGrammarParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(ExampleGrammarParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(ExampleGrammarParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ExampleGrammarParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ExampleGrammarParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ExampleGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ExampleGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void enterDisjunction(ExampleGrammarParser.DisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#disjunction}.
	 * @param ctx the parse tree
	 */
	void exitDisjunction(ExampleGrammarParser.DisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(ExampleGrammarParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(ExampleGrammarParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#inversion}.
	 * @param ctx the parse tree
	 */
	void enterInversion(ExampleGrammarParser.InversionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#inversion}.
	 * @param ctx the parse tree
	 */
	void exitInversion(ExampleGrammarParser.InversionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(ExampleGrammarParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(ExampleGrammarParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#sum}.
	 * @param ctx the parse tree
	 */
	void enterSum(ExampleGrammarParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#sum}.
	 * @param ctx the parse tree
	 */
	void exitSum(ExampleGrammarParser.SumContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#product}.
	 * @param ctx the parse tree
	 */
	void enterProduct(ExampleGrammarParser.ProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#product}.
	 * @param ctx the parse tree
	 */
	void exitProduct(ExampleGrammarParser.ProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(ExampleGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(ExampleGrammarParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExampleGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(ExampleGrammarParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(ExampleGrammarParser.AtomContext ctx);
}