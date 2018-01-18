/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    protected static Graph<String> graph;
    protected static Graph<String> completeGraph;
    
    @Before
    public void graphSetUp() {
    		graph = emptyInstance();
    		completeGraph = emptyInstance();
    		completeGraph.add("usa");
    		completeGraph.add("china");
    		completeGraph.add("german");
    		completeGraph.add("franch");
    		completeGraph.add("taiwan");
    		completeGraph.add("japan");
    		completeGraph.set("usa", "german", 10);
    		completeGraph.set("german", "franch", 1);
    		completeGraph.set("franch", "german", 1);
    		completeGraph.set("taiwan", "japan", 2);
    		completeGraph.set("japan", "taiwan", 2);
    		completeGraph.set("taiwan", "china", 3);
    		completeGraph.set("china", "taiwan", 2);
    		completeGraph.set("china", "german", 8);
    		completeGraph.set("german", "china", 7);
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    @Test
    public void testAdd() {
    		// general test
    		graph.add("foobar");
    		graph.add("bazz");
    		assertEquals("expected size of vertices to be 2",
                    graph.vertices().size(), 2);
    		// test add repeat vertex
    		graph = emptyInstance();
    		graph.add("foobar");
    		graph.add("foobar");
    		assertEquals("expected size of vertices to be 1",
                    graph.vertices().size(), 1);
    }
    
    @Test
    public void testRemove() {
    		// general test
    		graph.add("foobar");
    		graph.add("bazz");
    		graph.remove("bazz");
    		assertEquals("expected size of vertices to be 1",
                    graph.vertices().size(), 1);
    		assertTrue("expected contain vertex 'foobar'",
                    graph.vertices().contains("foobar"));
    		// test remove not exist vertex
    		graph = emptyInstance();
    		graph.add("foobar");
    		graph.remove("bazz");
    		assertEquals("expected size of vertices to be 1",
                    graph.vertices().size(), 1);
    		// remove empty vertices
    		graph = emptyInstance();
    		graph.remove("bazz");
    		assertEquals("expected size of vertices to be 1",
                    graph.vertices().size(), 0);
    }
    
    @Test
    public void testVertices() {
    		// general test
    		graph.add("foobar");
		graph.add("bazz");
		assertEquals("expected size of vertices to be 2",
                graph.vertices().size(), 2);
		assertTrue("expected should contain 'bazz' vertex",
                graph.vertices().contains("bazz"));
		// empty vertices
		graph = emptyInstance();
		assertEquals("expected size of vertices to be 0",
                graph.vertices().size(), 0);
    }
    
    @Test
    public void testSource() {
    		// test 0 sources vertex
    		Map<String, Integer> sources = completeGraph.sources("usa");
    		
    		assertEquals("expected size of sources to be 0",
                    sources.size(), 0);
    		// general testing
    		sources = completeGraph.sources("german");
    		assertEquals("expected size of sources to be 3",
                    sources.size(), 3);
    		assertTrue("expected sources contain vertex 'usa'",
                    sources.containsKey("usa"));
    		assertEquals("expected weight of edge from usa to german = 10",
    				(int) sources.get("usa"), 10);
    		assertTrue("expected sources contain vertex 'china'",
                    sources.containsKey("china"));
    		assertEquals("expected weight of edge from china to german = 8",
    				(int) sources.get("china"), 8);
    		// test target not exist in graph
    		sources = completeGraph.sources("foobar");
    		assertTrue("expected sources should be empty",
                    sources.isEmpty());
    }
    
    @Test
    public void testTarget() {
		Map<String, Integer> targets;
		
		// general testing
		targets = completeGraph.targets("german");
		assertEquals(targets.size(), 2);
		assertTrue(targets.containsKey("china"));
		assertTrue(targets.containsKey("franch"));
		assertFalse(targets.containsKey("taiwan"));
		assertEquals((int) targets.get("china"), 7);
		assertEquals((int) targets.get("franch"), 1);
		// test source not exist in graph
		targets = completeGraph.targets("foobar");
		assertTrue(targets.isEmpty());
    }
    
    @Test(expected=Graph.VertextNotExistException.class)
    public void testSetTargetNotInGraph() {
    		// target not in graph
    		completeGraph.set("usa", "bar", 100);
    }
    @Test(expected=Graph.VertextNotExistException.class)
    public void testSetSourceNotInGraph() {
    		// source not in graph
		completeGraph.set("foobar", "usa", 100);
    }
    @Test(expected=Graph.VertextNotExistException.class)
    public void testSetTargetSourceNotInGraph() {
    		// both not in graph
		completeGraph.set("foo", "bar", 100);
    }
    @Test(expected=Graph.NegativeWeightException.class)
    public void testSetNegativeWeight() {
    		completeGraph.set("usa", "german", -100);
    }
    @Test
    public void testSet() {
    		// add new edge
    		int result = completeGraph.set("german", "usa", 9);
    		
    		assertTrue(completeGraph.targets("german")
    				.containsKey("usa"));
    		assertEquals((int) completeGraph.targets("german")
    				.get("usa"), 9);
    		assertEquals(result, 0);
    		// replace edge
    		result = completeGraph.set("german", "usa", 2);
    		assertTrue(completeGraph.targets("german")
    				.containsKey("usa"));
    		assertEquals((int) completeGraph.targets("german")
    				.get("usa"), 2);
    		assertEquals(result, 9);
    		// delete edge
    		result = completeGraph.set("german", "usa", 0);
    		assertFalse(completeGraph.targets("german")
    				.containsKey("usa"));
    		assertEquals(result, 2);
    }
    
    // TODO other tests for instance methods of Graph
    
}
