package crpoon.nwHacks.server;

import crpoon.nwHacks.service.InstagramService;
import crpoon.nwHacks.service.TwitterService;

/**
 * Created by crpoon on 2017-03-19.
 */
public class InstagramServer {

    private static final long THREAD_SLEEP_TIME = 15000;

    private static InstagramServer instance;
    private static InstagramService service;

    private InstagramServer() {
        service = InstagramService.getInstance();
    }

    public static InstagramServer getInstance() {
        if (instance == null) {
            instance = new InstagramServer();
        }
        return instance;
    }

    public void start() {
        Thread selfiesThread = createThread("Selfies");
        Thread blessedThread = createThread("Blessed");
        Thread igersThread = createThread("Igers");
        Thread nofilterThread = createThread("No_Filter");

        selfiesThread.start();
        blessedThread.start();
        igersThread.start();
        nofilterThread.start();
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
