package crpoon.nwHacks.model;

import java.util.List;

public class StockInfo {
	
	private String name;
	private String ticker;
	private List<String> sectorIds;
	private String imgUrl;
	
	/*	CONSTRUCTORS
	 * 
	 */
	public StockInfo(String name, String ticker, List<String> sectorIds, String imgUrl){
		this.name = name;
		this.ticker = ticker;
		this.sectorIds = sectorIds;
		this.imgUrl = imgUrl;
	}
	
	/*	#### INCOMPLETE ####
	 * 		Implement Database Portion
	 * 		Check if info exists
	 * 
	 * Name: getStockInfoByName
	 * 	Param: String name
	 * 	Return: StockInfo
	 * 
	 * 	Purpose: Given a database name,
	 * 			 retrieve the stock info
	 * 			 from the database.
	 */
	public static StockInfo getStockInfoByName(String name){
		String db_ticker = null;
		List<String> db_sectorIds = null;
		String db_imgUrl = null;

		return new StockInfo(name, db_ticker, db_sectorIds, db_imgUrl);
	}

	/*	#### INCOMPLETE ####
	 * 		Implement Database Portion
	 * 		Check if info exists
	 * 
	 * Name: getStockInfoByTicker
	 * 	Param: String ticker
	 * 	Return: StockInfo
	 * 
	 * 	Purpose: Given a database ticker,
	 * 			 retrieve the stock info
	 * 			 from the database.
	 */
	public static StockInfo getStockInfoByTicker(String ticker){
		String db_name = null;
		List<String> db_sectorIds = null;
		String db_imgUrl = null;

		return new StockInfo(db_name, ticker, db_sectorIds, db_imgUrl);
	}
	
	/*	Name: getName
	 * 	Param:
	 * 	Return: String
	 * 
	 * 	Purpose: Returns the name of the stock
	 */
	public String getName() {
		return name;
	}
	
	/*	Name: setName
	 * 	Param:	String name
	 * 	Return: 
	 * 
	 * 	Purpose: Sets the name of the stock
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*	Name: getTicker
	 * 	Param:
	 * 	Return: String
	 * 
	 * 	Purpose: Returns the ticker of the stock
	 */
	public String getTicker() {
		return ticker;
	}

	/*	Name: setTicker
	 * 	Param:	String ticker
	 * 	Return: 
	 * 
	 * 	Purpose: Sets the ticker of the stock
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	/*	Name: getSectorIds
	 * 	Param:
	 * 	Return: List<String> 
	 * 
	 * 	Purpose: Gets the sector ids of the stock
	 */
	public List<String> getSectorIds() {
		return sectorIds;
	}
	
	/*	Name: setSectorIds
	 * 	Param: List<String> sectorIds
	 * 	Return: 
	 * 
	 * 	Purpose: Sets the sector ids of the stock
	 */
	public void setSectorIds(List<String> sectorIds) {
		this.sectorIds = sectorIds;
	}
	
	/* #### INCOMPLETE ####
	 * 	May need to add functionality to update DB
	 * 
	 * Name: addSectorId
	 * Param: String newSectorId
	 * Return: Void
	 * 
	 * Purpose: Adds a newSectorId to the existing list.
	 * 			It will only add if the sectorID does not exist
	 * 			in the list already
	 */
	public void addSectorId(String newSectorId){
		for(int i=0; i<sectorIds.size(); i++){
			if(newSectorId == sectorIds.get(i)){
				return;
			}
		}
		sectorIds.add(newSectorId);
		return;
	}
	
	/* #### INCOMPLETE ####
	 * 		May need to add functionality to update DB
	 * 
	 * Name: removeSectorId
	 * Param: String removeId
	 * Return: Void
	 * 
	 * Purpose: Removes the sectorId from the existing list
	 * 			if it is found.
	 */
	public void removeSectorId(String removeId){
		for(int i=0; i<sectorIds.size(); i++){
			if(removeId == sectorIds.get(i)){
				sectorIds.remove(i);
				return;
			}
		}
		return;
	}
		
	/*	Name: getImgUrl
	 * 	Params:
	 * 	Return: String
	 * 
	 *	Purpose: Returns the URL to stock's
	 *			 image/icon
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/*	Name: setImgUrl
	 * 	Params: String imgUrl
	 * 	Return:
	 * 
	 *	Purpose: Set a different URL
	 *			 to use as the stock's 
	 *			 image/icon
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
	
	/* #############################
	 * DATABASE FUNCTIONS
	 * 
	 * Past this point are a set of
	 *  database functions.
	 * #############################
	 */
	
	/*	#### INCOMPLETE ####
	 * 
	 *  Name: db_StockNameExists
	 * 	Params:	String name
	 * 	Return: boolean
	 * 
	 * 	Purpose: Checks to see if stock exists
	 * 			 given the stock name
	 */
	public static boolean db_StockNameExists(String name){
		return true;
	}
	
	/*	#### INCOMPLETE ####
	 * 
	 *  Name: db_StockTickerExists
	 * 	Params:	String ticker
	 * 	Return: boolean
	 * 
	 * 	Purpose: Checks to see if stock exists
	 * 			 given the stock ticker
	 */
	public static boolean db_StockTickerExists(String ticker){
		return true;
	}
	
	/*	Name: db_getImgURL
	 * 	Params:
	 * 	Return: String
	 * 
	 * 	Purpose: Gets the image URL from the database
	 */
	private String db_getImgURL(){
		return null;
	}
	
	/*	Name: db_setImgUrl
	 * 	Params: String imgUrl
	 * 	Return: 
	 * 
	 * 	Purpose: Updates the database to 
	 * 			 the new URL
	 */
	private void db_setImgUrl(String imgUrl){
		return;
	}
}
