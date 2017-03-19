package crpoon.nwHacks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import crpoon.nwHacks.database.StockDao;
import crpoon.nwHacks.database.StockInfoDao;
import crpoon.nwHacks.model.Stock;
import crpoon.nwHacks.model.StockInfo;

public class PopulateTwitchDatabase {
	
	public static void main(String[] args) throws IOException{
		chatlogParser parser = chatlogParser.getInstance();
		StockDao databaseDao = StockDao.getInstance();
		StockInfoDao stockinfodao = StockInfoDao.getInstance();
		
		String userDir = System.getProperty("user.dir");
		System.out.println(System.getProperty("user.dir"));
		String folderName = userDir + "/TwitchChat";
		
		List<File> files = new ArrayList<File>();
		listf(folderName, files);
		
		String[] list = {"kappa", "vohiyo", "minglee", "pogchamp", "biblethump", "4head", "salt", "trigger"};
		List<String> memeList = Arrays.asList(list);
		Map<String, String> memeTicker = new HashMap<>();
		memeTicker.put("kappa", "KPPA");
		memeTicker.put("vohiyo", "VHYO");
		memeTicker.put("minglee", "MNLE");
		memeTicker.put("pogchamp", "PGCHMP");
		memeTicker.put("biblethump", "BBTHMP");
		memeTicker.put("4head", "FRHD");
		memeTicker.put("salt", "SALT");
		memeTicker.put("trigger", "TRGR");
		
		//stockinfodao.insertStockInfo(new StockInfo("kappa", "KPPA", "Twitch", ""));
		//stockinfodao.insertStockInfo(new StockInfo("vohiyo", "VHYO", "Twitch", ""));
		//stockinfodao.insertStockInfo(new StockInfo("pogchamp", "PGCHMP", "Twitch", ""));
		//stockinfodao.insertStockInfo(new StockInfo("biblethump", "BBTHMP", "Twitch", ""));
		//stockinfodao.insertStockInfo(new StockInfo("kreygasm", "KGSM", "Twitch", ""));
		//stockinfodao.insertStockInfo(new StockInfo("4head", "FRHD", "Twitch", ""));
		//stockinfodao.insertStockInfo(new StockInfo("salt", "SALT", "Twitch", ""));
		//stockinfodao.insertStockInfo(new StockInfo("trigger", "TRGR", "Twitch", ""));
		
		databaseDao.removeAllStocksByName("kappa");
		databaseDao.removeAllStocksByName("vohiyo");
		databaseDao.removeAllStocksByName("minglee");
		databaseDao.removeAllStocksByName("elegiggle");
		databaseDao.removeAllStocksByName("kreygasm");
		databaseDao.removeAllStocksByName("pogchamp");
		databaseDao.removeAllStocksByName("biblethump");
		databaseDao.removeAllStocksByName("4head");
		databaseDao.removeAllStocksByName("salt");
		databaseDao.removeAllStocksByName("trigger");
		
		// Parse Twitch Chatlogs
		
		for(int i=0; i<files.size(); i++){
			System.out.println(files.get(i).getAbsolutePath());
			// Find memes in chatlogs
			List<HourlyMeme> result = parser.memeCount(files.get(i).getAbsolutePath(), memeList);
			for(int j=0; j<result.size(); j++){
				HourlyMeme current = result.get(j);
				Stock databaseStock = databaseDao.getStockByNameAndDate(current.getMeme(), current.getDate());
				if(databaseStock == null){
					Stock newStock = new Stock(current.getMeme(), memeTicker.get(current.getMeme()), current.getDate(), current.getCount(), 0);
					databaseDao.insertStock(newStock);
				} else {
					Stock newStock = new Stock(current.getMeme(), memeTicker.get(current.getMeme()), current.getDate(), current.getCount() + databaseStock.getPrice(), 0);
					databaseDao.updateStockPrice(newStock);
				}
				
			}
			System.out.println("Finished" + files.get(i).getAbsolutePath());
			
		}
		
		System.out.println("Finished Populating");
		
		for(int i=0; i<memeList.size(); i++){
			databaseDao.removeStockByNameAndDate(memeList.get(i), new Date(0));
			List<Stock> stockhistory = databaseDao.getAllStockByName(memeList.get(i));
			
			Stock previousStock = stockhistory.get(stockhistory.size()-1);
			previousStock.setCurIncrease(previousStock.getPrice() - 100);
			stockhistory.get(stockhistory.size()-1).setPrice(100);
			double previousPrice = 100;
			double previousCount = previousStock.getCurIncrease();
			for(int j=stockhistory.size()-2; j>=0; j--){
				previousStock = stockhistory.get(j);
				double currentCount = previousStock.getPrice();
				
				double diff = currentCount - previousCount;
				previousStock.setPrice( diff / Math.max(currentCount, previousCount)* 10+ previousPrice);
				
				previousCount = previousStock.getCurIncrease();
				previousPrice = previousStock.getPrice();
			}
			
			for(int j=0; j<stockhistory.size(); j++){
				databaseDao.updateStockPrice(stockhistory.get(j));
			}
		}
		
		System.out.println("Finished All!");
	}
	
	public static void listf(String directoryName, List<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}
}

