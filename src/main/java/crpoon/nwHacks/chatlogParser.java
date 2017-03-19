package crpoon.nwHacks;

import java.io.File;
import java.util.Scanner;

import crpoon.nwHacks.database.StockInfoDao;

public class chatlogParser {
	private static chatlogParser instance;
	
	private chatlogParser() {
	}
	
	public static chatlogParser getInstance() {
		if (instance == null) {
			instance = new chatlogParser();
		}
		return instance;
	}
	
	public int memeCount(String filepath, String meme){
		int count = 0;
		
		File file = new File(filepath);
		if(file.exists() && !file.isDirectory()) { 
		    // do something
		}

		return count;
	}
}
