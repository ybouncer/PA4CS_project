// Generated from c:/Users/yboun/Desktop/Cyber Security/PA4CS_project/src/main/antlr/be/unamur/info/infom227/cst/ExampleGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExampleGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, BOOL=2, PRINT=3, IF=4, ELSE=5, WHILE=6, LBRACE=7, RBRACE=8, LPAR=9, 
		RPAR=10, SEMICOLON=11, ASSIGN=12, NOT=13, AND=14, OR=15, ADD=16, SUBTRACT=17, 
		MULTIPLY=18, DIVIDE=19, GREATER=20, GREATER_EQUAL=21, LESS=22, LESS_EQUAL=23, 
		EQUAL=24, DIFFERENT=25, TRUE=26, FALSE=27, NUMBER=28, IDENTIFIER=29, COMMENT=30, 
		NEWLINE=31, WS=32;
	public static final int
		RULE_program = 0, RULE_scope = 1, RULE_statements = 2, RULE_statement = 3, 
		RULE_declareStatement = 4, RULE_assignStatement = 5, RULE_printStatement = 6, 
		RULE_ifStatement = 7, RULE_whileStatement = 8, RULE_type = 9, RULE_expression = 10, 
		RULE_disjunction = 11, RULE_conjunction = 12, RULE_inversion = 13, RULE_comparison = 14, 
		RULE_sum = 15, RULE_product = 16, RULE_factor = 17, RULE_atom = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "scope", "statements", "statement", "declareStatement", "assignStatement", 
			"printStatement", "ifStatement", "whileStatement", "type", "expression", 
			"disjunction", "conjunction", "inversion", "comparison", "sum", "product", 
			"factor", "atom"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'bool'", "'print'", "'if'", "'else'", "'while'", "'{'", 
			"'}'", "'('", "')'", "';'", "'='", "'not'", "'and'", "'or'", "'+'", "'-'", 
			"'*'", "'/'", "'>'", "'>='", "'<'", "'<='", "'=='", "'!='", "'True'", 
			"'False'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "BOOL", "PRINT", "IF", "ELSE", "WHILE", "LBRACE", "RBRACE", 
			"LPAR", "RPAR", "SEMICOLON", "ASSIGN", "NOT", "AND", "OR", "ADD", "SUBTRACT", 
			"MULTIPLY", "DIVIDE", "GREATER", "GREATER_EQUAL", "LESS", "LESS_EQUAL", 
			"EQUAL", "DIFFERENT", "TRUE", "FALSE", "NUMBER", "IDENTIFIER", "COMMENT", 
			"NEWLINE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ExampleGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExampleGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ExampleGrammarParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			scope();
			setState(39);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScopeContext extends ParserRuleContext {
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public List<DeclareStatementContext> declareStatement() {
			return getRuleContexts(DeclareStatementContext.class);
		}
		public DeclareStatementContext declareStatement(int i) {
			return getRuleContext(DeclareStatementContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitScope(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INT || _la==BOOL) {
				{
				{
				setState(41);
				declareStatement();
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
			statements();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitStatements(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(49);
					statement();
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				assignStatement();
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				printStatement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				ifStatement();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				whileStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclareStatementContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(ExampleGrammarParser.SEMICOLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ExampleGrammarParser.IDENTIFIER, 0); }
		public TerminalNode LBRACE() { return getToken(ExampleGrammarParser.LBRACE, 0); }
		public TerminalNode NUMBER() { return getToken(ExampleGrammarParser.NUMBER, 0); }
		public TerminalNode RBRACE() { return getToken(ExampleGrammarParser.RBRACE, 0); }
		public DeclareStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declareStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterDeclareStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitDeclareStatement(this);
		}
	}

	public final DeclareStatementContext declareStatement() throws RecognitionException {
		DeclareStatementContext _localctx = new DeclareStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_declareStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(61);
			type();
			setState(62);
			match(IDENTIFIER);
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(63);
				match(LBRACE);
				setState(64);
				match(NUMBER);
				setState(65);
				match(RBRACE);
				}
			}

			}
			setState(68);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignStatementContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ExampleGrammarParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(ExampleGrammarParser.ASSIGN, 0); }
		public TerminalNode SEMICOLON() { return getToken(ExampleGrammarParser.SEMICOLON, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> LBRACE() { return getTokens(ExampleGrammarParser.LBRACE); }
		public TerminalNode LBRACE(int i) {
			return getToken(ExampleGrammarParser.LBRACE, i);
		}
		public ScopeContext scope() {
			return getRuleContext(ScopeContext.class,0);
		}
		public List<TerminalNode> RBRACE() { return getTokens(ExampleGrammarParser.RBRACE); }
		public TerminalNode RBRACE(int i) {
			return getToken(ExampleGrammarParser.RBRACE, i);
		}
		public AssignStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitAssignStatement(this);
		}
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(IDENTIFIER);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(71);
				match(LBRACE);
				setState(72);
				expression();
				setState(73);
				match(RBRACE);
				}
			}

			setState(77);
			match(ASSIGN);
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
			case NOT:
			case ADD:
			case SUBTRACT:
			case TRUE:
			case FALSE:
			case NUMBER:
			case IDENTIFIER:
				{
				setState(78);
				expression();
				}
				break;
			case LBRACE:
				{
				setState(79);
				match(LBRACE);
				setState(80);
				scope();
				setState(81);
				expression();
				setState(82);
				match(RBRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(86);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrintStatementContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(ExampleGrammarParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ExampleGrammarParser.SEMICOLON, 0); }
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitPrintStatement(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_printStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(PRINT);
			setState(89);
			expression();
			setState(90);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public StatementsContext if_;
		public StatementsContext else_;
		public TerminalNode IF() { return getToken(ExampleGrammarParser.IF, 0); }
		public TerminalNode LPAR() { return getToken(ExampleGrammarParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(ExampleGrammarParser.RPAR, 0); }
		public List<TerminalNode> LBRACE() { return getTokens(ExampleGrammarParser.LBRACE); }
		public TerminalNode LBRACE(int i) {
			return getToken(ExampleGrammarParser.LBRACE, i);
		}
		public List<TerminalNode> RBRACE() { return getTokens(ExampleGrammarParser.RBRACE); }
		public TerminalNode RBRACE(int i) {
			return getToken(ExampleGrammarParser.RBRACE, i);
		}
		public TerminalNode ELSE() { return getToken(ExampleGrammarParser.ELSE, 0); }
		public List<StatementsContext> statements() {
			return getRuleContexts(StatementsContext.class);
		}
		public StatementsContext statements(int i) {
			return getRuleContext(StatementsContext.class,i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(IF);
			setState(93);
			match(LPAR);
			setState(94);
			expression();
			setState(95);
			match(RPAR);
			setState(96);
			match(LBRACE);
			setState(97);
			((IfStatementContext)_localctx).if_ = statements();
			setState(98);
			match(RBRACE);
			setState(99);
			match(ELSE);
			setState(100);
			match(LBRACE);
			setState(101);
			((IfStatementContext)_localctx).else_ = statements();
			setState(102);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(ExampleGrammarParser.WHILE, 0); }
		public TerminalNode LPAR() { return getToken(ExampleGrammarParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(ExampleGrammarParser.RPAR, 0); }
		public TerminalNode LBRACE() { return getToken(ExampleGrammarParser.LBRACE, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(ExampleGrammarParser.RBRACE, 0); }
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(WHILE);
			setState(105);
			match(LPAR);
			setState(106);
			expression();
			setState(107);
			match(RPAR);
			setState(108);
			match(LBRACE);
			setState(109);
			statements();
			setState(110);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ExampleGrammarParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(ExampleGrammarParser.BOOL, 0); }
		public TerminalNode LBRACE() { return getToken(ExampleGrammarParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ExampleGrammarParser.RBRACE, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(BOOL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114);
				match(INT);
				setState(115);
				match(LBRACE);
				setState(116);
				match(RBRACE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				match(BOOL);
				setState(118);
				match(LBRACE);
				setState(119);
				match(RBRACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public DisjunctionContext disjunction() {
			return getRuleContext(DisjunctionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			disjunction();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DisjunctionContext extends ParserRuleContext {
		public ConjunctionContext passthrough;
		public List<ConjunctionContext> conjunction() {
			return getRuleContexts(ConjunctionContext.class);
		}
		public ConjunctionContext conjunction(int i) {
			return getRuleContext(ConjunctionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(ExampleGrammarParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(ExampleGrammarParser.OR, i);
		}
		public DisjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_disjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterDisjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitDisjunction(this);
		}
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_disjunction);
		int _la;
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				conjunction();
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(125);
					match(OR);
					setState(126);
					conjunction();
					}
					}
					setState(129); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OR );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				((DisjunctionContext)_localctx).passthrough = conjunction();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConjunctionContext extends ParserRuleContext {
		public InversionContext passthrough;
		public List<InversionContext> inversion() {
			return getRuleContexts(InversionContext.class);
		}
		public InversionContext inversion(int i) {
			return getRuleContext(InversionContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(ExampleGrammarParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(ExampleGrammarParser.AND, i);
		}
		public ConjunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conjunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterConjunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitConjunction(this);
		}
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_conjunction);
		int _la;
		try {
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				inversion();
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(135);
					match(AND);
					setState(136);
					inversion();
					}
					}
					setState(139); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AND );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				((ConjunctionContext)_localctx).passthrough = inversion();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InversionContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(ExampleGrammarParser.NOT, 0); }
		public InversionContext inversion() {
			return getRuleContext(InversionContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public InversionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inversion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterInversion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitInversion(this);
		}
	}

	public final InversionContext inversion() throws RecognitionException {
		InversionContext _localctx = new InversionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_inversion);
		try {
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				match(NOT);
				setState(145);
				inversion();
				}
				break;
			case LPAR:
			case ADD:
			case SUBTRACT:
			case TRUE:
			case FALSE:
			case NUMBER:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				comparison(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonContext extends ParserRuleContext {
		public ComparisonContext left;
		public SumContext passthrough;
		public SumContext right;
		public SumContext sum() {
			return getRuleContext(SumContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public TerminalNode LESS() { return getToken(ExampleGrammarParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(ExampleGrammarParser.GREATER, 0); }
		public TerminalNode EQUAL() { return getToken(ExampleGrammarParser.EQUAL, 0); }
		public TerminalNode DIFFERENT() { return getToken(ExampleGrammarParser.DIFFERENT, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(ExampleGrammarParser.GREATER_EQUAL, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(ExampleGrammarParser.LESS_EQUAL, 0); }
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitComparison(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		return comparison(0);
	}

	private ComparisonContext comparison(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ComparisonContext _localctx = new ComparisonContext(_ctx, _parentState);
		ComparisonContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_comparison, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(150);
			((ComparisonContext)_localctx).passthrough = sum(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(157);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ComparisonContext(_parentctx, _parentState);
					_localctx.left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_comparison);
					setState(152);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(153);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 66060288L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(154);
					((ComparisonContext)_localctx).right = sum(0);
					}
					} 
				}
				setState(159);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SumContext extends ParserRuleContext {
		public SumContext left;
		public ProductContext passthrough;
		public ProductContext right;
		public ProductContext product() {
			return getRuleContext(ProductContext.class,0);
		}
		public SumContext sum() {
			return getRuleContext(SumContext.class,0);
		}
		public TerminalNode ADD() { return getToken(ExampleGrammarParser.ADD, 0); }
		public TerminalNode SUBTRACT() { return getToken(ExampleGrammarParser.SUBTRACT, 0); }
		public SumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterSum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitSum(this);
		}
	}

	public final SumContext sum() throws RecognitionException {
		return sum(0);
	}

	private SumContext sum(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SumContext _localctx = new SumContext(_ctx, _parentState);
		SumContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_sum, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(161);
			((SumContext)_localctx).passthrough = product(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(168);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SumContext(_parentctx, _parentState);
					_localctx.left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_sum);
					setState(163);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(164);
					_la = _input.LA(1);
					if ( !(_la==ADD || _la==SUBTRACT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(165);
					((SumContext)_localctx).right = product(0);
					}
					} 
				}
				setState(170);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProductContext extends ParserRuleContext {
		public ProductContext left;
		public FactorContext passthrough;
		public FactorContext right;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public ProductContext product() {
			return getRuleContext(ProductContext.class,0);
		}
		public TerminalNode MULTIPLY() { return getToken(ExampleGrammarParser.MULTIPLY, 0); }
		public TerminalNode DIVIDE() { return getToken(ExampleGrammarParser.DIVIDE, 0); }
		public ProductContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_product; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterProduct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitProduct(this);
		}
	}

	public final ProductContext product() throws RecognitionException {
		return product(0);
	}

	private ProductContext product(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ProductContext _localctx = new ProductContext(_ctx, _parentState);
		ProductContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_product, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(172);
			((ProductContext)_localctx).passthrough = factor();
			}
			_ctx.stop = _input.LT(-1);
			setState(179);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ProductContext(_parentctx, _parentState);
					_localctx.left = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_product);
					setState(174);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(175);
					_la = _input.LA(1);
					if ( !(_la==MULTIPLY || _la==DIVIDE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(176);
					((ProductContext)_localctx).right = factor();
					}
					} 
				}
				setState(181);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode ADD() { return getToken(ExampleGrammarParser.ADD, 0); }
		public TerminalNode SUBTRACT() { return getToken(ExampleGrammarParser.SUBTRACT, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_factor);
		int _la;
		try {
			setState(185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case SUBTRACT:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUBTRACT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(183);
				atom();
				}
				break;
			case LPAR:
			case TRUE:
			case FALSE:
			case NUMBER:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(ExampleGrammarParser.IDENTIFIER, 0); }
		public TerminalNode LBRACE() { return getToken(ExampleGrammarParser.LBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(ExampleGrammarParser.RBRACE, 0); }
		public TerminalNode NUMBER() { return getToken(ExampleGrammarParser.NUMBER, 0); }
		public TerminalNode TRUE() { return getToken(ExampleGrammarParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(ExampleGrammarParser.FALSE, 0); }
		public TerminalNode LPAR() { return getToken(ExampleGrammarParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(ExampleGrammarParser.RPAR, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExampleGrammarListener ) ((ExampleGrammarListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_atom);
		try {
			setState(201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				match(IDENTIFIER);
				setState(192);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(188);
					match(LBRACE);
					setState(189);
					expression();
					setState(190);
					match(RBRACE);
					}
					break;
				}
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				match(NUMBER);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(195);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				match(FALSE);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(197);
				match(LPAR);
				setState(198);
				expression();
				setState(199);
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return comparison_sempred((ComparisonContext)_localctx, predIndex);
		case 15:
			return sum_sempred((SumContext)_localctx, predIndex);
		case 16:
			return product_sempred((ProductContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean comparison_sempred(ComparisonContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean sum_sempred(SumContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean product_sempred(ProductContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001 \u00cc\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001+\b\u0001"+
		"\n\u0001\f\u0001.\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0005\u0002"+
		"3\b\u0002\n\u0002\f\u00026\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003<\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004C\b\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"L\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005U\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\ty\b\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0004\u000b\u0080\b\u000b\u000b\u000b\f"+
		"\u000b\u0081\u0001\u000b\u0003\u000b\u0085\b\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0004\f\u008a\b\f\u000b\f\f\f\u008b\u0001\f\u0003\f\u008f\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0003\r\u0094\b\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u009c\b\u000e\n\u000e"+
		"\f\u000e\u009f\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0005\u000f\u00a7\b\u000f\n\u000f\f\u000f\u00aa"+
		"\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0005\u0010\u00b2\b\u0010\n\u0010\f\u0010\u00b5\t\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u00ba\b\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00c1\b\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u00ca\b\u0012\u0001\u0012\u0000\u0003\u001c\u001e \u0013"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$\u0000\u0003\u0001\u0000\u0014\u0019\u0001\u0000\u0010"+
		"\u0011\u0001\u0000\u0012\u0013\u00d1\u0000&\u0001\u0000\u0000\u0000\u0002"+
		",\u0001\u0000\u0000\u0000\u00044\u0001\u0000\u0000\u0000\u0006;\u0001"+
		"\u0000\u0000\u0000\b=\u0001\u0000\u0000\u0000\nF\u0001\u0000\u0000\u0000"+
		"\fX\u0001\u0000\u0000\u0000\u000e\\\u0001\u0000\u0000\u0000\u0010h\u0001"+
		"\u0000\u0000\u0000\u0012x\u0001\u0000\u0000\u0000\u0014z\u0001\u0000\u0000"+
		"\u0000\u0016\u0084\u0001\u0000\u0000\u0000\u0018\u008e\u0001\u0000\u0000"+
		"\u0000\u001a\u0093\u0001\u0000\u0000\u0000\u001c\u0095\u0001\u0000\u0000"+
		"\u0000\u001e\u00a0\u0001\u0000\u0000\u0000 \u00ab\u0001\u0000\u0000\u0000"+
		"\"\u00b9\u0001\u0000\u0000\u0000$\u00c9\u0001\u0000\u0000\u0000&\'\u0003"+
		"\u0002\u0001\u0000\'(\u0005\u0000\u0000\u0001(\u0001\u0001\u0000\u0000"+
		"\u0000)+\u0003\b\u0004\u0000*)\u0001\u0000\u0000\u0000+.\u0001\u0000\u0000"+
		"\u0000,*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-/\u0001\u0000"+
		"\u0000\u0000.,\u0001\u0000\u0000\u0000/0\u0003\u0004\u0002\u00000\u0003"+
		"\u0001\u0000\u0000\u000013\u0003\u0006\u0003\u000021\u0001\u0000\u0000"+
		"\u000036\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u000045\u0001\u0000"+
		"\u0000\u00005\u0005\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u0000"+
		"7<\u0003\n\u0005\u00008<\u0003\f\u0006\u00009<\u0003\u000e\u0007\u0000"+
		":<\u0003\u0010\b\u0000;7\u0001\u0000\u0000\u0000;8\u0001\u0000\u0000\u0000"+
		";9\u0001\u0000\u0000\u0000;:\u0001\u0000\u0000\u0000<\u0007\u0001\u0000"+
		"\u0000\u0000=>\u0003\u0012\t\u0000>B\u0005\u001d\u0000\u0000?@\u0005\u0007"+
		"\u0000\u0000@A\u0005\u001c\u0000\u0000AC\u0005\b\u0000\u0000B?\u0001\u0000"+
		"\u0000\u0000BC\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0005"+
		"\u000b\u0000\u0000E\t\u0001\u0000\u0000\u0000FK\u0005\u001d\u0000\u0000"+
		"GH\u0005\u0007\u0000\u0000HI\u0003\u0014\n\u0000IJ\u0005\b\u0000\u0000"+
		"JL\u0001\u0000\u0000\u0000KG\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000"+
		"\u0000LM\u0001\u0000\u0000\u0000MT\u0005\f\u0000\u0000NU\u0003\u0014\n"+
		"\u0000OP\u0005\u0007\u0000\u0000PQ\u0003\u0002\u0001\u0000QR\u0003\u0014"+
		"\n\u0000RS\u0005\b\u0000\u0000SU\u0001\u0000\u0000\u0000TN\u0001\u0000"+
		"\u0000\u0000TO\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0005"+
		"\u000b\u0000\u0000W\u000b\u0001\u0000\u0000\u0000XY\u0005\u0003\u0000"+
		"\u0000YZ\u0003\u0014\n\u0000Z[\u0005\u000b\u0000\u0000[\r\u0001\u0000"+
		"\u0000\u0000\\]\u0005\u0004\u0000\u0000]^\u0005\t\u0000\u0000^_\u0003"+
		"\u0014\n\u0000_`\u0005\n\u0000\u0000`a\u0005\u0007\u0000\u0000ab\u0003"+
		"\u0004\u0002\u0000bc\u0005\b\u0000\u0000cd\u0005\u0005\u0000\u0000de\u0005"+
		"\u0007\u0000\u0000ef\u0003\u0004\u0002\u0000fg\u0005\b\u0000\u0000g\u000f"+
		"\u0001\u0000\u0000\u0000hi\u0005\u0006\u0000\u0000ij\u0005\t\u0000\u0000"+
		"jk\u0003\u0014\n\u0000kl\u0005\n\u0000\u0000lm\u0005\u0007\u0000\u0000"+
		"mn\u0003\u0004\u0002\u0000no\u0005\b\u0000\u0000o\u0011\u0001\u0000\u0000"+
		"\u0000py\u0005\u0001\u0000\u0000qy\u0005\u0002\u0000\u0000rs\u0005\u0001"+
		"\u0000\u0000st\u0005\u0007\u0000\u0000ty\u0005\b\u0000\u0000uv\u0005\u0002"+
		"\u0000\u0000vw\u0005\u0007\u0000\u0000wy\u0005\b\u0000\u0000xp\u0001\u0000"+
		"\u0000\u0000xq\u0001\u0000\u0000\u0000xr\u0001\u0000\u0000\u0000xu\u0001"+
		"\u0000\u0000\u0000y\u0013\u0001\u0000\u0000\u0000z{\u0003\u0016\u000b"+
		"\u0000{\u0015\u0001\u0000\u0000\u0000|\u007f\u0003\u0018\f\u0000}~\u0005"+
		"\u000f\u0000\u0000~\u0080\u0003\u0018\f\u0000\u007f}\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000"+
		"\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000"+
		"\u0000\u0083\u0085\u0003\u0018\f\u0000\u0084|\u0001\u0000\u0000\u0000"+
		"\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0017\u0001\u0000\u0000\u0000"+
		"\u0086\u0089\u0003\u001a\r\u0000\u0087\u0088\u0005\u000e\u0000\u0000\u0088"+
		"\u008a\u0003\u001a\r\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008b\u008c"+
		"\u0001\u0000\u0000\u0000\u008c\u008f\u0001\u0000\u0000\u0000\u008d\u008f"+
		"\u0003\u001a\r\u0000\u008e\u0086\u0001\u0000\u0000\u0000\u008e\u008d\u0001"+
		"\u0000\u0000\u0000\u008f\u0019\u0001\u0000\u0000\u0000\u0090\u0091\u0005"+
		"\r\u0000\u0000\u0091\u0094\u0003\u001a\r\u0000\u0092\u0094\u0003\u001c"+
		"\u000e\u0000\u0093\u0090\u0001\u0000\u0000\u0000\u0093\u0092\u0001\u0000"+
		"\u0000\u0000\u0094\u001b\u0001\u0000\u0000\u0000\u0095\u0096\u0006\u000e"+
		"\uffff\uffff\u0000\u0096\u0097\u0003\u001e\u000f\u0000\u0097\u009d\u0001"+
		"\u0000\u0000\u0000\u0098\u0099\n\u0002\u0000\u0000\u0099\u009a\u0007\u0000"+
		"\u0000\u0000\u009a\u009c\u0003\u001e\u000f\u0000\u009b\u0098\u0001\u0000"+
		"\u0000\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u001d\u0001\u0000"+
		"\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a1\u0006\u000f"+
		"\uffff\uffff\u0000\u00a1\u00a2\u0003 \u0010\u0000\u00a2\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a4\n\u0002\u0000\u0000\u00a4\u00a5\u0007\u0001\u0000"+
		"\u0000\u00a5\u00a7\u0003 \u0010\u0000\u00a6\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a7\u00aa\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u001f\u0001\u0000\u0000\u0000"+
		"\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab\u00ac\u0006\u0010\uffff\uffff"+
		"\u0000\u00ac\u00ad\u0003\"\u0011\u0000\u00ad\u00b3\u0001\u0000\u0000\u0000"+
		"\u00ae\u00af\n\u0002\u0000\u0000\u00af\u00b0\u0007\u0002\u0000\u0000\u00b0"+
		"\u00b2\u0003\"\u0011\u0000\u00b1\u00ae\u0001\u0000\u0000\u0000\u00b2\u00b5"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0001\u0000\u0000\u0000\u00b4!\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b7\u0007\u0001\u0000\u0000\u00b7\u00ba\u0003"+
		"$\u0012\u0000\u00b8\u00ba\u0003$\u0012\u0000\u00b9\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000\u00ba#\u0001\u0000\u0000\u0000"+
		"\u00bb\u00c0\u0005\u001d\u0000\u0000\u00bc\u00bd\u0005\u0007\u0000\u0000"+
		"\u00bd\u00be\u0003\u0014\n\u0000\u00be\u00bf\u0005\b\u0000\u0000\u00bf"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c0\u00bc\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c1\u00ca\u0001\u0000\u0000\u0000\u00c2"+
		"\u00ca\u0005\u001c\u0000\u0000\u00c3\u00ca\u0005\u001a\u0000\u0000\u00c4"+
		"\u00ca\u0005\u001b\u0000\u0000\u00c5\u00c6\u0005\t\u0000\u0000\u00c6\u00c7"+
		"\u0003\u0014\n\u0000\u00c7\u00c8\u0005\n\u0000\u0000\u00c8\u00ca\u0001"+
		"\u0000\u0000\u0000\u00c9\u00bb\u0001\u0000\u0000\u0000\u00c9\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c9\u00c3\u0001\u0000\u0000\u0000\u00c9\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c9\u00c5\u0001\u0000\u0000\u0000\u00ca%\u0001\u0000"+
		"\u0000\u0000\u0012,4;BKTx\u0081\u0084\u008b\u008e\u0093\u009d\u00a8\u00b3"+
		"\u00b9\u00c0\u00c9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}