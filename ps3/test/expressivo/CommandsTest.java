/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void testDiffrentiate() {
    		String result = Commands.differentiate("x*x*x", "x");
    		
    		assertEquals(result, "x*x*(1.0) + x*(x*(1.0) + x*(1.0))");
    		result = Commands.differentiate("x*x*x", "y");
    		assertEquals(result, "x*x*(0.0) + x*(x*(0.0) + x*(0.0))");
    		result = Commands.differentiate("3*x*x", "x");
    		assertEquals(result, "3.0*x*(1.0) + x*(3.0*(1.0) + x*(0.0))");
    		result = Commands.differentiate("3*x*y*x", "x");
    		assertEquals("3.0*x*y*(1.0) + x*(3.0*x*(0.0) + y*(3.0*(1.0) + x*(0.0)))", result);
    		result = Commands.differentiate("3.0*x*y*x", "x");
    }
    
}
