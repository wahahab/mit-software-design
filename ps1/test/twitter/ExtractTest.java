/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import twitter.Extract.EmptyTweetsException;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much @foobar ?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype @bazzfooz ", d2);
    private static final Tweet tweetNoMentioned = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much ?", d1);
    private static final ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    
    @Before
    public void setUpTweets() {
    		tweets.clear();
    		tweets.add(tweet1);
		tweets.add(tweet2);
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(
        		Arrays.asList(tweetNoMentioned));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    @Test(expected = EmptyTweetsException.class)
    public void testGetTimespanEmptyTweets() {
    		// Empty tweets
		Extract.getTimespan(new ArrayList<Tweet>());
    }
    @Test
    public void testGetTimespan() {
    		// test two tweets
    		assertEquals(Extract.getTimespan(tweets),
    				new Timespan(d1, d2));
    		// test only one tweet
    		tweets.remove(0);
    		assertEquals(Extract.getTimespan(tweets), new Timespan(d2, d2));
    }
    @Test
    public void testGetMentionedUsersEmptyTweets() {
    		Set<String> result = Extract.getMentionedUsers(new ArrayList<Tweet>()); 
    		assertTrue(result.isEmpty());
    }
    @Test
    public void testGetMentionedUsers() {
    		// regular tweets
    		Set<String> result = Extract.getMentionedUsers(tweets);
    		String expected[] = {
    			"foobar",
    			"bazzfooz"
    		};
    		assertEquals(result,
    				new HashSet<String>(Arrays.asList(expected)));
    		// repeat mentioned
    		tweets.add(tweet1);
    		result = Extract.getMentionedUsers(tweets);
    		assertEquals(tweets.size(), 3);
    		assertEquals(result,
    				new HashSet<String>(Arrays.asList(expected)));
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
