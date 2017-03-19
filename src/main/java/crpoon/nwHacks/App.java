package crpoon.nwHacks;

import crpoon.nwHacks.server.TwitterServer;
import crpoon.nwHacks.server.InstagramServer;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //TwitterServer.getInstance().start();
        InstagramServer.getInstance().start();

        System.out.println("THIS SHOULD NEVER RUN! YOU DUN GOOFED");
    }
}