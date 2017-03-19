package crpoon.nwHacks.server;

import crpoon.nwHacks.database.StockInfoDao;
import crpoon.nwHacks.model.StockInfo;
import crpoon.nwHacks.service.TwitterService;

import java.util.List;

/**
 * Created by crpoon on 2017-03-19.
 */
public class TwitterServer {

    private static final long THREAD_SLEEP_TIME = 15000;

    private static TwitterServer instance;
    private static TwitterService service;

    private TwitterServer() {
        service = TwitterService.getInstance();
    }

    public static TwitterServer getInstance() {
        if (instance == null) {
            instance = new TwitterServer();
        }
        return instance;
    }

    public void start() {
        Thread trumpThread = createThread("Trump");
        Thread fnThread = createThread("Fake News");
        Thread winThread = createThread("#win");
        Thread saltThread = createThread("Salt Bae");

        trumpThread.start();
        fnThread.start();
        winThread.start();
        saltThread.start();
    }

    public Thread createThread(String name){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        service.calculateAndPersistStock(name);
                        Thread.sleep(THREAD_SLEEP_TIME);
                    } catch (InterruptedException e) {
                        // TODO: Handle this better
                        System.out.println("rip");
                    }
                }
            }
        });
    }
}
