/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.ConcreteVerticesGraph.TargetRepeatException;
import graph.Vertex.VertexTarget;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
	
	public static class TargetRepeatException extends Exception {
		public TargetRepeatException(String msg) {
			super(msg);
		}
	}
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO constructor
    public ConcreteVerticesGraph() {
		
	}
    void checkRep() {
    		
    }
    
    @Override public boolean add(L vertex) {
        return vertices.add(new Vertex<L>(vertex));
    }
    
    @Override public int set(L source, L target, int weight) {
    		Iterator<VertexTarget> it;
    		VertexTarget<L> vTarget;
    		int prevWeight = 0;
    		Vertex<L> sourceVertex = null;
    		Vertex<L> targetVertex = null;
    	
    		if (weight < 0) {
    			throw new NegativeWeightException("Weight can not be less than 0");
    		}
    		for (Vertex<L> vertex : vertices) {
    			if (vertex.getValue() == source) {
    				sourceVertex = vertex;
    			}
    			if (vertex.getValue() == target) {
    				targetVertex = vertex;
    			}
    		}
    		if (sourceVertex == null || targetVertex == null) {
    			throw new VertextNotExistException(source + " not exist");
    		}
    		it = sourceVertex.getTargets().iterator();
    		while (it.hasNext()) {
    			vTarget = it.next();
    			if (vTarget.getVertex().getValue() == target) {
    				prevWeight = vTarget.getWeight();
    				if (weight == 0) {
    					it.remove();
    				}
    				else {
    					vTarget.setWeight(weight);
    				}
    				return prevWeight;
    			}
    		}
    		try {
    			sourceVertex.addTarget(targetVertex, weight);
    		} catch (TargetRepeatException repeatException) {
    			return prevWeight;
    		}
    		return prevWeight;
    }
    
    @Override public boolean remove(L vertex) {
        Iterator<Vertex<L>> it = vertices.iterator();
        Vertex<L> v;
        
        while (it.hasNext()) {
        		v = it.next();
        		if (v.getValue() == vertex) {
        			it.remove();
        			return true;
        		}
        }
        return false;
    }
    
    @Override public Set<L> vertices() {
    		Set<L> valueVertices = new HashSet<>();
    		
    		for (Vertex<L> v : vertices) {
    			valueVertices.add(v.getValue());
    		}
    		return valueVertices;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> targets = new HashMap<L, Integer>();
        
        for (Vertex<L> v : vertices) {
        		for (VertexTarget<L> vt : v.getTargets()) {
        			if (vt.getVertex().getValue() == target) {
        				targets.put(v.getValue(), vt.getWeight());
        				break;
        			}
        		}
        }
        return targets;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Vertex<L> sVertex = null;
        Map<L, Integer> sourceMap = new HashMap<L, Integer>();
        
        for (Vertex<L> v : vertices) {
        		if (v.getValue() == source) {
        			sVertex = v;
        			break;
        		}
        }
        if (sVertex == null) {
        		return sourceMap;
        }
        for (VertexTarget<L> vt : sVertex.getTargets()) {
        		sourceMap.put(vt.getVertex().getValue(), vt.getWeight());
        }
        return sourceMap;
    }
    
    // TODO toString()
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
	
	static class VertexTarget<L> {
		private Vertex<L> vertex;
		private int weight;
		
		public VertexTarget(Vertex<L> v, int w) {
			vertex = v;
			weight = w;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public Vertex<L> getVertex() {
			return vertex;
		}
	}
    
	private L value;
	private List<VertexTarget> targets;
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    Vertex(L val) {
    		value = val;
    		targets = new ArrayList<>();
    }
    
    void checkRep() {
    		// check there are no repeat vertices in targets
    		assert (new HashSet<>(targets)).size() == targets.size();
    }
    
    void addTarget(Vertex<L> v, int weight) throws TargetRepeatException {
    		for (VertexTarget t : targets) {
    			if (t.vertex.value == v.value)
    				throw new ConcreteVerticesGraph
    					.TargetRepeatException(t.toString() + " already in graph!");
    		}
    		targets.add(new VertexTarget(v, weight));
    		checkRep();
    }
    public L getValue() {
		return value;
	}
    public List<VertexTarget> getTargets() {
		return targets;
	}
    
    @Override
    public String toString() {
    		String string = value.toString();
    		
    		string += ": ";
    		for (VertexTarget t : targets) {
    			string += (t.vertex.value + ", ");
    		}
    		string += "\n";
    		return string;
    }
    
}
