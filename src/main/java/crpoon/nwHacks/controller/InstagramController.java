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


@Path("instagram/")
public class InstagramController {
	
	public InstagramController() {
	}

	@GET
	@Path("stocks/")
	public Response getHistorical() {
		System.out.println("Getting History for Instagram");
		Gson gson = new Gson();
		List<StockFrontEnd> blessed = FrontEndService.getInstance().getStocksToFrontEnd("Blessed");
		List<StockFrontEnd> noFilter = FrontEndService.getInstance().getStocksToFrontEnd("No_Filter");
		List<StockFrontEnd> igers = FrontEndService.getInstance().getStocksToFrontEnd("Igers");
		List<StockFrontEnd> selfies = FrontEndService.getInstance().getStocksToFrontEnd("Selfies");

		List<List<StockFrontEnd>> ret = new ArrayList<>();
		ret.add(blessed);
		ret.add(noFilter);
		ret.add(igers);
		ret.add(selfies);

		return Response.ok(gson.toJson(ret), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("stocks/recent")
	public Response getRecent() {
		System.out.println("Getting Recent for Instagram");
		Gson gson = new Gson();
		StockFrontEnd blessed = FrontEndService.getInstance().getRecentStockToFrontEnd("Blessed");
		StockFrontEnd noFilter = FrontEndService.getInstance().getRecentStockToFrontEnd("No_Filter");
		StockFrontEnd igers = FrontEndService.getInstance().getRecentStockToFrontEnd("Igers");
		StockFrontEnd selfies = FrontEndService.getInstance().getRecentStockToFrontEnd("Selfies");
		List<StockFrontEnd> ret = ImmutableList.of(blessed, noFilter, igers, selfies);
		return Response.ok(gson.toJson(ret), MediaType.APPLICATION_JSON).build();
	}

}
