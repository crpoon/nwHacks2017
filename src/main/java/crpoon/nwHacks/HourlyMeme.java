package crpoon.nwHacks;

import java.util.Date;

public class HourlyMeme {
	private Date hour;
	private String meme;
	private int count;
	
	public HourlyMeme(Date hour, String meme, int count){
		this.hour = hour;
		this.meme = meme;
		this.count = count;
	}
	
	public HourlyMeme(Date hour, String meme, Integer integer){
		this.hour = hour;
		this.meme = meme;
		this.count = integer;
	}
	
	public Date getDate(){
		return this.hour;
	}
	
	public String getMeme(){
		return this.meme;
	}
	
	public int getCount(){
		return this.count;
	}
}
