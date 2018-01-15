/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

	
	/**
	 * Find user relationship base on common hashtag
	 * if two users have same hashtag in their text
	 * treat same as they follow each other
	 * 
	 * @param tweets Array of tweets
	 * @return Graph of social network username => [set of related usernames]
	 */
	public static Map<String, Set<String>> guessFollowsGraphCommonTag(List<Tweet> tweets) {
        HashMap<String, Set<String>> socialNet = new HashMap<String, Set<String>>();
        // map of username => [set of mentioned hashtags]
        HashMap<String, Set<String>> userHashtag = new HashMap<String, Set<String>>();
        // map of hashtag => [set of users that mentioned this hashtag]
        HashMap<String, Set<String>> hashtagUsers = new HashMap<String, Set<String>>();
        
        // extract tags and record tags each user use
        for (Tweet tweet : tweets) {
        		for (String hashtag : Extract.extractHashtags(tweet.getText())) {
        			if (!userHashtag.containsKey(tweet.getAuthor())) {
        				userHashtag.put(tweet.getAuthor(),
        						new HashSet<String>());
        			}
        			userHashtag.get(tweet.getAuthor())
        				.add(hashtag);
        		}
        }
        // build tag to users map
        for (String name : userHashtag.keySet()) {
        		for (String tag: userHashtag.get(name)) {
        			if (!hashtagUsers.containsKey(tag)) {
        				hashtagUsers.put(tag,
        						new HashSet<String>());
        			}
        			hashtagUsers.get(tag)
        				.add(name);
        		}
        }
        // build social network
        for (String name : userHashtag.keySet()) {
	        	for (String tag: userHashtag.get(name)) {
	        		if (!socialNet.containsKey(name)) {
	        			socialNet.put(name,
	        					new HashSet<String>());
	        		}
	        		socialNet.get(name)
	        			.addAll(hashtagUsers.get(tag));
	        	}
    		}
        // remove self (you can't follow yourself, right?)
        for (String name : socialNet.keySet()) {
        		socialNet.get(name)
        			.remove(name);
		}
        return socialNet;
    }
	
    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        HashMap<String, Set<String>> socialNet = new HashMap<String, Set<String>>();
        
        for (Tweet tweet : tweets) {
        		if (!socialNet.containsKey(tweet.getAuthor())) {
        			socialNet.put(tweet.getAuthor(),
        				new HashSet<String>());
        		}
        		for (String name : Extract.extractUserNames(tweet.getText())) {
        			socialNet.get(tweet.getAuthor())
        				.add(name);
        		}
        }
        return socialNet;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
    		class UserCount {
    			Integer count;
    			String username;
    			public UserCount(Integer c, String n) {
    				count = c;
    				username = n;
    			}
    		}
    		ArrayList<UserCount> sortedUsers = new ArrayList<UserCount>();
    		ArrayList<String> sortedUsernames = new ArrayList<String>();
    		
        for (String username : followsGraph.keySet()) {
        		sortedUsers.add(
        				new UserCount(followsGraph.get(username).size(), username));
        }
        Collections.sort(sortedUsers, new Comparator<UserCount>() {
			@Override
			public int compare(UserCount o1, UserCount o2) {
				return o2.count - o1.count;
			}
        		
		});
        for (UserCount userCount : sortedUsers) {
        		sortedUsernames.add(userCount.username);
        }
        return sortedUsernames;
    }

}
