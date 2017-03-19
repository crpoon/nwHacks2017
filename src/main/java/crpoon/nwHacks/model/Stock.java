package crpoon.nwHacks.model;

import java.util.Date;
import java.util.List;

public class Stock {
	
	private String name;
	private String ticker;
	private Date date;
	private double price;
	private double curIncrease;
	
	/*	CONSTRUCTORS
	 * 
	 */
	public Stock(String name, String ticker, Date date, double price, double curIncrease){
		this.name = name;
		this.ticker = ticker;
		this.date = date;
		this.price = price;
		this.curIncrease = curIncrease;
	}
	
	public Stock(String name, String ticker, Long date, double price, double curIncrease){
		this.name = name;
		this.ticker = ticker;
		this.date = new Date(date);
		this.price = price;
		this.curIncrease = curIncrease;
	}
	
	/*	Name: getName
	 * 	Param:
	 * 	Return: String
	 * 
	 * 	Purpose: Returns the name of the stock
	 */
	public String getName() {
		return this.name;
	}
	
	/*	#### SHOULD NOT BE USED ####
	 * 	Name: setName
	 * 	Param:	String name
	 * 	Return: 
	 * 
	 * 	Purpose: Sets the name of the stock
	 */
	private void setName(String name) {
		this.name = name;
	}
	
	/*	Name: getTicker
	 * 	Param:
	 * 	Return: String
	 * 
	 * 	Purpose: Returns the ticker of the stock
	 */
	public String getTicker() {
		return this.ticker;
	}
	
	/*	#### SHOULD NOT BE USED ####
	 * 	Name: setTicker
	 * 	Param:	String ticker
	 * 	Return: 
	 * 
	 * 	Purpose: Sets the ticker of the stock
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public long getDateAsLong(){
		return this.date.getTime();
	}
	
	public void setDateFromLong(Long date){
		this.date = new Date(date);
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}

	public double getCurIncrease() { return this.curIncrease; };

	public void setCurIncrease(double curIncrease) { this.curIncrease = curIncrease; };

}
