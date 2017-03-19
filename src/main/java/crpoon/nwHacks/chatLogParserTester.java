package crpoon.nwHacks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import crpoon.nwHacks.*;
import crpoon.nwHacks.model.Stock;

public class chatLogParserTester {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		chatlogParser parser = chatlogParser.getInstance();
		
		String userDir = System.getProperty("user.dir");
		System.out.println(System.getProperty("user.dir"));
		String filename = userDir + "/resources/2017-03-18.txt";
		
		String[] list = {"kappa", "vohiyo", "elegiggle", "minglee", "kreygasm"};
		Map<String, String> memeTicker = new HashMap<>();
		memeTicker.put("kappa", "KPPA");
		memeTicker.put("vohiyo", "VHYO");
		memeTicker.put("elegiggle", "ELGL");
		memeTicker.put("minglee", "MNLE");
		memeTicker.put("kreygasm", "KGSM");
		
		
		List<String> memeList = Arrays.asList(list);
		List<HourlyMeme> result = parser.memeCount(filename, memeList);
		for(int i=0; i<result.size(); i++){
			HourlyMeme current = result.get(i);
			Stock newStock = new Stock(current.getMeme(), memeTicker.get(current.getMeme()), current.getDate(), current.getCount(), 0);
		}
		
		System.out.println("Done!");
	}

}
