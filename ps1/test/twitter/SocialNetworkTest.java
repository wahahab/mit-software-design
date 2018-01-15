/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Before;
import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
	
	private static final ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	
	@Before
	public void tweetsSetUp() {
		tweets.clear();
		
	}
	
	@Test
	public void testGuessFollowsGraph() {
		Map<String, Set<String>> result = SocialNetwork.guessFollowsGraph(
			new ArrayList<Tweet>());
		
		// empty tweets
		assertEquals(result.size(), 0);
		// tweets without any mentions
		tweets.add(
			new Tweet(0, "foo", "bar foo", Instant.now()));
		tweets.add(
				new Tweet(1, "foo", "bar foo ask", Instant.now()));
		tweets.add(
				new Tweet(2, "foo", "bar foo", Instant.now()));
		tweets.add(
				new Tweet(3, "bar", "bar foo", Instant.now()));
		result = SocialNetwork.guessFollowsGraph(tweets);
		assertEquals(result.size(), 2);
		assertTrue(result.containsKey("foo"));
		assertTrue(result.containsKey("bar"));
		assertTrue(result.get("foo").isEmpty());
		assertTrue(result.get("bar").isEmpty());
		// tweets with mentions
		tweets.clear();
		tweets.add(
				new Tweet(0, "foo", "bar @bar", Instant.now()));
		tweets.add(
				new Tweet(1, "foo", "bar foo @ask", Instant.now()));
		tweets.add(
				new Tweet(2, "foo", "bar foo @foobar", Instant.now()));
		tweets.add(
				new Tweet(3, "bar", "bar @foo", Instant.now()));
		result = SocialNetwork.guessFollowsGraph(tweets);
		assertEquals(result.size(), 2);
		assertTrue(result.containsKey("foo"));
		assertTrue(result.containsKey("bar"));
		assertEquals(result.get("foo").size(), 3);
		assertEquals(result.get("bar").size(), 1);
		assertTrue(result.get("foo").contains("bar"));
		assertTrue(result.get("foo").contains("bar"));
	}
	
	@Test
	public void testInfluencers() {
		// test empty map
		HashMap<String, Set<String>> graph = new HashMap<String, Set<String>>();
		List<String> result = SocialNetwork.influencers(graph);
		
		assertEquals(result.size(), 0);
		// test one username
		HashSet<String> followers = new HashSet<String>();
		followers.add("bar");
		followers.add("bazz");
		graph.put("foo", followers);
		result = SocialNetwork.influencers(graph);
		assertEquals(result.size(), 1);
		assertEquals(result.get(0), "foo");
		// test multiple usernames and same follow count
		HashSet<String> followers2 = new HashSet<String>();
		followers2.add("bar");
		followers2.add("bazz");
		followers2.add("bazzzzz");
		followers2.add("josh");
		graph.put("jerry", followers2);
		graph.put("josh", followers);
		result = SocialNetwork.influencers(graph);
		assertEquals(result.size(), 3);
		assertEquals(result.get(0), "jerry");
		assertTrue(result.subList(1, result.size()).contains("josh"));
		assertTrue(result.subList(1, result.size()).contains("foo"));
	}
	
	private void assertFollowEachOther(Map<String, Set<String>> result,
			String a, String b) {
		assertTrue(result.get(a).contains(b));
		assertTrue(result.get(b).contains(a));
	}
	
	@Test
	public void testGuessFollowsGraphCommonTag() {
		ArrayList<Tweet> tweets = new ArrayList<>();
		// empty tweets
		Map<String, Set<String>> result = SocialNetwork.guessFollowsGraphCommonTag(tweets);
		
		assertEquals(result.size(), 0);
		// test no common tags
		tweets.add(
				new Tweet(0, "foo", "#bar foo", Instant.now()));
		tweets.add(
				new Tweet(1, "bazz", "#foo foo ask", Instant.now()));
		result = SocialNetwork.guessFollowsGraphCommonTag(tweets);
		assertEquals(result.size(), 2);
		assertEquals(result.get("foo").size(), 0);
		assertEquals(result.get("bazz").size(), 0);
		// test one common tags
		tweets.add(
				new Tweet(2, "bar", "#foo foo #ask", Instant.now()));
		result = SocialNetwork.guessFollowsGraphCommonTag(tweets);
		assertEquals(result.size(), 3);
		assertFollowEachOther(result, "bazz", "bar");
		// test multiple common tags
		tweets.add(
				new Tweet(3, "jerry", "#foo #bar #ask", Instant.now()));
		tweets.add(
				new Tweet(4, "john", "foo foo #ask", Instant.now()));
		result = SocialNetwork.guessFollowsGraphCommonTag(tweets);
		assertEquals(result.size(), 5);
		assertFollowEachOther(result, "foo", "jerry");
		assertFollowEachOther(result, "john", "jerry");
		assertFollowEachOther(result, "john", "bar");
		assertFollowEachOther(result, "jerry", "bar");
		assertFollowEachOther(result, "jerry", "bazz");
	}
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
