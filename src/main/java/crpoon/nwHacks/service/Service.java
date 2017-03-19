package crpoon.nwHacks.service;

import crpoon.nwHacks.database.StockDao;
import crpoon.nwHacks.model.Stock;

import java.util.Date;
import java.util.List;

/**
 * Created by crpoon on 2017-03-19.
 */
public class Service {

    protected static final int MINUTE_COUNT = 5;
    protected static final int MAX_COUNT = 25;

    public double getAverageIncomePerHour(String hashtag) {
        long oneHourTime = 1000 * 60 * 60;
        Date oneHourAgo = new Date(System.currentTimeMillis() - oneHourTime);
        List<Stock> stocks = StockDao.getInstance().getAllStockAfterDate(oneHourAgo);

        double sumIncrease = 0.0;
        for (Stock stock : stocks) {
            sumIncrease += stock.getCurIncrease();
        }
        double averageIncrease = sumIncrease / stocks.size();

        double multiplier = (MAX_COUNT - 1) / MAX_COUNT;

        return multiplier * averageIncrease;
    }
}
