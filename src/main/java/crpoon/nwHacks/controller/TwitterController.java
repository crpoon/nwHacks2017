package crpoon.nwHacks.controller;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import crpoon.nwHacks.model.StockFrontEnd;
import crpoon.nwHacks.service.FrontEndService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crpoon on 2017-03-19.
 */
@Path("twitter/")
public class TwitterController {

    public TwitterController() {
    }

    @GET
    @Path("stocks/")
    public Response getHistorical() {
        System.out.println("Getting History for Twitter");
        Gson gson = new Gson();
        List<StockFrontEnd> trump = FrontEndService.getInstance().getStocksToFrontEnd("Trump");
        List<StockFrontEnd> fakeNews = FrontEndService.getInstance().getStocksToFrontEnd("Fake News");
        List<StockFrontEnd> win = FrontEndService.getInstance().getStocksToFrontEnd("#win");
        List<StockFrontEnd> saltBae = FrontEndService.getInstance().getStocksToFrontEnd("Salt Bae");

        List<List<StockFrontEnd>> ret = new ArrayList<>();
        ret.add(trump);
        ret.add(fakeNews);
        ret.add(win);
        ret.add(saltBae);

        return Response.ok(gson.toJson(ret), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("stocks/recent")
    public Response getRecent() {
        System.out.println("Getting Recent for Twitter");
        Gson gson = new Gson();
        StockFrontEnd trump = FrontEndService.getInstance().getRecentStockToFrontEnd("Trump");
        StockFrontEnd fakeNews = FrontEndService.getInstance().getRecentStockToFrontEnd("Fake News");
        StockFrontEnd win = FrontEndService.getInstance().getRecentStockToFrontEnd("#win");
        StockFrontEnd saltBae = FrontEndService.getInstance().getRecentStockToFrontEnd("Salt Bae");
        List<StockFrontEnd> ret = ImmutableList.of(trump, fakeNews, win, saltBae);
        return Response.ok(gson.toJson(ret), MediaType.APPLICATION_JSON).build();
    }
}
