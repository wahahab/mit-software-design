/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testExpression() {
    		Expression expression = Expression.parse("x * x * x");
    		
    		assertEquals(expression.toString(),
    				"x*x*x");
    		expression = Expression.parse("x *y");
    		assertEquals(expression.toString(),
    				"x*y");
    		expression = Expression.parse("x + x * x * y + x");
    		assertEquals(expression.toString(),
    				"x + x*x*y + x");
    		assertTrue(Expression.parse("x + 1").equals(Expression.parse("x + 1.00000")));
    		assertTrue(Expression.parse("(1)+(x)").equals(Expression.parse(" 1+x")));
    		assertTrue(Expression.parse(" 1+x").equals(Expression.parse("(1+x)")));
    }
    
    @Test(expected=ParseError.class)
    public void testExpressionParseError() {
    		Expression.parse("3xy");
    }
    
}
