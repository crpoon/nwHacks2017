package crpoon.nwHacks.service;


import com.google.common.collect.ImmutableList;
import crpoon.nwHacks.client.TwitterClient;
import crpoon.nwHacks.database.StockDao;
import crpoon.nwHacks.database.StockInfoDao;
import crpoon.nwHacks.model.Stock;
import crpoon.nwHacks.model.StockInfo;

import java.util.Date;
import java.util.List;

public class TwitterService {

    private static final int MINUTE_COUNT = 5;
    private static final int MAX_COUNT = 25;
    private static final double STANDARD_TWEET_PER_UPDATE = 20.0;
    private static TwitterService instance;

    private TwitterService() {
    }

    public static TwitterService getInstance() {
        if (instance == null) {
            instance = new TwitterService();
        }
        return instance;
    }

    public void initializeTwitterStockInfo() {
        List<String> twitterSector = ImmutableList.of("twitter");

        StockInfo trump = new StockInfo("Trump", "TRMP", twitterSector, "");
        StockInfo fakeNews = new StockInfo("Fake News", "FKNW", twitterSector, "");
        StockInfo saltBae = new StockInfo("Salt Bae", "SALT", twitterSector, "");
        StockInfo win = new StockInfo("#win", "WIN", twitterSector, "");

        List<StockInfo> infos = ImmutableList.of(trump, fakeNews, saltBae, win);
        for (StockInfo info : infos) {
            StockInfoDao.getInstance().insertStockInfo(info);
        }
    }

    public void performUpdateHashtag(String hashtag) {
        int count = TwitterClient.getInstance().getTweets(hashtag);

    }

//    private double getPrice(int count, String hashtag) {
//        double newCount = (double) count;
//
//        long oneDayTime = 1000 * 60 * 60 * 24;
//        Date oneDayAgo = new Date(System.currentTimeMillis() - oneDayTime);
//        List<Stock> stocks = StockDao.getInstance().getAllStockAfterDate(oneDayAgo);
//        Stock mostRecentStock;
//        if (stocks.size() > 0) {
//            mostRecentStock = stocks.get(0);
//        }
//
//
//    }


}
