/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.undo.UndoableEditSupport;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    private final Set<String> assignedEdge = new HashSet<>();
    
    // Abstraction function:
    //   represent a graph by set of vertices and edges
    // Representation invariant:
    //   there should not have two same vertex and two same edge
    // Safety from rep exposure:
    //   prevent direct access vertices and edges
    
    // constructor
    public ConcreteEdgesGraph() {
		
	}
    // checkRep
    private void checkRep() {
    		// check there are two same edge or not
    		assert edges.size() == assignedEdge.size();
    }
    
    @Override public boolean add(String vertex) {
    		if (vertices.contains(vertex))
    			return false;
        vertices.add(vertex);
        checkRep();
        return true;
    }
    
    @Override public int set(String source, String target, int weight) {
        String key = source + "=>" + target;
        Edge edge;
        
        // if source or target not in graph
        if (!vertices.contains(source)
        		|| !vertices.contains(target)) {
        		throw new VertextNotExistException(source + " or " + target + "not in graph!");
        }
     // weight must be equal or greater than zero
        if (weight < 0) {
        		throw new NegativeWeightException("Weight must be equal or greater than 0");
        }
        if (assignedEdge.contains(key)) {
        		for (Edge e: edges) {
        			if (e.getSource().equals(source)
        					&& e.getTarget().equals(target)) {
        				// remove edge
        				if (weight == 0) {
        					assignedEdge.remove(key);
        					edges.remove(e);
        				} else {
        					// update weight
        					edges.remove(e);
        					edges.add(new Edge(source, target, weight));
        				}
        				checkRep();
        				return e.getWeight();
        			}
        		}
        }
        	assignedEdge.add(key);
        	edge = new Edge(source, target, weight);
        	edges.add(edge);
        	checkRep();
        	return 0;
    }
    
    @Override public boolean remove(String vertex) {
        	return vertices.remove(vertex);
    }
    
    @Override public Set<String> vertices() {
        return new HashSet<>(vertices);
    }
    
    @Override public Map<String, Integer> sources(String target) {
	    	HashMap<String, Integer> sources = new HashMap<String, Integer>();
			
		for (Edge edge : edges) {
			if (edge.getTarget().equals(target)) {
				sources.put(edge.getSource(), edge.getWeight());
			}
		}
		checkRep();
	    return sources;
    }
    
    @Override public Map<String, Integer> targets(String source) {
    		HashMap<String, Integer> targets = new HashMap<String, Integer>();
    		
    		for (Edge edge : edges) {
    			if (edge.getSource().equals(source)) {
    				targets.put(edge.getTarget(), edge.getWeight());
    			}
    		}
    		checkRep();
        return targets;
    }
    
    @Override
    public String toString() {
    		String stringRep = "Vertices:\n";
    		
    		for (String vertex: vertices) {
    			stringRep += (vertex + ", ");
    		}
    		stringRep += "\nEdges:\n";
    		for (Edge edge: edges) {
    			stringRep += (edge.toString() + ", ");
    		}
    		stringRep += "\n";
	    	return stringRep;
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
    
    private String source;
    private String target;
    private int weight;
    // Abstraction function:
    //   reprecent an edge
    // Representation invariant:
    //   weight should not below or equal 0
    // Safety from rep exposure:
    //   can not change weight directly
    
    // constructor
    public Edge(String s, String t, int w) {
		source = s;
		target = t;
		weight = w;
		checkRep();
	}
    
    // checkRep
    private void checkRep() {
    		assert weight > 0;
    }
    
    // methods
    public String getSource() {
    		return source;
    }
    public String getTarget() {
		return target;
    }
    public int getWeight() {
		return weight;
    }
    
    @Override
    public String toString() {
    		return source + " => " + target + " (" + weight + ")";
    }
}
