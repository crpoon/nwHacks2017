package crpoon.nwHacks;

import crpoon.nwHacks.client.TwitterClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println(TwitterClient.getInstance().getTweets("#fakenews"));
    }
}
