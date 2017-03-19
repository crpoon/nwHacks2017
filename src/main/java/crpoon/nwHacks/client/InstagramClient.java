package crpoon.nwHacks.client;

import org.jinstagram.*;
import org.jinstagram.auth.*;
import org.jinstagram.auth.model.*;
import org.jinstagram.entity.tags.*;
import org.jinstagram.exceptions.*;


public class InstagramClient {

private static InstagramClient instance;

	//private static final String apiKey = "8d608465facb4c7cbd5dbae5974c96c4";
	//private static final String apiSecret = "c0b037cf57c94094973700f83ebb30dd";
	//private static final String callbackUrl = "http://dankmemes-nwhacks.herokuapp.com/";
	private static final String accesstoken = "4848984552.8d60846.60eda05fe65c461088e5c6c7c4a5c769";
	//private static final String scope = "tags";

	private Instagram instagram;
	private InstagramApi api;

	public InstagramClient() {


		this.api = new InstagramApi();
		Token token = new Token(accesstoken, accesstoken);
		this.instagram = new Instagram(token);

	}
	
	public static InstagramClient getInstance() {
		if (instance == null) {
			instance = new InstagramClient();
		}
		return instance;
	}


	
	public String getInstasTags(String hashtag) {
		try {
			TagInfoFeed feed = instagram.getTagInfo(hashtag);
			TagInfoData tagData = feed.getTagInfo();
			System.out.println("name : " + tagData.getTagName());
			System.out.println("media_count : " + tagData.getMediaCount());
			return tagData.toString();
		} catch (InstagramException e) {
			System.out.println(e.toString());
		}
		return "";
	}
}
