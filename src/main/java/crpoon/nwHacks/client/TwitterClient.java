package crpoon.nwHacks.client;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TwitterClient {

private static TwitterClient instance;

	private static final String apiKey = "fUgJpMNuroKJ9uECAbwOo7TjQ";
	private static final String apiSecret = "ZlVzkLZDVFvX2I2DzHEhLk7X305a3rrrAObDNCLITgF1ReUJ2V";
	
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
	
	public String getTweets(String hashtag) {
		WebResource resource = client.resource("https://api.twitter.com/1.1/search/tweets.json");
	
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		//queryParams.add("json", js);
		
		String appKey = "Bearer " + apiKey;
		
		ClientResponse response = null;
		response = resource.queryParams(queryParams)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", appKey)
				.get(ClientResponse.class);
		
		String output = response.getEntity(String.class);
		System.out.println(output);
		return output;
	}
}
