package crpoon.nwHacks.service;


import com.google.common.collect.ImmutableList;
import crpoon.nwHacks.client.TwitterClient;
import crpoon.nwHacks.database.StockInfoDao;
import crpoon.nwHacks.database.TwitterDao;
import crpoon.nwHacks.model.StockInfo;
import crpoon.nwHacks.model.TwitterSince;

import java.util.List;

public class TwitterService extends Service {

    // https://twitter.com/CarlosandAnaC/status/843310363609370624
    private static final long baseTweetId = 843310363609370624L;

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
            StockInfoDao.getInstance().updateStockInfo(info);

            TwitterSince since = new TwitterSince(info.getName(), baseTweetId);
            TwitterDao.getInstance().updateTwitterSince(since);
        }

    }

    public int getCurrentIncrease(String hashtag) {
        return TwitterClient.getInstance().getTweets(hashtag);

    }
}
