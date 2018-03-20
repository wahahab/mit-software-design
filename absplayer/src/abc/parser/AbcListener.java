// Generated from Abc.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AbcParser}.
 */
public interface AbcListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link AbcParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(AbcParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(AbcParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#headers}.
   * @param ctx the parse tree
   */
  void enterHeaders(AbcParser.HeadersContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#headers}.
   * @param ctx the parse tree
   */
  void exitHeaders(AbcParser.HeadersContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#indexHeader}.
   * @param ctx the parse tree
   */
  void enterIndexHeader(AbcParser.IndexHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#indexHeader}.
   * @param ctx the parse tree
   */
  void exitIndexHeader(AbcParser.IndexHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#titleHeader}.
   * @param ctx the parse tree
   */
  void enterTitleHeader(AbcParser.TitleHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#titleHeader}.
   * @param ctx the parse tree
   */
  void exitTitleHeader(AbcParser.TitleHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#keyHeader}.
   * @param ctx the parse tree
   */
  void enterKeyHeader(AbcParser.KeyHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#keyHeader}.
   * @param ctx the parse tree
   */
  void exitKeyHeader(AbcParser.KeyHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#otherHeader}.
   * @param ctx the parse tree
   */
  void enterOtherHeader(AbcParser.OtherHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#otherHeader}.
   * @param ctx the parse tree
   */
  void exitOtherHeader(AbcParser.OtherHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#meterHeader}.
   * @param ctx the parse tree
   */
  void enterMeterHeader(AbcParser.MeterHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#meterHeader}.
   * @param ctx the parse tree
   */
  void exitMeterHeader(AbcParser.MeterHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#lengthHeader}.
   * @param ctx the parse tree
   */
  void enterLengthHeader(AbcParser.LengthHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#lengthHeader}.
   * @param ctx the parse tree
   */
  void exitLengthHeader(AbcParser.LengthHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#tempoHeader}.
   * @param ctx the parse tree
   */
  void enterTempoHeader(AbcParser.TempoHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#tempoHeader}.
   * @param ctx the parse tree
   */
  void exitTempoHeader(AbcParser.TempoHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#composerHeader}.
   * @param ctx the parse tree
   */
  void enterComposerHeader(AbcParser.ComposerHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#composerHeader}.
   * @param ctx the parse tree
   */
  void exitComposerHeader(AbcParser.ComposerHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#voiceHeader}.
   * @param ctx the parse tree
   */
  void enterVoiceHeader(AbcParser.VoiceHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#voiceHeader}.
   * @param ctx the parse tree
   */
  void exitVoiceHeader(AbcParser.VoiceHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#body}.
   * @param ctx the parse tree
   */
  void enterBody(AbcParser.BodyContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#body}.
   * @param ctx the parse tree
   */
  void exitBody(AbcParser.BodyContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#sharp}.
   * @param ctx the parse tree
   */
  void enterSharp(AbcParser.SharpContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#sharp}.
   * @param ctx the parse tree
   */
  void exitSharp(AbcParser.SharpContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#comment}.
   * @param ctx the parse tree
   */
  void enterComment(AbcParser.CommentContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#comment}.
   * @param ctx the parse tree
   */
  void exitComment(AbcParser.CommentContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#repeat_start}.
   * @param ctx the parse tree
   */
  void enterRepeat_start(AbcParser.Repeat_startContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#repeat_start}.
   * @param ctx the parse tree
   */
  void exitRepeat_start(AbcParser.Repeat_startContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#repeat_end}.
   * @param ctx the parse tree
   */
  void enterRepeat_end(AbcParser.Repeat_endContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#repeat_end}.
   * @param ctx the parse tree
   */
  void exitRepeat_end(AbcParser.Repeat_endContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#ending}.
   * @param ctx the parse tree
   */
  void enterEnding(AbcParser.EndingContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#ending}.
   * @param ctx the parse tree
   */
  void exitEnding(AbcParser.EndingContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#section}.
   * @param ctx the parse tree
   */
  void enterSection(AbcParser.SectionContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#section}.
   * @param ctx the parse tree
   */
  void exitSection(AbcParser.SectionContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#chord}.
   * @param ctx the parse tree
   */
  void enterChord(AbcParser.ChordContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#chord}.
   * @param ctx the parse tree
   */
  void exitChord(AbcParser.ChordContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#tuplet}.
   * @param ctx the parse tree
   */
  void enterTuplet(AbcParser.TupletContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#tuplet}.
   * @param ctx the parse tree
   */
  void exitTuplet(AbcParser.TupletContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#note}.
   * @param ctx the parse tree
   */
  void enterNote(AbcParser.NoteContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#note}.
   * @param ctx the parse tree
   */
  void exitNote(AbcParser.NoteContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#rest}.
   * @param ctx the parse tree
   */
  void enterRest(AbcParser.RestContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#rest}.
   * @param ctx the parse tree
   */
  void exitRest(AbcParser.RestContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#note_length}.
   * @param ctx the parse tree
   */
  void enterNote_length(AbcParser.Note_lengthContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#note_length}.
   * @param ctx the parse tree
   */
  void exitNote_length(AbcParser.Note_lengthContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#accidentals}.
   * @param ctx the parse tree
   */
  void enterAccidentals(AbcParser.AccidentalsContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#accidentals}.
   * @param ctx the parse tree
   */
  void exitAccidentals(AbcParser.AccidentalsContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#end_bar}.
   * @param ctx the parse tree
   */
  void enterEnd_bar(AbcParser.End_barContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#end_bar}.
   * @param ctx the parse tree
   */
  void exitEnd_bar(AbcParser.End_barContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#fraction}.
   * @param ctx the parse tree
   */
  void enterFraction(AbcParser.FractionContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#fraction}.
   * @param ctx the parse tree
   */
  void exitFraction(AbcParser.FractionContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#lower_key}.
   * @param ctx the parse tree
   */
  void enterLower_key(AbcParser.Lower_keyContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#lower_key}.
   * @param ctx the parse tree
   */
  void exitLower_key(AbcParser.Lower_keyContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#key}.
   * @param ctx the parse tree
   */
  void enterKey(AbcParser.KeyContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#key}.
   * @param ctx the parse tree
   */
  void exitKey(AbcParser.KeyContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#name}.
   * @param ctx the parse tree
   */
  void enterName(AbcParser.NameContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#name}.
   * @param ctx the parse tree
   */
  void exitName(AbcParser.NameContext ctx);
}