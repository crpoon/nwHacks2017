package crpoon.nwHacks.model;

/**
 * Created by crpoon on 2017-03-19.
 */
public class TwitterSince {

    private String stockName;
    private long sinceId;

    public TwitterSince(String stockName, long sinceId){
        this.stockName = stockName;
        this.sinceId = sinceId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public long getSinceId() {
        return sinceId;
    }

    public void setSinceId(long sinceId) {
        this.sinceId = sinceId;
    }
}
