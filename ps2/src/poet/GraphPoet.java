/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.omg.CORBA.PRIVATE_MEMBER;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    private int getPrevWeight(String source, String target) {
    		Map<String, Integer> targets = graph.targets(source);
    		
    		if (targets.containsKey(target)) {
    			return targets.get(target);
    		}
    		return 0;
    }
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        BufferedReader reader = new BufferedReader(
        		new FileReader(corpus));
        String line;
        String words[];
        String word;
        String nextWord;
        int prevWeight;
        
        while ((line = reader.readLine()) != null) {
        		words = line.split(" ");
        		for (int i = 0 ; i < words.length - 1 ; i++) {
        			word = words[i].toLowerCase();
        			nextWord = words[i + 1].toLowerCase();
        			if (!graph.vertices().contains(word)) {
        				graph.add(word);
        			}
        			if (!graph.vertices().contains(nextWord)) {
        				graph.add(nextWord);
        			}
        			prevWeight = getPrevWeight(word, nextWord);
        			graph.set(word, nextWord, prevWeight + 1);
        		}
        }
        
    }
    
    private Integer getWeight(String potentialBridge, String targetWord) {
    		Map<String, Integer> targets = graph.targets(potentialBridge);
    		if (targets.containsKey(targetWord)) {
    			return targets.get(targetWord);
    		}
    		return 0;
    }
    
    private String getBridgeWord(String word, String targetWord) {
    		Map<String, Integer> potentialBridges = graph.targets(word.toLowerCase());
    		Integer weight;
    		Integer thisWeight;
    		Integer maxWeight = 0;
    		String maxBridge = "";
    		
    		for (String potentialBridge : potentialBridges.keySet()) {
    			thisWeight = potentialBridges.get(potentialBridge);
    			weight = getWeight(potentialBridge, targetWord.toLowerCase());
    			if (weight != 0 && weight + thisWeight > maxWeight) {
    				maxWeight = weight + thisWeight;
    				maxBridge = potentialBridge;
    			}
    		}
    		return maxBridge;
    }
    
    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        String words[] = input.split(" ");
        String word;
        String nextWord;
        String bridgeWord;
        ArrayList<String> poemWords = new ArrayList<>();
        
        poemWords.add(words[0]);
        for (int i = 0 ; i < words.length - 1 ; i++) {
        		word = words[i];
        		nextWord = words[i + 1];
        		bridgeWord = getBridgeWord(word, nextWord);
        		if (bridgeWord != "")
        			poemWords.add(bridgeWord);
        		poemWords.add(nextWord);
        }
        return String.join(" ", poemWords);
    }
    
    @Override
    public String toString() {
    		return graph.toString();
    }
    
}
