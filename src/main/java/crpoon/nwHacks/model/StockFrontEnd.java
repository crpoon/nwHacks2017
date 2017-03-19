package crpoon.nwHacks.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Darren on 2017-03-19.
 */
public class StockFrontEnd {

    private double price;
    private double increase;
    private String ticker;
    private String indexName;
    private String timeStamp;

    public StockFrontEnd(double price, double increase, String ticker, String indexName, Date timeStamp) {
        this.price = price;
        this.increase = increase;
        this.ticker = ticker;
        this.indexName = indexName;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.timeStamp = format.format(timeStamp);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getIncrease() {
        return increase;
    }

    public void setIncrease(double increase) {
        this.increase = increase;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        this.timeStamp = format.format(timeStamp);
    }
}
