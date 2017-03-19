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

    protected static final double MAX_COUNT = 25.0;

    private static final long ONE_HOUR = 1000 * 60 * 60;

    // Gets the sum of all increases of a stock
    public double getAverageIncomePerHour(String hashtag) {
        Date oneHourAgo = new Date(System.currentTimeMillis() - ONE_HOUR);
        List<Stock> stocks = StockDao.getInstance().getAllStockByNameAfterDate(hashtag, oneHourAgo);

        double sumIncrease = 0.0;
        for (Stock stock : stocks) {
            sumIncrease += stock.getCurIncrease();
        }
        return sumIncrease / stocks.size();
    }

    // Calculate the currentIncrease/hour
    public double getCurrentIncreasePerHour(String hashtag, Stock lastStock) {
        if (lastStock == null) {
            return 100.0;
        }
        // Calls the client to get the newest value from the server
        int count = getCurrentIncrease(hashtag);
        Date lastStockDate = lastStock.getDate();
        double diffTime = System.currentTimeMillis() - lastStockDate.getTime();

        diffTime = ONE_HOUR / diffTime;

        // count * diffTime = currentIncresase/hr
        return count * diffTime;
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
            double avgIncreasePerHour = getAverageIncomePerHour(hashtag);

            System.out.println("Name: " + hashtag +
                    ", AvgInc: " + avgIncreasePerHour +
                    ", CurInc: " + currentIncreasePerHour);
            currentPrice = calculatePrice(avgIncreasePerHour, currentIncreasePerHour, lastStock);
            currentIncrease = currentPrice - lastStock.getPrice();
        }
        System.out.println("Name: "  + hashtag +
                ", Ticker: " + ticker +
                ", Price: " + currentPrice +
                ", Increase: " + currentIncrease);
        Stock stock = new Stock(hashtag, ticker, new Date(), currentPrice, currentIncrease);
        StockDao.getInstance().insertStock(stock);
    }

    private double calculatePrice(double avgIncPerHour, double curIncPerHour, Stock lastStock) {
        double fullAverage = avgIncPerHour;
        double firstMultiplier = ((MAX_COUNT - 1.0)/ MAX_COUNT);
        double secondMultiplier = (1.0 / MAX_COUNT);
        double firstHalf = firstMultiplier * avgIncPerHour;
        double secondHalf = secondMultiplier *curIncPerHour;
        double averageWithCur = firstHalf + secondHalf;

        System.out.println("Name: " + lastStock.getName() +
                ", 1Mul: " + firstMultiplier +
                ", 2Mul: " + secondMultiplier +
                ", 1Haf: " + firstHalf +
                ", 2Mul: " + secondHalf +
                ", AvgCur: " + averageWithCur);
        return lastStock.getPrice() + (averageWithCur - fullAverage);
    }

    public abstract int getCurrentIncrease(String hashtag);
}
