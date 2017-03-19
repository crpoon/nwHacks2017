package crpoon.nwHacks;

import crpoon.nwHacks.controller.InstagramController;
import crpoon.nwHacks.server.TwitterServer;
import crpoon.nwHacks.server.InstagramServer;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Hello world!
 *
 */
public class App
{

    public static final String BASE_URI = "http://localhost:8080/";

//    public static void main( String[] args )
//    {
//        System.out.println( "Hello World!" );
//        //TwitterServer.getInstance().start();
//        //InstagramServer.getInstance().start();
//        System.out.println(InstagramController.getInstance().getRecent());
//
//        System.out.println("THIS SHOULD NEVER RUN! YOU DUN GOOFED");
//    }


    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.underdog.jersey.grizzly package
        final ResourceConfig rc = new ResourceConfig();//.packages("crpoon.nwHacks");
        rc.registerClasses(InstagramController.class);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
//        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI));
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}