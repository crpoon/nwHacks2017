//package crpoon.nwHacks;
//
//import java.util.Date;
//import java.util.List;
//
//import crpoon.nwHacks.controller.*;
//import crpoon.nwHacks.database.*;
//import crpoon.nwHacks.model.*;
//
//public class StockDaoTester {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		StockDao databaseDao = StockDao.getInstance();
//		
//		List<Stock> allStockInfo = databaseDao.getAllStock();
//		if(allStockInfo == null || allStockInfo.isEmpty()){
//			System.out.println("Initial getAllStock Success");
//		} else {
//			System.out.println("Initial getAllStock Failure");
//		}
//		
//		Stock topkek1 = new Stock("topkek", "TKEK", (long) 1000, 100.0);
//		Stock topkek2 = new Stock("topkek", "TKEK", (long) 1050, 200.0);
//		Stock topkek3 = new Stock("topkek", "TKEK", (long) 1100, 50.0);
//		Stock topkek4 = new Stock("topkek", "TKEK", (long) 1150, 75.0);
//		Stock topkek5 = new Stock("topkek", "TKEK", (long) 1200, 300.0);
//		Stock topkek6 = new Stock("topkek", "TKEK", (long) 1250, 275.0);
//		databaseDao.insertStock(topkek1);
//		databaseDao.insertStock(topkek2);
//		databaseDao.insertStock(topkek3);
//		databaseDao.insertStock(topkek4);
//		databaseDao.insertStock(topkek5);
//		databaseDao.insertStock(topkek6);
//		
//		Stock kappa1 = new Stock("kappa", "KPPA", (long) 1000, 200.0);
//		Stock kappa2 = new Stock("kappa", "KPPA", (long) 1050, 210.0);
//		Stock kappa3 = new Stock("kappa", "KPPA", (long) 1100, 190.0);
//		Stock kappa4 = new Stock("kappa", "KPPA", (long) 1150, 500.0);
//		Stock kappa5 = new Stock("kappa", "KPPA", (long) 1200, 400.0);
//		Stock kappa6 = new Stock("kappa", "KPPA", (long) 1250, 275.0);
//		databaseDao.insertStock(kappa1);
//		databaseDao.insertStock(kappa2);
//		databaseDao.insertStock(kappa3);
//		databaseDao.insertStock(kappa4);
//		databaseDao.insertStock(kappa5);
//		databaseDao.insertStock(kappa6);
//		
//		allStockInfo = databaseDao.getAllStock();
//		if(allStockInfo.size() == 12){
//			System.out.println("Insert 12 Stocks Success");
//		} else {
//			System.out.println("Insert 12 stocks Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByName("kappa");
//		if(allStockInfo.size() == 6){
//			System.out.println("getAllStockByName Success");
//		} else {
//			System.out.println("getAllStockByName Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByTicker("TKEK");
//		if(allStockInfo.size() == 6){
//			System.out.println("getAllStockByTicker Success");
//		} else {
//			System.out.println("getAllStockByTicker Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockAfterDate(new Date(1049));
//		if(allStockInfo.size() == 10){
//			System.out.println("getAllStockAfterDate Success");
//		} else {
//			System.out.println("getAllStockAfterDate Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockBeforeDate(new Date(1049));
//		if(allStockInfo.size() == 2){
//			System.out.println("getAllStockBeforeDate Success");
//		} else {
//			System.out.println("getAllStockBeforeDate Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByNameAfterDate("kappa", new Date(1149));
//		if(allStockInfo.size() == 3){
//			System.out.println("getAllStockByNameAfterDate Success");
//		} else {
//			System.out.println("getAllStockByNameAfterDate Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByNameBeforeDate("kappa", new Date(1001));
//		if(allStockInfo.size() == 1){
//			System.out.println("getAllStockByNameBeforeDate Success");
//		} else {
//			System.out.println("getAllStockByNameBeforeDate Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByTickerAfterDate("KPPA", new Date(1025));
//		if(allStockInfo.size() == 5){
//			System.out.println("getAllStockByTickerAfterDate Success");
//		} else {
//			System.out.println("getAllStockByTickerAfterDate Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByTickerBeforeDate("TKEK", new Date(1101));
//		if(allStockInfo.size() == 3){
//			System.out.println("getAllStockByTickerBeforeDate Success");
//		} else {
//			System.out.println("getAllStockByTickerBeforeDate Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByTickerBeforeDate("TKEK", new Date(1101));
//		if(allStockInfo.size() == 3){
//			System.out.println("getAllStockByTickerBeforeDate Success");
//		} else {
//			System.out.println("getAllStockByTickerBeforeDate Failure");
//		}
//		
//		databaseDao.removeAllStocksByName("kappa");
//		allStockInfo = databaseDao.getAllStock();
//		if(allStockInfo.size() == 6){
//			System.out.println("removeAllStocksByName Success");
//		} else {
//			System.out.println("removeAllStocksByName Failure");
//		}
//		
//		databaseDao.removeAllStocksByTicker("TKEK");
//		allStockInfo = databaseDao.getAllStock();
//		if(allStockInfo == null || allStockInfo.size() == 0){
//			System.out.println("removeAllStocksByTicker Success");
//		} else {
//			System.out.println("removeAllStocksByTicker Failure");
//		}
//		
//		Stock vohiyo1 = new Stock("vohiyo", "VHYO", (long) 1000, 200.0);
//		Stock vohiyo2 = new Stock("vohiyo", "VHYO", (long) 1050, 210.0);
//		Stock vohiyo3 = new Stock("vohiyo", "VHYO", (long) 1100, 190.0);
//		Stock vohiyo4 = new Stock("vohiyo", "VHYO", (long) 1150, 500.0);
//		Stock vohiyo5 = new Stock("vohiyo", "VHYO", (long) 1200, 400.0);
//		Stock vohiyo6 = new Stock("vohiyo", "VHYO", (long) 1250, 275.0);
//		Stock vohiyo7 = new Stock("vohiyo", "VHYO", (long) 1300, 300.0);
//		Stock vohiyo8 = new Stock("vohiyo", "VHYO", (long) 1350, 225.0);
//		databaseDao.insertStock(vohiyo1);
//		databaseDao.insertStock(vohiyo2);
//		databaseDao.insertStock(vohiyo3);
//		databaseDao.insertStock(vohiyo4);
//		databaseDao.insertStock(vohiyo5);
//		databaseDao.insertStock(vohiyo6);
//		databaseDao.insertStock(vohiyo7);
//		databaseDao.insertStock(vohiyo8);
//		
//		Stock giff1 = new Stock("giff diretide", "GIFF", (long) 955, 200.0);
//		Stock giff2 = new Stock("giff diretide", "GIFF", (long) 980, 210.0);
//		Stock giff3 = new Stock("giff diretide", "GIFF", (long) 1155, 190.0);
//		Stock giff4 = new Stock("giff diretide", "GIFF", (long) 1203, 500.0);
//		Stock giff5 = new Stock("giff diretide", "GIFF", (long) 1206, 400.0);
//		Stock giff6 = new Stock("giff diretide", "GIFF", (long) 1207, 275.0);
//		Stock giff7 = new Stock("giff diretide", "GIFF", (long) 1208, 300.0);
//		Stock giff8 = new Stock("giff diretide", "GIFF", (long) 1350, 225.0);
//		databaseDao.insertStock(giff1);
//		databaseDao.insertStock(giff2);
//		databaseDao.insertStock(giff3);
//		databaseDao.insertStock(giff4);
//		databaseDao.insertStock(giff5);
//		databaseDao.insertStock(giff6);
//		databaseDao.insertStock(giff7);
//		databaseDao.insertStock(giff8);
//		
//		
//		allStockInfo = databaseDao.getAllStockBetweenDates(new Date(1101), new Date(1200));
//		if(allStockInfo.size() == 2){
//			System.out.println("getAllStockBetweenDates Success");
//		} else {
//			System.out.println("getAllStockBetweenDates Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockBetweenDates(new Date(1149), new Date(1301));
//		if(allStockInfo.get(0).getTicker().equals("VHYO") && allStockInfo.get(0).getPrice() == 300.0
//		&& allStockInfo.get(1).getTicker().equals("VHYO") && allStockInfo.get(1).getPrice() == 275.0
//		&& allStockInfo.get(2).getTicker().equals("GIFF") && allStockInfo.get(2).getPrice() == 300.0
//		&& allStockInfo.get(3).getTicker().equals("GIFF") && allStockInfo.get(3).getPrice() == 275.0
//		&& allStockInfo.get(4).getTicker().equals("GIFF") && allStockInfo.get(4).getPrice() == 400.0
//		&& allStockInfo.get(5).getTicker().equals("GIFF") && allStockInfo.get(5).getPrice() == 500.0
//		&& allStockInfo.get(6).getTicker().equals("VHYO") && allStockInfo.get(6).getPrice() == 400.0
//		&& allStockInfo.get(7).getTicker().equals("GIFF") && allStockInfo.get(7).getPrice() == 190.0
//		&& allStockInfo.get(8).getTicker().equals("VHYO") && allStockInfo.get(8).getPrice() == 500.0){
//			System.out.println("check descending order Success");
//		} else {
//			System.out.println("check descending order Failure");
//			System.out.println("\t\t\t" + allStockInfo.get(0).getTicker() + " " + allStockInfo.get(0).getPrice());
//			System.out.println("\t\t\t" + allStockInfo.get(1).getTicker() + " " + allStockInfo.get(1).getPrice());
//			System.out.println("\t\t\t" + allStockInfo.get(2).getTicker() + " " + allStockInfo.get(2).getPrice());
//			System.out.println("\t\t\t" + allStockInfo.get(3).getTicker() + " " + allStockInfo.get(3).getPrice());
//			System.out.println("\t\t\t" + allStockInfo.get(4).getTicker() + " " + allStockInfo.get(4).getPrice());
//			System.out.println("\t\t\t" + allStockInfo.get(5).getTicker() + " " + allStockInfo.get(5).getPrice());
//			System.out.println("\t\t\t" + allStockInfo.get(6).getTicker() + " " + allStockInfo.get(6).getPrice());
//			System.out.println("\t\t\t" + allStockInfo.get(7).getTicker() + " " + allStockInfo.get(7).getPrice());
//		}
//		
//		
//		allStockInfo = databaseDao.getAllStockBetweenDates(new Date(1250), new Date(1000));
//		if(allStockInfo.size() == 9){
//			System.out.println("getAllStockBetweenDates Reversed Success");
//		} else {
//			System.out.println("getAllStockBetweenDates Reversed Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByNameBetweenDates("vohiyo", new Date(950), new Date(1200));
//		if(allStockInfo.size() == 4){
//			System.out.println("getAllStockByNameBetweenDates Reversed Success");
//		} else {
//			System.out.println("getAllStockByNameBetweenDates Reversed Failure");
//		}
//		
//		allStockInfo = databaseDao.getAllStockByTickerBetweenDates("GIFF", new Date(960), new Date(1250));
//		if(allStockInfo.size() == 6){
//			System.out.println("getAllStockByTickerBetweenDates Success");
//		} else {
//			System.out.println("getAllStockByTickerBetweenDates Failure");
//		}
//		
//		Stock result = databaseDao.getStockByNameAndDate("vohiyo", new Date(1200));
//		if(result.getTicker().equals("VHYO") && result.getPrice() == 400.0){
//			System.out.println("getStockByNameAndDate Success");
//		} else {
//			System.out.println("getStockByNameAndDate Failure");
//		}
//		
//		result = databaseDao.getStockByTickerAndDate("GIFF", new Date(980));
//		if(result.getName().equals("giff diretide") && result.getPrice() == 210.0){
//			System.out.println("getStockByTickerAndDate Success");
//		} else {
//			System.out.println("getStockByTickerAndDate Failure");
//		}
//		
//		databaseDao.removeAllStocks();
//		allStockInfo = databaseDao.getAllStock();
//		if(allStockInfo == null || allStockInfo.size() == 0){
//			System.out.println("removeAllStocks Success");
//		} else {
//			System.out.println("removeAllStocks Failure");
//		}
//	}
//
//}
