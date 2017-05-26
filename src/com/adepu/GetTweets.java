package com.adepu;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;





@Path("/gettweets")
public class GetTweets {
	@GET
	@Produces("application/json")
	public Response doSomething() throws JSONException
	{
		final String TWITTER_CONSUMER_KEY = "cPEZbUzIAXyPbxeUNHyN1gDuj";
		final String TWITTER_SECRET_KEY = "XRXFN7oUbNi0k0zB01Mn3buq6D0CgYg55ZuoEtkRqiabVXN6hX";
		final String TWITTER_ACCESS_TOKEN = "154065080-UHxDNDDhM1jNXbdBJJFyuIiRYOoJR10gT68PljLU";
		final String TWITTER_ACCESS_TOKEN_SECRET = "bpPt6ek9N18U7IxDDPe83QRQg7WuaXe7d9uWcsKGTavDv";

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		    .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
		    .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
		    .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
		    .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		 JSONObject jo= new JSONObject();
		try {
		    Query query = new Query("Salesforce");
		    QueryResult result;
		   
		    
		
		    do {
		    	
		        result = twitter.search(query);
		        List<Status> tweets = result.getTweets();
		        int i=0;
		        for (Status tweet : tweets) {
		        	// tweet.getCreatedAt()
		        	i++;
		        	if(i<10)
		        	{
		           jo.put("@" + tweet.getUser().getScreenName() + " - " + tweet.getText(), tweet.getId()) ;
		        	}
		        	else
		        	{

		        		String s="Latest Tweets Mynam Bro"+jo;
		        		return Response.status(200).entity(s).build();
		        	}
		        }
		       
		    } while ((query = result.nextQuery()) != null );
		    System.exit(0);
		} catch (TwitterException te) {
		    te.printStackTrace();
		    System.out.println("Failed to search tweets: " + te.getMessage());
		    System.exit(-1);
		}
		
		return null;
		
		
		

		
	}

}

