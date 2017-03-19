package crpoon.nwHacks.service;

import crpoon.nwHacks.database.StockDao;
import crpoon.nwHacks.database.StockInfoDao;
import crpoon.nwHacks.model.Stock;
import crpoon.nwHacks.model.StockInfo;

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

    public double getCurrentIncreasePerHour(String hashtag, Stock lastStock) {
        if (lastStock == null) {
            return 100.0;
        }
        int count = getCurrentIncrease(hashtag);
        Date lastStockDate = lastStock.getDate();
        double diffTime = System.currentTimeMillis() - lastStockDate.getTime();
        diffTime = ONE_HOUR / diffTime;

        double multiplier = 1 / MAX_COUNT;
        return multiplier * count * diffTime;
    }

    public void calculateAndPersistStock(String hashtag) {
        StockInfo info = StockInfoDao.getInstance().getStockInfoByName(hashtag);
        String ticker = info.getTicker();

        double currentPrice;
        double currentIncrease;
        List<Stock> stocks = StockDao.getInstance().getAllStockByName(hashtag);
        if (stocks == null || stocks.isEmpty()) {
            currentPrice = 100.0;
            currentIncrease = 0.0;
        } else {
            Stock lastStock = stocks.get(0);
            double currentIncreasePerHour = getCurrentIncreasePerHour(hashtag, lastStock);
            currentPrice = calculatePrice(getAverageIncomePerHour(hashtag),
                    currentIncreasePerHour, lastStock);
            currentIncrease = currentPrice - lastStock.getPrice();
        }
        Stock stock = new Stock(hashtag, ticker, new Date(), currentPrice, currentIncrease);
        StockDao.getInstance().insertStock(stock);
    }

    private double calculatePrice(double avgIncPerHour, double curIncPerHour, Stock lastStock) {
        return lastStock.getPrice() + (curIncPerHour - avgIncPerHour);
    }

    public abstract int getCurrentIncrease(String hashtag);
}
