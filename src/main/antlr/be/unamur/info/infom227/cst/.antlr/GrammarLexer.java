// Generated from /Users/ryad/Documents/GitHub/INFOM227_ExampleAnalyser/src/main/antlr/be/unamur/info/infom227/cst/Grammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class GrammarLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"INT", "BOOL", "PRINT", "IF", "ELSE", "WHILE", "LBRACE", "RBRACE", "LPAR", 
			"RPAR", "SEMICOLON", "ASSIGN", "NOT", "AND", "OR", "ADD", "SUBTRACT", 
			"MULTIPLY", "DIVIDE", "GREATER", "GREATER_EQUAL", "LESS", "LESS_EQUAL", 
			"EQUAL", "DIFFERENT", "TRUE", "FALSE", "DIGIT", "NUMBER", "LETTER", "IDENTIFIER", 
			"COMMENT", "NEWLINE", "WS"
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000 \u00d9\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0004\u001c\u00a0\b\u001c\u000b\u001c\f\u001c\u00a1\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u00a9"+
		"\b\u001e\n\u001e\f\u001e\u00ac\t\u001e\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0005\u001f\u00b2\b\u001f\n\u001f\f\u001f\u00b5\t\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0005"+
		"\u001f\u00bd\b\u001f\n\u001f\f\u001f\u00c0\t\u001f\u0001\u001f\u0003\u001f"+
		"\u00c3\b\u001f\u0001\u001f\u0003\u001f\u00c6\b\u001f\u0003\u001f\u00c8"+
		"\b\u001f\u0001\u001f\u0001\u001f\u0001 \u0003 \u00cd\b \u0001 \u0001 "+
		"\u0001 \u0001 \u0001!\u0004!\u00d4\b!\u000b!\f!\u00d5\u0001!\u0001!\u0002"+
		"\u00b3\u00be\u0000\"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t"+
		"\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f"+
		"\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014"+
		")\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u00009\u001c;\u0000"+
		"=\u001d?\u001eA\u001fC \u0001\u0000\u0004\u0001\u000009\u0002\u0000AZ"+
		"az\u0001\u0001\n\n\u0002\u0000\t\t  \u00df\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000"+
		"\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C"+
		"\u0001\u0000\u0000\u0000\u0001E\u0001\u0000\u0000\u0000\u0003I\u0001\u0000"+
		"\u0000\u0000\u0005N\u0001\u0000\u0000\u0000\u0007T\u0001\u0000\u0000\u0000"+
		"\tW\u0001\u0000\u0000\u0000\u000b\\\u0001\u0000\u0000\u0000\rb\u0001\u0000"+
		"\u0000\u0000\u000fd\u0001\u0000\u0000\u0000\u0011f\u0001\u0000\u0000\u0000"+
		"\u0013h\u0001\u0000\u0000\u0000\u0015j\u0001\u0000\u0000\u0000\u0017l"+
		"\u0001\u0000\u0000\u0000\u0019n\u0001\u0000\u0000\u0000\u001br\u0001\u0000"+
		"\u0000\u0000\u001dv\u0001\u0000\u0000\u0000\u001fy\u0001\u0000\u0000\u0000"+
		"!{\u0001\u0000\u0000\u0000#}\u0001\u0000\u0000\u0000%\u007f\u0001\u0000"+
		"\u0000\u0000\'\u0081\u0001\u0000\u0000\u0000)\u0083\u0001\u0000\u0000"+
		"\u0000+\u0086\u0001\u0000\u0000\u0000-\u0088\u0001\u0000\u0000\u0000/"+
		"\u008b\u0001\u0000\u0000\u00001\u008e\u0001\u0000\u0000\u00003\u0091\u0001"+
		"\u0000\u0000\u00005\u0096\u0001\u0000\u0000\u00007\u009c\u0001\u0000\u0000"+
		"\u00009\u009f\u0001\u0000\u0000\u0000;\u00a3\u0001\u0000\u0000\u0000="+
		"\u00a5\u0001\u0000\u0000\u0000?\u00c7\u0001\u0000\u0000\u0000A\u00cc\u0001"+
		"\u0000\u0000\u0000C\u00d3\u0001\u0000\u0000\u0000EF\u0005i\u0000\u0000"+
		"FG\u0005n\u0000\u0000GH\u0005t\u0000\u0000H\u0002\u0001\u0000\u0000\u0000"+
		"IJ\u0005b\u0000\u0000JK\u0005o\u0000\u0000KL\u0005o\u0000\u0000LM\u0005"+
		"l\u0000\u0000M\u0004\u0001\u0000\u0000\u0000NO\u0005p\u0000\u0000OP\u0005"+
		"r\u0000\u0000PQ\u0005i\u0000\u0000QR\u0005n\u0000\u0000RS\u0005t\u0000"+
		"\u0000S\u0006\u0001\u0000\u0000\u0000TU\u0005i\u0000\u0000UV\u0005f\u0000"+
		"\u0000V\b\u0001\u0000\u0000\u0000WX\u0005e\u0000\u0000XY\u0005l\u0000"+
		"\u0000YZ\u0005s\u0000\u0000Z[\u0005e\u0000\u0000[\n\u0001\u0000\u0000"+
		"\u0000\\]\u0005w\u0000\u0000]^\u0005h\u0000\u0000^_\u0005i\u0000\u0000"+
		"_`\u0005l\u0000\u0000`a\u0005e\u0000\u0000a\f\u0001\u0000\u0000\u0000"+
		"bc\u0005{\u0000\u0000c\u000e\u0001\u0000\u0000\u0000de\u0005}\u0000\u0000"+
		"e\u0010\u0001\u0000\u0000\u0000fg\u0005(\u0000\u0000g\u0012\u0001\u0000"+
		"\u0000\u0000hi\u0005)\u0000\u0000i\u0014\u0001\u0000\u0000\u0000jk\u0005"+
		";\u0000\u0000k\u0016\u0001\u0000\u0000\u0000lm\u0005=\u0000\u0000m\u0018"+
		"\u0001\u0000\u0000\u0000no\u0005n\u0000\u0000op\u0005o\u0000\u0000pq\u0005"+
		"t\u0000\u0000q\u001a\u0001\u0000\u0000\u0000rs\u0005a\u0000\u0000st\u0005"+
		"n\u0000\u0000tu\u0005d\u0000\u0000u\u001c\u0001\u0000\u0000\u0000vw\u0005"+
		"o\u0000\u0000wx\u0005r\u0000\u0000x\u001e\u0001\u0000\u0000\u0000yz\u0005"+
		"+\u0000\u0000z \u0001\u0000\u0000\u0000{|\u0005-\u0000\u0000|\"\u0001"+
		"\u0000\u0000\u0000}~\u0005*\u0000\u0000~$\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0005/\u0000\u0000\u0080&\u0001\u0000\u0000\u0000\u0081\u0082\u0005"+
		">\u0000\u0000\u0082(\u0001\u0000\u0000\u0000\u0083\u0084\u0005>\u0000"+
		"\u0000\u0084\u0085\u0005=\u0000\u0000\u0085*\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0005<\u0000\u0000\u0087,\u0001\u0000\u0000\u0000\u0088\u0089\u0005"+
		"<\u0000\u0000\u0089\u008a\u0005=\u0000\u0000\u008a.\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0005=\u0000\u0000\u008c\u008d\u0005=\u0000\u0000\u008d"+
		"0\u0001\u0000\u0000\u0000\u008e\u008f\u0005!\u0000\u0000\u008f\u0090\u0005"+
		"=\u0000\u0000\u00902\u0001\u0000\u0000\u0000\u0091\u0092\u0005T\u0000"+
		"\u0000\u0092\u0093\u0005r\u0000\u0000\u0093\u0094\u0005u\u0000\u0000\u0094"+
		"\u0095\u0005e\u0000\u0000\u00954\u0001\u0000\u0000\u0000\u0096\u0097\u0005"+
		"F\u0000\u0000\u0097\u0098\u0005a\u0000\u0000\u0098\u0099\u0005l\u0000"+
		"\u0000\u0099\u009a\u0005s\u0000\u0000\u009a\u009b\u0005e\u0000\u0000\u009b"+
		"6\u0001\u0000\u0000\u0000\u009c\u009d\u0007\u0000\u0000\u0000\u009d8\u0001"+
		"\u0000\u0000\u0000\u009e\u00a0\u00037\u001b\u0000\u009f\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2:\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0007\u0001\u0000\u0000\u00a4<\u0001\u0000\u0000\u0000"+
		"\u00a5\u00aa\u0003;\u001d\u0000\u00a6\u00a9\u0003;\u001d\u0000\u00a7\u00a9"+
		"\u00037\u001b\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a7\u0001"+
		"\u0000\u0000\u0000\u00a9\u00ac\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001"+
		"\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab>\u0001\u0000"+
		"\u0000\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005/\u0000"+
		"\u0000\u00ae\u00af\u0005*\u0000\u0000\u00af\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b2\t\u0000\u0000\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b5\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b4\u00b6\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005*\u0000\u0000\u00b7\u00c8"+
		"\u0005/\u0000\u0000\u00b8\u00b9\u0005/\u0000\u0000\u00b9\u00ba\u0005/"+
		"\u0000\u0000\u00ba\u00be\u0001\u0000\u0000\u0000\u00bb\u00bd\t\u0000\u0000"+
		"\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bf\u00c2\u0001\u0000\u0000\u0000\u00c0\u00be\u0001\u0000\u0000"+
		"\u0000\u00c1\u00c3\u0005\r\u0000\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c6\u0007\u0002\u0000\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c8\u0001\u0000\u0000\u0000\u00c7\u00ad\u0001\u0000\u0000\u0000"+
		"\u00c7\u00b8\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000"+
		"\u00c9\u00ca\u0006\u001f\u0000\u0000\u00ca@\u0001\u0000\u0000\u0000\u00cb"+
		"\u00cd\u0005\r\u0000\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000\u00cc\u00cd"+
		"\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0005\n\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00d1\u0006"+
		" \u0000\u0000\u00d1B\u0001\u0000\u0000\u0000\u00d2\u00d4\u0007\u0003\u0000"+
		"\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d8\u0006!\u0000\u0000"+
		"\u00d8D\u0001\u0000\u0000\u0000\u000b\u0000\u00a1\u00a8\u00aa\u00b3\u00be"+
		"\u00c2\u00c5\u00c7\u00cc\u00d5\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}