package crpoon.nwHacks.controller;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import crpoon.nwHacks.model.StockFrontEnd;
import crpoon.nwHacks.service.FrontEndService;
import crpoon.nwHacks.service.TwitchFrontEndService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crpoon on 2017-03-19.
 */
@Path("twitch/")
public class TwitchController {

    public TwitchController() {
    }

    @GET
    @Path("stocks/")
    public Response getHistorical() {
        System.out.println("Getting History for Twitch");
        Gson gson = new Gson();
        List<StockFrontEnd> kappa = TwitchFrontEndService.getInstance().getStocksToFrontEnd("kappa");
        List<StockFrontEnd> vohiyo = TwitchFrontEndService.getInstance().getStocksToFrontEnd("vohiyo");
        List<StockFrontEnd> minglee = TwitchFrontEndService.getInstance().getStocksToFrontEnd("minglee");
        List<StockFrontEnd> pogchamp = TwitchFrontEndService.getInstance().getStocksToFrontEnd("pogchamp");
        List<StockFrontEnd> biblethump = TwitchFrontEndService.getInstance().getStocksToFrontEnd("biblethump");
        List<StockFrontEnd> fourhead = TwitchFrontEndService.getInstance().getStocksToFrontEnd("4head");
        List<StockFrontEnd> salt = TwitchFrontEndService.getInstance().getStocksToFrontEnd("salt");
        List<StockFrontEnd> trigger = TwitchFrontEndService.getInstance().getStocksToFrontEnd("trigger");

        List<List<StockFrontEnd>> ret = new ArrayList<>();
        ret.add(kappa);
        ret.add(vohiyo);
        ret.add(minglee);
        ret.add(pogchamp);
        ret.add(biblethump);
        ret.add(fourhead);
        ret.add(salt);
        ret.add(trigger);

        return Response.ok(gson.toJson(ret), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Path("stocks/recent")
    public Response getRecent() {
        System.out.println("Getting Recent for Twitch");
        Gson gson = new Gson();
        StockFrontEnd kappa = FrontEndService.getInstance().getRecentStockToFrontEnd("kappa");
        StockFrontEnd vohiyo = FrontEndService.getInstance().getRecentStockToFrontEnd("vohiyo");
        StockFrontEnd minglee = FrontEndService.getInstance().getRecentStockToFrontEnd("minglee");
        StockFrontEnd pogchamp = FrontEndService.getInstance().getRecentStockToFrontEnd("pogchamp");
        StockFrontEnd biblethump = FrontEndService.getInstance().getRecentStockToFrontEnd("biblethump");
        StockFrontEnd fourhead = FrontEndService.getInstance().getRecentStockToFrontEnd("4head");
        StockFrontEnd salt = FrontEndService.getInstance().getRecentStockToFrontEnd("salt");
        StockFrontEnd trigger = FrontEndService.getInstance().getRecentStockToFrontEnd("trigger");
        List<StockFrontEnd> ret = ImmutableList.of(kappa, vohiyo, minglee, pogchamp,
                biblethump, fourhead, salt, trigger);
        return Response.ok(gson.toJson(ret), MediaType.APPLICATION_JSON)
                .header("Access-Control-Allow-Origin", "*").build();
    }
}
