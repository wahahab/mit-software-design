/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
	
	ConcreteEdgesGraph graph;
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
//    
//    @Test
//    public void testToStringEmpty() {
//    		String expectedString = "Vertices:\n\nEdges:\n\n";
//
//    		assertEquals(graph.toString(), "Vertices:\n\nEdges:\n\n");
//    }

//  completeGraph.set("usa", "german", 10);
//	completeGraph.set("german", "franch", 1);
//	completeGraph.set("franch", "german", 1);
//	completeGraph.set("taiwan", "japan", 2);
//	completeGraph.set("japan", "taiwan", 2);
//	completeGraph.set("taiwan", "china", 3);
//	completeGraph.set("china", "taiwan", 2);
//	completeGraph.set("china", "german", 8);
//	completeGraph.set("german", "china", 7);
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    
}
