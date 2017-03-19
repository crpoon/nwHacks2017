package crpoon.nwHacks.service;


import com.google.common.collect.ImmutableList;
import crpoon.nwHacks.client.TwitterClient;
import crpoon.nwHacks.database.StockDao;
import crpoon.nwHacks.database.StockInfoDao;
import crpoon.nwHacks.model.Stock;
import crpoon.nwHacks.model.StockInfo;

import java.util.Date;
import java.util.List;

public class TwitterService extends Service {

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

    public int getCurrentIncrease(String hashtag) {
        return TwitterClient.getInstance().getTweets(hashtag);

    }
}
