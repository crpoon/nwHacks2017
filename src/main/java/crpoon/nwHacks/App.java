package crpoon.nwHacks;

import crpoon.nwHacks.client.InstagramClient;
import crpoon.nwHacks.client.TwitterClient;
import crpoon.nwHacks.service.TwitterService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        //TwitterService.getInstance().initializeTwitterStockInfo();
        //System.out.println("YAAAAAAS");
        //System.out.println(TwitterClient.getInstance().getTweets("#fakenews"));
        InstagramClient.getInstance().getInstasTags("selfies");
    }
}
