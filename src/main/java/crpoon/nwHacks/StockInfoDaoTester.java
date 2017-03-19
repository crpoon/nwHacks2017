package crpoon.nwHacks;

import java.util.List;

import crpoon.nwHacks.controller.*;
import crpoon.nwHacks.database.*;
import crpoon.nwHacks.model.*;

public class StockInfoDaoTester {
	public static void main(String[] args) { 
		StockInfoDao databaseDao = StockInfoDao.getInstance();
		
		List<StockInfo> allStockInfo = databaseDao.getAllStockInfo();
		if(allStockInfo == null || allStockInfo.isEmpty()){
			System.out.println("Initial getAllStockInfo Success");
		} else {
			System.out.println("Initial getAllStockInfo Failure");
		}
		
		databaseDao.insertStockInfo(new StockInfo("Hi", "HELLO", "facebook", "amazonSomwhere"));
		databaseDao.insertStockInfo(new StockInfo("yolo", "YOLO", "twitter", "azure"));
		
		allStockInfo = databaseDao.getAllStockInfo();
		if(allStockInfo.size() == 2){
			System.out.println("Insert 2 StockInfo Success");
		} else {
			System.out.println("Insert 2 StockInfo Failure");
		}
		
		StockInfo result = databaseDao.getStockInfoByTicker("YOLO");
		if(result.getName().equals("yolo")){
			System.out.println("result name = yolo Success");
		} else {
			System.out.println("result name = yolo Failure");
		}
		if(result.getSectorIdsAsString().equals("twitter")){
			System.out.println("result sectorid = twitter Success");
		} else {
			System.out.println("result sectorid = twitter Failure");
			System.out.println("\t\t sectorid is " + result.getSectorIdsAsList().get(0));
		}
		if(result.getImgUrl().equals("azure")){
			System.out.println("result getImgUrl = azure Success");
		} else {
			System.out.println("result getImgUrl = azure Failure");
		}
		
		result.setImgUrl("youtube");
		if(result.getImgUrl().equals("youtube")){
			System.out.println("after youtube update Success");
		} else {
			System.out.println("after youtubeupdate Failure");
		}
		
		databaseDao.updateStockInfo(result);
		result = databaseDao.getStockInfoByName("yolo");
		if(result.getImgUrl().equals("azure")){
			System.out.println("after youtube update doesn't equal azure Failure");
		} else {
			System.out.println("after youtube update doesn't equal azure Success");
		}
		if(result.getImgUrl().equals("youtube")){
			System.out.println("after database update equals youtube Success");
		} else {
			System.out.println("after database update quals youtube Failure");
		}
		
		databaseDao.removeStockInfoByName("yolo");
		databaseDao.removeStockInfoByTicker("HELLO");
		allStockInfo = databaseDao.getAllStockInfo();
		if(allStockInfo == null || allStockInfo.isEmpty()){
			System.out.println("FInal getallstockinfo Success");
		} else {
			System.out.println("Final getAllStockInfo Failure " + allStockInfo.size());
		}
	}
}