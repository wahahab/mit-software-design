/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.Map;
import java.util.Stack;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import expressivo.parser.ExpressionParser.ExpressionContext;
import expressivo.parser.ExpressionParser.GroupContext;
import expressivo.parser.ExpressionParser.P1Context;
import expressivo.parser.ExpressionParser.RootContext;
import expressivo.parser.ExpressionParser.ValueContext;
import expressivo.parser.ExpressionParser.VariableContext;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //   TODO
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
    		Stack<Expression> expressions = new Stack<>();
        org.antlr.v4.runtime.CharStream stream =  new ANTLRInputStream(input);
        ExpressionLexer lexer = new ExpressionLexer(stream);
        org.antlr.v4.runtime.TokenStream tokenStream = new org.antlr.v4.runtime.CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokenStream);
        org.antlr.v4.runtime.tree.ParseTree tree;
        parser.reportErrorsAsExceptions();
        try {
        		tree = parser.root();
        } catch (ParseCancellationException e) {
        		throw new ParseError(e.getMessage());
        }
        ParseTreeWalker walker = new ParseTreeWalker();
        ExpressionListener listener = new ExpressionBuilder(
        		expressions);
        
//        Trees.inspect(tree, parser);
        walker.walk(listener, tree);
        return expressions.pop();
    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    
    /**
     * @return diffrentiated expression
     */
    public Expression diffrentiate(String variable);
    
    /**
     * @param environment variables' value
     * @return simplified expression
     */
    public Expression simplify(Map<String, Double> environment);
    
}

class ParseError extends RuntimeException {
	public ParseError(String msg) {
		super(msg);
	}
}

class ExpressionBuilder implements ExpressionListener {
	
	Stack<Expression> expressions;
	
	public ExpressionBuilder(Stack<Expression> es) {
		expressions = es;
	}

	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		
	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterRoot(RootContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitRoot(RootContext ctx) {
	}
	@Override
	public void enterExpression(ExpressionContext ctx) {
	}
	@Override
	public void enterGroup(GroupContext ctx) {
	}
	@Override
	public void enterValue(ValueContext ctx) {
	}
	@Override
	public void enterVariable(VariableContext ctx) {	
	}
	
	@Override
	public void exitGroup(GroupContext ctx) {
		expressions.push(new Group(expressions.pop()));
	}
	
	@Override
	public void exitExpression(ExpressionContext ctx) {
		Expression right;
		
		if (ctx.getChildCount() >= 3) {
			right = expressions.pop();
			expressions.push(new Sum(expressions.pop(), right));
		}
	}

	@Override
	public void exitValue(ValueContext ctx) {
		expressions.push(new Value(Double.valueOf(ctx.VALUE().getText())));
	}

	@Override
	public void exitVariable(VariableContext ctx) {
		expressions.push(new Variable(ctx.VARIABLE().getText()));
	}

	@Override
	public void enterP1(P1Context ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitP1(P1Context ctx) {
		String symbol;
		Expression right;
		if (ctx.getChildCount() >= 3) {
			symbol = ctx.getChild(1).getText();
			right = expressions.pop();
			switch(symbol) {
				case "*":
					expressions.push(new Multiply(expressions.pop(), right));
					return;
			}
		}
	}
	
}
