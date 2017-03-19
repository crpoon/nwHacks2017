package crpoon.nwHacks.client;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import twitter4j.*;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TwitterClient {

private static TwitterClient instance;

	//https://twitter.com/realDonaldTrump/status/843088518339612673
	private static final long baseTweetId = 843088518339612673L;

	private static final String consumerKey = "fUgJpMNuroKJ9uECAbwOo7TjQ";
	private static final String consumerSecret = "ZlVzkLZDVFvX2I2DzHEhLk7X305a3rrrAObDNCLITgF1ReUJ2V";
	private static final String token = "843243416007802880-4D9S2x368zSDcsrwBLPiGmWJwS9vIJd";
	private static final String tokenSecret = "BOX4oY69vUPkt5I1c9FdHqqr4ktE3KEwmViZTwdW3eNXQ";
	
	private Client client;
	
	private TwitterClient() {
		client = Client.create();
	}
	
	public static TwitterClient getInstance() {
		if (instance == null) {
			instance = new TwitterClient();
		}
		return instance;
	}

	private Configuration createAuthConfiguration() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(token)
				.setOAuthAccessTokenSecret(tokenSecret);
		return cb.build();
	}

	public String getTweets(String hashtag) {
		try {
			TwitterFactory factory = new TwitterFactory(createAuthConfiguration());
			Twitter twitter = factory.getInstance();

			Query query = new Query("#trump");
			query.setSinceId(baseTweetId);
			query.count(100);
			QueryResult result = twitter.search(query);
//			for (Status status : result.getTweets()) {
//				System.out.println(status.getText());
//			}
			return result.getTweets().size() + "";
		} catch (TwitterException e) {
			// TODO: Write a exception to handle
			System.out.println("rip");
		}
		return "";
	}

	class NonceGenerator {

		private SecureRandom random = new SecureRandom();

		public String createNonce() {
			return new BigInteger(130, random).toString(32);
		}
	}
}
