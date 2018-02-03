/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

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
    
    @Test
    public void testSimplify() {
    		Map<String, Double> environment = new HashMap<>();
    		String result;
		
    		environment.put("x", 5.);
    		environment.put("y", 10.);
    		environment.put("z", 20.);
    		result = Commands.simplify("x*x*x", environment);
		assertEquals(result, "125.0");
		environment.clear();
		environment.put("y", 10.);
		result = Commands.simplify("x*x*x + y*y*y", environment);
		assertEquals(result, "x*x*x + 1000.0");
		environment.clear();
		result = Commands.simplify("1+2*3+8*0.5", environment);
		assertEquals(result, "11.0");
    }
    
}
