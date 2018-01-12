/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.CancelablePrintJob;
import javax.security.sasl.AuthorizeCallback;
import javax.xml.soap.SAAJMetaFactory;

import org.junit.Before;
import org.junit.Test;
import org.omg.IOP.MultipleComponentProfileHelper;

public class FilterTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    
    @Before
    public void setUpTweets() {
    		tweets.clear();
    		tweets.add(tweet1);
    		tweets.add(tweet2);
    }
    
    @Test
    public void testWrittenByEmpty() {
    		List<Tweet> result = Filter.writtenBy(new ArrayList<Tweet>(), "foo");
    		
//    		empty tweets
    		assertEquals(result.size(), 0);
//    		can find tweet
    		result = Filter.writtenBy(tweets, "alyssa");
    		assertEquals(result.size(), 1);
    		assertEquals(result.get(0).getAuthor(), "alyssa");
//    		author name not in tweets
    		result = Filter.writtenBy(tweets, "foobar");
    		assertEquals(result.size(), 0);
//    		multiple tweets written by same author
    		tweets.add(tweet1);
    		tweets.add(tweet1);
    		result = Filter.writtenBy(tweets, "alyssa");
    		assertEquals(result.size(), 3);
    		for (Tweet tweet : result) {
    			assertEquals(tweet.getAuthor(), "alyssa");
    		}
    }
    
    @Test
    public void testInTimespan() {
    		Timespan timespan = new Timespan(Instant.MIN, Instant.MAX);
    		List<Tweet> result = Filter.inTimespan(new ArrayList<Tweet>(),
    				timespan);
    		
//    		empty tweets
    		assertEquals(result.size(), 0);
//    		test large timespan
    		result = Filter.inTimespan(tweets, timespan);
    		assertEquals(result.size(), tweets.size());
//    		test small timespan
    		timespan = new Timespan(Instant.parse("2016-02-17T09:50:00Z"),
    				Instant.parse("2016-02-17T10:10:00Z"));
    		result = Filter.inTimespan(tweets, timespan);
    		assertEquals(result.size(), 1);
    		assertEquals(result.get(0).getId(), tweet1.getId());
//    		test small timespan and cant find any match tweets
    		timespan = new Timespan(Instant.parse("2016-02-16T09:50:00Z"),
    				Instant.parse("2016-02-16T10:10:00Z"));
    		result = Filter.inTimespan(tweets, timespan);
    		assertEquals(result.size(), 0);
    }
   
    @Test
    public void testContainingMy() {
    		String wordsArray[] = {
            	"reasonable",
        };
    		ArrayList<String> words = new ArrayList<String>(
    			Arrays.asList(wordsArray));
    		// test empty tweets
    		List<Tweet> result = Filter.containing(new ArrayList<Tweet>(),
    				words);
    		
    		assertEquals(result.size(), 0);
    		// find first tweet
    		result = Filter.containing(tweets, words);
    		assertEquals(result.size(), 1);
    		assertEquals(result.get(0).getId(), tweet1.getId());
    		// find both tweets
    		words.clear();
    		words.add("talk");
    		result = Filter.containing(tweets, words);
    		assertEquals(result.size(), tweets.size());
    		// empty word list
    		words.clear();
    		result = Filter.containing(tweets, words);
    		assertEquals(result.size(), 0);
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }
    
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
    }
    
    @Test
    public void testContaining() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, containing.indexOf(tweet1));
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
