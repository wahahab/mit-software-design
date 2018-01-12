/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.List;
import java.util.Set;


/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {
	
	public static class EmptyTweetsException extends RuntimeException {
		public EmptyTweetsException(String string) {
			super(string);
		}
	}

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     * @throws EmptyTweetsException 
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    		Instant start = null;
    		Instant end = null;
    		Instant timestamp;
    		
    		if (tweets.size() == 0)
    			throw new EmptyTweetsException("Empty tweets!");
    		for (Tweet tweet : tweets) {
    			timestamp = tweet.getTimestamp();
    			if (start == null) {
    				start = timestamp;
    				end = timestamp;
    			}
    			else if (timestamp.isBefore(start))
    				start = timestamp;
    			else if (timestamp.isAfter(end))
    				end = timestamp;
    		}
    		return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        throw new RuntimeException("not implemented");
    }

}
