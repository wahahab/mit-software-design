/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {
	
	public static class EmptyTweetsException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
     * Extract username in text
     * example usernames: @foobar @foo_bar @foo-rewe
     * 
     * @param text tweet text
     * @return distinct usernames in text
     */
    private static HashSet<String> extractUserNames(String text) {
    		String lower = text.toLowerCase();
		Pattern pattern = Pattern.compile("@([a-z_-]+)\\b");
		Matcher matcher = pattern.matcher(lower);
//		System.out.printf("Text: %s\n", lower);
		ArrayList<String> result = new ArrayList<String>();
    		
		while(matcher.find()) {
//			System.out.printf("Match: %s\n", );
			result.add(matcher.group(1));
		}
		return new HashSet<String>(result);
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
        HashSet<String> result = new HashSet<String>();
        
        for (Tweet tweet : tweets) {
        		result.addAll(extractUserNames(tweet.getText()));
        }
        return result;
    }

}
