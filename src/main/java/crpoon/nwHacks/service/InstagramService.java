package crpoon.nwHacks.service;


import com.google.common.collect.ImmutableList;
import crpoon.nwHacks.client.InstagramClient;
import crpoon.nwHacks.database.InstagramInfoDao;
import crpoon.nwHacks.database.StockInfoDao;
import crpoon.nwHacks.model.InstagramInfo;
import crpoon.nwHacks.model.StockInfo;

import java.util.Date;
import java.util.List;

public class InstagramService extends Service {

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

        StockInfo selfies = new StockInfo("Selfies", "SELF", instagramSector, "");
        StockInfo blessed = new StockInfo("Blessed", "BLES", instagramSector, "");
        StockInfo igers = new StockInfo("Igers", "IGER", instagramSector, "");
        StockInfo nofilter = new StockInfo("No_Filter", "NOF", instagramSector, "");

        List<StockInfo> infos = ImmutableList.of(selfies, blessed, igers, nofilter);
        for (StockInfo info : infos) {
            StockInfoDao.getInstance().updateStockInfo(info);
            InstagramInfoDao.getInstance().updateInstagramInfo(new InstagramInfo(info.getName(), info.getTicker(), 0, new Date()));
        }


    }

    public int getCurrentIncrease(String hashtag) {
        int currentMention = InstagramClient.getInstance().getInstasTags(hashtag);
        InstagramInfo info = InstagramInfoDao.getInstance().getAllInstagramInfoByName(hashtag).get(0);
        int lastMention = info.getTotalMention();
        String ticker = info.getTicker();
        InstagramInfoDao.getInstance().updateInstagramInfo(new InstagramInfo(hashtag, ticker, currentMention, new Date()));
        int currentIncrease = currentMention - lastMention;
        System.out.println("LastMention: "+ lastMention);
        System.out.println("CurrentMention: "+ currentMention);
        System.out.println("CurrentIncrease: "+ currentIncrease);
        return currentIncrease;

    }
}
