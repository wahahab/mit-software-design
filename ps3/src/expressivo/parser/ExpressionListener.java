// Generated from Expression.g4 by ANTLR 4.5.1

package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(ExpressionParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(ExpressionParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#expression}.
   * @param ctx the parse tree
   */
  void enterExpression(ExpressionParser.ExpressionContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#expression}.
   * @param ctx the parse tree
   */
  void exitExpression(ExpressionParser.ExpressionContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#value}.
   * @param ctx the parse tree
   */
  void enterValue(ExpressionParser.ValueContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#value}.
   * @param ctx the parse tree
   */
  void exitValue(ExpressionParser.ValueContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#variable}.
   * @param ctx the parse tree
   */
  void enterVariable(ExpressionParser.VariableContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#variable}.
   * @param ctx the parse tree
   */
  void exitVariable(ExpressionParser.VariableContext ctx);
  /**
   * Enter a parse tree produced by {@link ExpressionParser#group}.
   * @param ctx the parse tree
   */
  void enterGroup(ExpressionParser.GroupContext ctx);
  /**
   * Exit a parse tree produced by {@link ExpressionParser#group}.
   * @param ctx the parse tree
   */
  void exitGroup(ExpressionParser.GroupContext ctx);
}