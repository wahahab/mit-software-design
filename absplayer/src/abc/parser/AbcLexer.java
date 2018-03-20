// Generated from Abc.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AbcLexer extends Lexer {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
    T__9=10, T__10=11, SMALL_M=12, BAR=13, LEFT_C=14, RIGHT_C=15, COLON=16, 
    SMALL_Z=17, SMALL_B=18, BIG_C=19, FLAT=20, UPPER=21, KEY=22, LOWER_KEY=23, 
    EQUAL=24, COMMA=25, SINGLE_Q=26, NUMBER=27, SPACE=28, DOT=29, SLASH=30, 
    NEWLINE=31, ALPHA=32;
  public static String[] modeNames = {
    "DEFAULT_MODE"
  };

  public static final String[] ruleNames = {
    "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
    "T__9", "T__10", "SMALL_M", "BAR", "LEFT_C", "RIGHT_C", "COLON", "SMALL_Z", 
    "SMALL_B", "BIG_C", "FLAT", "UPPER", "KEY", "LOWER_KEY", "EQUAL", "COMMA", 
    "SINGLE_Q", "NUMBER", "SPACE", "DOT", "SLASH", "NEWLINE", "ALPHA"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'X:'", "'T:'", "'K:'", "'M:'", "'L:'", "'Q:'", "'C:'", "'V:'", 
    "'#'", "'%'", "'('", "'m'", "'|'", "'['", "']'", "':'", "'z'", "'b'", 
    "'C'", "'_'", "'^'", null, null, "'='", "','", "'''", null, "' '", "'.'", 
    "'/'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, null, null, null, null, null, null, null, 
    "SMALL_M", "BAR", "LEFT_C", "RIGHT_C", "COLON", "SMALL_Z", "SMALL_B", 
    "BIG_C", "FLAT", "UPPER", "KEY", "LOWER_KEY", "EQUAL", "COMMA", "SINGLE_Q", 
    "NUMBER", "SPACE", "DOT", "SLASH", "NEWLINE", "ALPHA"
  };
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


      // This method makes the parser stop running if it encounters
      // invalid input and throw a RuntimeException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol, 
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }


  public AbcLexer(CharStream input) {
    super(input);
    _interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }

  @Override
  public String getGrammarFileName() { return "Abc.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public String[] getModeNames() { return modeNames; }

  @Override
  public ATN getATN() { return _ATN; }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\"\u0093\b\1\4\2"+
      "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
      "\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
      "\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4"+
      "\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t"+
      "\36\4\37\t\37\4 \t \4!\t!\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5"+
      "\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
      "\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3"+
      "\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3"+
      "\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\6\34\u0081\n\34\r\34\16\34"+
      "\u0082\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \5 \u0090\n"+
      " \3!\3!\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
      "\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
      "\65\34\67\359\36;\37= ?!A\"\3\2\6\4\2CDFI\4\2ccei\3\2\62;\4\2C\\c"+
      "|\u0094\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
      "\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
      "\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2"+
      "\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
      "\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2"+
      "\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
      "\3C\3\2\2\2\5F\3\2\2\2\7I\3\2\2\2\tL\3\2\2\2\13O\3\2\2\2\rR\3\2\2"+
      "\2\17U\3\2\2\2\21X\3\2\2\2\23[\3\2\2\2\25]\3\2\2\2\27_\3\2\2\2\31"+
      "a\3\2\2\2\33c\3\2\2\2\35e\3\2\2\2\37g\3\2\2\2!i\3\2\2\2#k\3\2\2\2"+
      "%m\3\2\2\2\'o\3\2\2\2)q\3\2\2\2+s\3\2\2\2-u\3\2\2\2/w\3\2\2\2\61y"+
      "\3\2\2\2\63{\3\2\2\2\65}\3\2\2\2\67\u0080\3\2\2\29\u0084\3\2\2\2;"+
      "\u0088\3\2\2\2=\u008a\3\2\2\2?\u008f\3\2\2\2A\u0091\3\2\2\2CD\7Z\2"+
      "\2DE\7<\2\2E\4\3\2\2\2FG\7V\2\2GH\7<\2\2H\6\3\2\2\2IJ\7M\2\2JK\7<"+
      "\2\2K\b\3\2\2\2LM\7O\2\2MN\7<\2\2N\n\3\2\2\2OP\7N\2\2PQ\7<\2\2Q\f"+
      "\3\2\2\2RS\7S\2\2ST\7<\2\2T\16\3\2\2\2UV\7E\2\2VW\7<\2\2W\20\3\2\2"+
      "\2XY\7X\2\2YZ\7<\2\2Z\22\3\2\2\2[\\\7%\2\2\\\24\3\2\2\2]^\7\'\2\2"+
      "^\26\3\2\2\2_`\7*\2\2`\30\3\2\2\2ab\7o\2\2b\32\3\2\2\2cd\7~\2\2d\34"+
      "\3\2\2\2ef\7]\2\2f\36\3\2\2\2gh\7_\2\2h \3\2\2\2ij\7<\2\2j\"\3\2\2"+
      "\2kl\7|\2\2l$\3\2\2\2mn\7d\2\2n&\3\2\2\2op\7E\2\2p(\3\2\2\2qr\7a\2"+
      "\2r*\3\2\2\2st\7`\2\2t,\3\2\2\2uv\t\2\2\2v.\3\2\2\2wx\t\3\2\2x\60"+
      "\3\2\2\2yz\7?\2\2z\62\3\2\2\2{|\7.\2\2|\64\3\2\2\2}~\7)\2\2~\66\3"+
      "\2\2\2\177\u0081\t\4\2\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
      "\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u00838\3\2\2\2\u0084\u0085\7\""+
      "\2\2\u0085\u0086\3\2\2\2\u0086\u0087\b\35\2\2\u0087:\3\2\2\2\u0088"+
      "\u0089\7\60\2\2\u0089<\3\2\2\2\u008a\u008b\7\61\2\2\u008b>\3\2\2\2"+
      "\u008c\u0090\7\f\2\2\u008d\u008e\7\17\2\2\u008e\u0090\7\f\2\2\u008f"+
      "\u008c\3\2\2\2\u008f\u008d\3\2\2\2\u0090@\3\2\2\2\u0091\u0092\t\5"+
      "\2\2\u0092B\3\2\2\2\5\2\u0082\u008f\3\b\2\2";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}