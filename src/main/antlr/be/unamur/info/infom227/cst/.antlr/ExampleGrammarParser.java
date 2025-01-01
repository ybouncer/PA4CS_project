// Generated from /Users/ryad/Documents/GitHub/INFOM227_ExampleAnalyser/src/main/antlr/be/unamur/info/infom227/cst/ExampleGrammar.g4 by ANTLR 4.13.1
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
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			scope();
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
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INT || _la==BOOL) {
				{
				{
				setState(40);
				declareStatement();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
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
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(48);
					statement();
					}
					} 
				}
				setState(53);
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
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				assignStatement();
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				printStatement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				ifStatement();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(57);
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
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(ExampleGrammarParser.IDENTIFIER, 0); }
		public TerminalNode SEMICOLON() { return getToken(ExampleGrammarParser.SEMICOLON, 0); }
		public TerminalNode LBRACE() { return getToken(ExampleGrammarParser.LBRACE, 0); }
		public TerminalNode NUMBER() { return getToken(ExampleGrammarParser.NUMBER, 0); }
		public TerminalNode RBRACE() { return getToken(ExampleGrammarParser.RBRACE, 0); }
		public DeclareStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declareStatement; }
	}

	public final DeclareStatementContext declareStatement() throws RecognitionException {
		DeclareStatementContext _localctx = new DeclareStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_declareStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			type();
			setState(61);
			match(IDENTIFIER);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(62);
				match(LBRACE);
				setState(63);
				match(NUMBER);
				setState(64);
				match(RBRACE);
				}
			}

			setState(67);
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
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(IDENTIFIER);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(70);
				match(LBRACE);
				setState(71);
				expression();
				setState(72);
				match(RBRACE);
				}
			}

			setState(76);
			match(ASSIGN);
			setState(83);
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
				setState(77);
				expression();
				}
				break;
			case LBRACE:
				{
				setState(78);
				match(LBRACE);
				setState(79);
				scope();
				setState(80);
				expression();
				setState(81);
				match(RBRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(85);
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
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_printStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(PRINT);
			setState(88);
			expression();
			setState(89);
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
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(IF);
			setState(92);
			match(LPAR);
			setState(93);
			expression();
			setState(94);
			match(RPAR);
			setState(95);
			match(LBRACE);
			setState(96);
			((IfStatementContext)_localctx).if_ = statements();
			setState(97);
			match(RBRACE);
			setState(98);
			match(ELSE);
			setState(99);
			match(LBRACE);
			setState(100);
			((IfStatementContext)_localctx).else_ = statements();
			setState(101);
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
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(WHILE);
			setState(104);
			match(LPAR);
			setState(105);
			expression();
			setState(106);
			match(RPAR);
			setState(107);
			match(LBRACE);
			setState(108);
			statements();
			setState(109);
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
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type);
		try {
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				match(BOOL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				match(INT);
				setState(114);
				match(LBRACE);
				setState(115);
				match(RBRACE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(116);
				match(BOOL);
				setState(117);
				match(LBRACE);
				setState(118);
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
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
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
	}

	public final DisjunctionContext disjunction() throws RecognitionException {
		DisjunctionContext _localctx = new DisjunctionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_disjunction);
		int _la;
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				conjunction();
				setState(126); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(124);
					match(OR);
					setState(125);
					conjunction();
					}
					}
					setState(128); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OR );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
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
	}

	public final ConjunctionContext conjunction() throws RecognitionException {
		ConjunctionContext _localctx = new ConjunctionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_conjunction);
		int _la;
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				inversion();
				setState(136); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(134);
					match(AND);
					setState(135);
					inversion();
					}
					}
					setState(138); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AND );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
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
	}

	public final InversionContext inversion() throws RecognitionException {
		InversionContext _localctx = new InversionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_inversion);
		try {
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				match(NOT);
				setState(144);
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
				setState(145);
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
			setState(149);
			((ComparisonContext)_localctx).passthrough = sum(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(156);
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
					setState(151);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(152);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 66060288L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(153);
					((ComparisonContext)_localctx).right = sum(0);
					}
					} 
				}
				setState(158);
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
			setState(160);
			((SumContext)_localctx).passthrough = product(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(167);
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
					setState(162);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(163);
					_la = _input.LA(1);
					if ( !(_la==ADD || _la==SUBTRACT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(164);
					((SumContext)_localctx).right = product(0);
					}
					} 
				}
				setState(169);
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
			setState(171);
			((ProductContext)_localctx).passthrough = factor();
			}
			_ctx.stop = _input.LT(-1);
			setState(178);
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
					setState(173);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(174);
					_la = _input.LA(1);
					if ( !(_la==MULTIPLY || _la==DIVIDE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(175);
					((ProductContext)_localctx).right = factor();
					}
					} 
				}
				setState(180);
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
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_factor);
		int _la;
		try {
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case SUBTRACT:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUBTRACT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(182);
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
				setState(183);
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
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_atom);
		try {
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				match(IDENTIFIER);
				setState(191);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(187);
					match(LBRACE);
					setState(188);
					expression();
					setState(189);
					match(RBRACE);
					}
					break;
				}
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				match(NUMBER);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
				match(FALSE);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(196);
				match(LPAR);
				setState(197);
				expression();
				setState(198);
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
		"\u0004\u0001 \u00cb\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0005\u0001*\b\u0001\n\u0001\f\u0001"+
		"-\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0005\u00022\b\u0002\n\u0002"+
		"\f\u00025\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003;\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004B\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005K\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005T\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\tx\b\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0004\u000b\u007f\b\u000b\u000b\u000b\f\u000b\u0080\u0001"+
		"\u000b\u0003\u000b\u0084\b\u000b\u0001\f\u0001\f\u0001\f\u0004\f\u0089"+
		"\b\f\u000b\f\f\f\u008a\u0001\f\u0003\f\u008e\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u0093\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0005\u000e\u009b\b\u000e\n\u000e\f\u000e\u009e\t\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0005\u000f\u00a6\b\u000f\n\u000f\f\u000f\u00a9\t\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00b1"+
		"\b\u0010\n\u0010\f\u0010\u00b4\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u00b9\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0003\u0012\u00c0\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00c9\b\u0012"+
		"\u0001\u0012\u0000\u0003\u001c\u001e \u0013\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$\u0000\u0003"+
		"\u0001\u0000\u0014\u0019\u0001\u0000\u0010\u0011\u0001\u0000\u0012\u0013"+
		"\u00d0\u0000&\u0001\u0000\u0000\u0000\u0002+\u0001\u0000\u0000\u0000\u0004"+
		"3\u0001\u0000\u0000\u0000\u0006:\u0001\u0000\u0000\u0000\b<\u0001\u0000"+
		"\u0000\u0000\nE\u0001\u0000\u0000\u0000\fW\u0001\u0000\u0000\u0000\u000e"+
		"[\u0001\u0000\u0000\u0000\u0010g\u0001\u0000\u0000\u0000\u0012w\u0001"+
		"\u0000\u0000\u0000\u0014y\u0001\u0000\u0000\u0000\u0016\u0083\u0001\u0000"+
		"\u0000\u0000\u0018\u008d\u0001\u0000\u0000\u0000\u001a\u0092\u0001\u0000"+
		"\u0000\u0000\u001c\u0094\u0001\u0000\u0000\u0000\u001e\u009f\u0001\u0000"+
		"\u0000\u0000 \u00aa\u0001\u0000\u0000\u0000\"\u00b8\u0001\u0000\u0000"+
		"\u0000$\u00c8\u0001\u0000\u0000\u0000&\'\u0003\u0002\u0001\u0000\'\u0001"+
		"\u0001\u0000\u0000\u0000(*\u0003\b\u0004\u0000)(\u0001\u0000\u0000\u0000"+
		"*-\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001\u0000\u0000"+
		"\u0000,.\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000./\u0003\u0004"+
		"\u0002\u0000/\u0003\u0001\u0000\u0000\u000002\u0003\u0006\u0003\u0000"+
		"10\u0001\u0000\u0000\u000025\u0001\u0000\u0000\u000031\u0001\u0000\u0000"+
		"\u000034\u0001\u0000\u0000\u00004\u0005\u0001\u0000\u0000\u000053\u0001"+
		"\u0000\u0000\u00006;\u0003\n\u0005\u00007;\u0003\f\u0006\u00008;\u0003"+
		"\u000e\u0007\u00009;\u0003\u0010\b\u0000:6\u0001\u0000\u0000\u0000:7\u0001"+
		"\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:9\u0001\u0000\u0000\u0000"+
		";\u0007\u0001\u0000\u0000\u0000<=\u0003\u0012\t\u0000=A\u0005\u001d\u0000"+
		"\u0000>?\u0005\u0007\u0000\u0000?@\u0005\u001c\u0000\u0000@B\u0005\b\u0000"+
		"\u0000A>\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0001\u0000"+
		"\u0000\u0000CD\u0005\u000b\u0000\u0000D\t\u0001\u0000\u0000\u0000EJ\u0005"+
		"\u001d\u0000\u0000FG\u0005\u0007\u0000\u0000GH\u0003\u0014\n\u0000HI\u0005"+
		"\b\u0000\u0000IK\u0001\u0000\u0000\u0000JF\u0001\u0000\u0000\u0000JK\u0001"+
		"\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LS\u0005\f\u0000\u0000MT\u0003"+
		"\u0014\n\u0000NO\u0005\u0007\u0000\u0000OP\u0003\u0002\u0001\u0000PQ\u0003"+
		"\u0014\n\u0000QR\u0005\b\u0000\u0000RT\u0001\u0000\u0000\u0000SM\u0001"+
		"\u0000\u0000\u0000SN\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000"+
		"UV\u0005\u000b\u0000\u0000V\u000b\u0001\u0000\u0000\u0000WX\u0005\u0003"+
		"\u0000\u0000XY\u0003\u0014\n\u0000YZ\u0005\u000b\u0000\u0000Z\r\u0001"+
		"\u0000\u0000\u0000[\\\u0005\u0004\u0000\u0000\\]\u0005\t\u0000\u0000]"+
		"^\u0003\u0014\n\u0000^_\u0005\n\u0000\u0000_`\u0005\u0007\u0000\u0000"+
		"`a\u0003\u0004\u0002\u0000ab\u0005\b\u0000\u0000bc\u0005\u0005\u0000\u0000"+
		"cd\u0005\u0007\u0000\u0000de\u0003\u0004\u0002\u0000ef\u0005\b\u0000\u0000"+
		"f\u000f\u0001\u0000\u0000\u0000gh\u0005\u0006\u0000\u0000hi\u0005\t\u0000"+
		"\u0000ij\u0003\u0014\n\u0000jk\u0005\n\u0000\u0000kl\u0005\u0007\u0000"+
		"\u0000lm\u0003\u0004\u0002\u0000mn\u0005\b\u0000\u0000n\u0011\u0001\u0000"+
		"\u0000\u0000ox\u0005\u0001\u0000\u0000px\u0005\u0002\u0000\u0000qr\u0005"+
		"\u0001\u0000\u0000rs\u0005\u0007\u0000\u0000sx\u0005\b\u0000\u0000tu\u0005"+
		"\u0002\u0000\u0000uv\u0005\u0007\u0000\u0000vx\u0005\b\u0000\u0000wo\u0001"+
		"\u0000\u0000\u0000wp\u0001\u0000\u0000\u0000wq\u0001\u0000\u0000\u0000"+
		"wt\u0001\u0000\u0000\u0000x\u0013\u0001\u0000\u0000\u0000yz\u0003\u0016"+
		"\u000b\u0000z\u0015\u0001\u0000\u0000\u0000{~\u0003\u0018\f\u0000|}\u0005"+
		"\u000f\u0000\u0000}\u007f\u0003\u0018\f\u0000~|\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0001\u0000\u0000\u0000\u0081\u0084\u0001\u0000\u0000\u0000\u0082"+
		"\u0084\u0003\u0018\f\u0000\u0083{\u0001\u0000\u0000\u0000\u0083\u0082"+
		"\u0001\u0000\u0000\u0000\u0084\u0017\u0001\u0000\u0000\u0000\u0085\u0088"+
		"\u0003\u001a\r\u0000\u0086\u0087\u0005\u000e\u0000\u0000\u0087\u0089\u0003"+
		"\u001a\r\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000"+
		"\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000"+
		"\u0000\u0000\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008e\u0003\u001a"+
		"\r\u0000\u008d\u0085\u0001\u0000\u0000\u0000\u008d\u008c\u0001\u0000\u0000"+
		"\u0000\u008e\u0019\u0001\u0000\u0000\u0000\u008f\u0090\u0005\r\u0000\u0000"+
		"\u0090\u0093\u0003\u001a\r\u0000\u0091\u0093\u0003\u001c\u000e\u0000\u0092"+
		"\u008f\u0001\u0000\u0000\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093"+
		"\u001b\u0001\u0000\u0000\u0000\u0094\u0095\u0006\u000e\uffff\uffff\u0000"+
		"\u0095\u0096\u0003\u001e\u000f\u0000\u0096\u009c\u0001\u0000\u0000\u0000"+
		"\u0097\u0098\n\u0002\u0000\u0000\u0098\u0099\u0007\u0000\u0000\u0000\u0099"+
		"\u009b\u0003\u001e\u000f\u0000\u009a\u0097\u0001\u0000\u0000\u0000\u009b"+
		"\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0001\u0000\u0000\u0000\u009d\u001d\u0001\u0000\u0000\u0000\u009e"+
		"\u009c\u0001\u0000\u0000\u0000\u009f\u00a0\u0006\u000f\uffff\uffff\u0000"+
		"\u00a0\u00a1\u0003 \u0010\u0000\u00a1\u00a7\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a3\n\u0002\u0000\u0000\u00a3\u00a4\u0007\u0001\u0000\u0000\u00a4\u00a6"+
		"\u0003 \u0010\u0000\u00a5\u00a2\u0001\u0000\u0000\u0000\u00a6\u00a9\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a8\u001f\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001"+
		"\u0000\u0000\u0000\u00aa\u00ab\u0006\u0010\uffff\uffff\u0000\u00ab\u00ac"+
		"\u0003\"\u0011\u0000\u00ac\u00b2\u0001\u0000\u0000\u0000\u00ad\u00ae\n"+
		"\u0002\u0000\u0000\u00ae\u00af\u0007\u0002\u0000\u0000\u00af\u00b1\u0003"+
		"\"\u0011\u0000\u00b0\u00ad\u0001\u0000\u0000\u0000\u00b1\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b3!\u0001\u0000\u0000\u0000\u00b4\u00b2\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0007\u0001\u0000\u0000\u00b6\u00b9\u0003$\u0012\u0000"+
		"\u00b7\u00b9\u0003$\u0012\u0000\u00b8\u00b5\u0001\u0000\u0000\u0000\u00b8"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b9#\u0001\u0000\u0000\u0000\u00ba\u00bf"+
		"\u0005\u001d\u0000\u0000\u00bb\u00bc\u0005\u0007\u0000\u0000\u00bc\u00bd"+
		"\u0003\u0014\n\u0000\u00bd\u00be\u0005\b\u0000\u0000\u00be\u00c0\u0001"+
		"\u0000\u0000\u0000\u00bf\u00bb\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c9\u0001\u0000\u0000\u0000\u00c1\u00c9\u0005"+
		"\u001c\u0000\u0000\u00c2\u00c9\u0005\u001a\u0000\u0000\u00c3\u00c9\u0005"+
		"\u001b\u0000\u0000\u00c4\u00c5\u0005\t\u0000\u0000\u00c5\u00c6\u0003\u0014"+
		"\n\u0000\u00c6\u00c7\u0005\n\u0000\u0000\u00c7\u00c9\u0001\u0000\u0000"+
		"\u0000\u00c8\u00ba\u0001\u0000\u0000\u0000\u00c8\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c2\u0001\u0000\u0000\u0000\u00c8\u00c3\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c4\u0001\u0000\u0000\u0000\u00c9%\u0001\u0000\u0000\u0000"+
		"\u0012+3:AJSw\u0080\u0083\u008a\u008d\u0092\u009c\u00a7\u00b2\u00b8\u00bf"+
		"\u00c8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}