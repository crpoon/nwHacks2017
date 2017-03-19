package crpoon.nwHacks.service;


import com.google.common.collect.ImmutableList;
import crpoon.nwHacks.client.InstagramClient;
import crpoon.nwHacks.client.TwitterClient;
import crpoon.nwHacks.database.StockInfoDao;
import crpoon.nwHacks.model.StockInfo;

import java.util.List;

public class InstagramService {

    private static final int MINUTE_COUNT = 5;
    private static final int MAX_COUNT = 25;
    private static final double STANDARD_TWEET_PER_UPDATE = 20.0;
    private static InstagramService instance;

    private InstagramService() {
    }

    public static InstagramService getInstance() {
        if (instance == null) {
            instance = new InstagramService();
        }
        return instance;
    }

    public void initializeInstagramStockInfo() {
        List<String> instagramSector = ImmutableList.of("instagram");

        StockInfo selfies = new StockInfo("Selfies", "SELF", "Instagram", "");
        StockInfo blessed = new StockInfo("Blessed", "BLES", "Instagram", "");
        StockInfo igers = new StockInfo("Igers", "IGER", "Instagram", "");
        StockInfo nofilter = new StockInfo("No Filter", "NOF", "Instagram", "");

        List<StockInfo> infos = ImmutableList.of(selfies, blessed, igers, nofilter);
        for (StockInfo info : infos) {
            StockInfoDao.getInstance().insertStockInfo(info);
        }
    }

    public void performUpdateHashtag(String hashtag) {
        int count = InstagramClient.getInstance().getInstasTags(hashtag);

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
