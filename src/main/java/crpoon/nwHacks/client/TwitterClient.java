package crpoon.nwHacks.client;

import crpoon.nwHacks.database.TwitterDao;
import crpoon.nwHacks.model.TwitterSince;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {

private static TwitterClient instance;

	private static final String consumerKey = "fUgJpMNuroKJ9uECAbwOo7TjQ";
	private static final String consumerSecret = "ZlVzkLZDVFvX2I2DzHEhLk7X305a3rrrAObDNCLITgF1ReUJ2V";
	private static final String token = "843243416007802880-4D9S2x368zSDcsrwBLPiGmWJwS9vIJd";
	private static final String tokenSecret = "BOX4oY69vUPkt5I1c9FdHqqr4ktE3KEwmViZTwdW3eNXQ";
	
	private TwitterClient() {
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

	public int getTweets(String hashtag) {
		try {
			TwitterFactory factory = new TwitterFactory(createAuthConfiguration());
			Twitter twitter = factory.getInstance();

			int count = 0;
			long sinceId = TwitterDao.getInstance().getTwitterSince(hashtag).getSinceId();
			Query query = new Query(hashtag);
			query.setSinceId(sinceId);
			query.count(100);
			Long setSinceId = null;

			while (query != null) {
				QueryResult result = twitter.search(query);
				count += result.getTweets().size();
				Status tweet = result.getTweets().get(0);

				sinceId = tweet.getId();
				if (setSinceId == null) {
					setSinceId = tweet.getId();
				}

				query = result.nextQuery();
				if (query != null) {
					query.setSinceId(sinceId);
					if (count >= 500) {
						break;
					}
				}
			}
			TwitterSince ts = new TwitterSince(hashtag, setSinceId);
			TwitterDao.getInstance().updateTwitterSince(ts);

			return count;
		} catch (TwitterException e) {
			// TODO: Write a exception to handle
		}
		return 0;
	}
}
