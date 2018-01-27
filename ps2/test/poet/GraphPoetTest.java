/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGraphPoet() {
    		GraphPoet graphPoet = null;
    		String generated;
    		
    		try {
    			graphPoet = new GraphPoet(
    					new File("./test/poet/test_poet.txt"));
    		} catch (IOException exception) {
    			fail("Should not throw: " + exception.getMessage());
    		}
    		generated = graphPoet.poem("Test the system.");
    		assertEquals(generated, "Test of the system.");
    		generated = graphPoet.poem("Testing the system.");
    		assertEquals(generated, "Testing the system.");
    }
    
    @Test
    public void testGraphPoetFileNotExist() {
    	try {
			GraphPoet graphPoet = new GraphPoet(new File("test_poet.txt"));
		} catch (IOException exception) {
			return;
		}
    		fail("Should throw IOException and return");
    }
    
}
