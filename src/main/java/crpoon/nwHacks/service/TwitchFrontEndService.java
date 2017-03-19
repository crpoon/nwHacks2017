package crpoon.nwHacks.service;

import crpoon.nwHacks.database.StockDao;
import crpoon.nwHacks.model.Stock;
import crpoon.nwHacks.model.StockFrontEnd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by crpoon on 2017-03-19.
 */
public class TwitchFrontEndService {

    private static TwitchFrontEndService instance;

    private TwitchFrontEndService() {
    }

    public static TwitchFrontEndService getInstance() {
        if (instance == null) {
            instance = new TwitchFrontEndService();
        }
        return instance;
    }

    public List<StockFrontEnd> getStocksToFrontEnd(String name) {
        List<Stock> stocks = StockDao.getInstance().getAllStockByName(name);

        List<StockFrontEnd> arr = new ArrayList<>();
        for (Stock s : stocks) {
            StockFrontEnd sfe = new StockFrontEnd(s.getPrice(), s.getCurIncrease(), s.getTicker(), s.getName(), s.getDate());
            arr.add(sfe);
        }
        return arr;
    }
}
