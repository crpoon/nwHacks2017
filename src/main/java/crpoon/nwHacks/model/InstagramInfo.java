package crpoon.nwHacks.model;

import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class InstagramInfo {

	private String name;
	private String ticker;
	private int totalMention;
	private Date date;

	/*	CONSTRUCTORS
	 *
	 */
	public InstagramInfo(String name, String ticker, int totalMention, Date date) {
		this.name = name;
		this.ticker = ticker;
		this.totalMention = totalMention;
		this.date = date;
	}

	public InstagramInfo(String name, String ticker, int totalMention, Long date) {
		this.name = name;
		this.ticker = ticker;
		this.totalMention = totalMention;
		this.date = new Date(date);
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
	private void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/*	Name: getTotalMention
	 * 	Param:
	 * 	Return: int
	 * 
	 * 	Purpose: Gets the total mention of this stock
	 */
	public int getTotalMention() {
		return this.totalMention;
	}


	public Date getDate() {
		return this.date;
	}

	public long getDateAsLong(){
		return this.date.getTime();
	}

	public void setTotalMention(int tm) {
		this.totalMention = tm;
	}


	public void setDate(Date d) {
		this.date = d;
	}

}