package crpoon.nwHacks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import crpoon.nwHacks.*;

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
	
	public List<HourlyMeme> memeCount(String filepath, List<String> memes) throws IOException{
		List<HourlyMeme> resultCount = null;
		File file = new File(filepath);
		if(file.exists() && !file.isDirectory()) {
			resultCount = new ArrayList<HourlyMeme>();
			FileReader in = new FileReader(filepath);
		    BufferedReader br = new BufferedReader(in);
		    String line;
		    Map<String, Integer> memeCount = new HashMap<>();
		    resetMemeCount(memeCount, memes);
		    Date currentHour = new Date(0);
		    while ((line = br.readLine()) != null) {
		    	// Parse Line to find hour current DateHour
		    	
		    	// Check if line's datehour is greater than current hour
		    	if( currentHour.getTime() < 0){
		    	//	output counts into the hourly meme list
		    		for(int i=0; i<memes.size(); i++){
		    			resultCount.add(new HourlyMeme(currentHour, memes.get(i), memeCount.get(memes.get(i))));
		    		}
		    	//  reset meme count
		    	resetMemeCount(memeCount, memes);
		    	//  set current hour
		    	}
		    	
		    	line = line.toLowerCase();
		    	
		    	for(int i=0; i<memes.size(); i++){
		    		// For each meme count occurances
		    		int appearanceCount = findMemesInString(memes.get(i), line);
		    		// Increment memecount
		    		memeCount.put(memes.get(i), memeCount.get(memes.get(i) + appearanceCount));
		    	}
		    }
		    br.close();
		}
		
		return resultCount;
	}
	
	public void resetMemeCount(Map<String, Integer> memeCount, List<String> memes){
		for(int i=0; i<memes.size(); i++){
			memeCount.put(memes.get(i), 0);
		}
		return;
	}
	
	public int findMemesInString(String meme, String line){
		// grep appearances of meme in line
		int index = line.indexOf(meme.toLowerCase());
		int appearanceCount = 0;
		while (index != -1) {
			appearanceCount++;
		    line = line.substring(index + 1);
		    index = line.indexOf(meme.toLowerCase());
		}
		return appearanceCount;
	}
}
