package crpoon.nwHacks.model;

import java.util.List;

public class Stock {
	
	private String name;
	private String ticker;
	private List<String> sectorIds;
	private String price;
	private String imgUrl;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public List<String> getSectorIds() {
		return sectorIds;
	}
	
	public void setSectorIds(List<String> sectorIds) {
		this.sectorIds = sectorIds;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
