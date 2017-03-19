package crpoon.nwHacks.service;

import com.google.gson.Gson;
import crpoon.nwHacks.database.StockDao;
import crpoon.nwHacks.model.Stock;
import crpoon.nwHacks.model.StockFrontEnd;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Darren on 2017-03-19.
 */
public class FrontEndService {

    private static final long INTERVAL_MS = 1000 * 60 * 60;

    private static FrontEndService instance;

    private FrontEndService() {
    }

    public static FrontEndService getInstance() {
        if (instance == null) {
            instance = new FrontEndService();
        }
        return instance;
    }

    public List<StockFrontEnd> getStocksToFrontEnd(String name) {
        Date threeHoursAgo = new Date(System.currentTimeMillis() - INTERVAL_MS);
        List<Stock> stocks = StockDao.getInstance().getAllStockByNameAfterDate(name, threeHoursAgo);

        List<StockFrontEnd> arr = new ArrayList<>();
        for (Stock s : stocks) {
            StockFrontEnd sfe = new StockFrontEnd(s.getPrice(), s.getCurIncrease(), s.getTicker(), s.getName(), s.getDate());
            arr.add(sfe);
        }
        return arr;
    }

    public StockFrontEnd getRecentStockToFrontEnd(String name) {
        Date threeHoursAgo = new Date(System.currentTimeMillis() - INTERVAL_MS);
        List<Stock> stocks = StockDao.getInstance().getAllStockByNameAfterDate(name, threeHoursAgo);
        Stock s = stocks.get(0);
        return new StockFrontEnd(s.getPrice(), s.getCurIncrease(), s.getTicker(), s.getName(), s.getDate());
    }

}
