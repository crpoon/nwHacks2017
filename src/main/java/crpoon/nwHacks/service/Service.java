package crpoon.nwHacks.service;

import crpoon.nwHacks.database.StockDao;
import crpoon.nwHacks.model.Stock;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by crpoon on 2017-03-19.
 */
public abstract class Service {

    protected static final int MINUTE_COUNT = 5;
    protected static final int MAX_COUNT = 25;

    private static final long ONE_HOUR = 1000 * 60 * 60;

    public double getAverageIncomePerHour(String hashtag) {
        Date oneHourAgo = new Date(System.currentTimeMillis() - ONE_HOUR);
        List<Stock> stocks = StockDao.getInstance().getAllStockByNameAfterDate(hashtag, oneHourAgo);

        double sumIncrease = 0.0;
        for (Stock stock : stocks) {
            sumIncrease += stock.getCurIncrease();
        }
        double averageIncrease = sumIncrease / stocks.size();

        double multiplier = (MAX_COUNT - 1) / MAX_COUNT;

        return multiplier * averageIncrease;
    }

    public double getCurrentIncreasePerHour(String hashtag) {
        int count = getCurrentIncrease(hashtag);

        List<Stock> stocks = StockDao.getInstance().getAllStockByName(hashtag);
        if (stocks == null || stocks.isEmpty()) {
            return 100.0;
        }
        Stock lastStock = stocks.get(0);
        Date lastStockDate = lastStock.getDate();
        double diffTime = System.currentTimeMillis() - lastStockDate.getTime();
        diffTime = ONE_HOUR / diffTime;

        double multiplier = 1 / MAX_COUNT;
        return multiplier * count * diffTime;
    }

    public abstract int getCurrentIncrease(String hashtag);
}
