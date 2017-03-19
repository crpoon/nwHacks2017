package crpoon.nwHacks.model;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	public StockInfo(String name, String ticker, String sectorIds, String imgUrl){
		this.name = name;
		this.ticker = ticker;
		List<String> result = Arrays.asList(sectorIds.trim().toLowerCase().split("\\s+"));
		this.sectorIds = result;
		this.imgUrl = imgUrl;
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
		return ticker;
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
	
	/*	Name: getSectorIdsAsList
	 * 	Param:
	 * 	Return: List<String> 
	 * 
	 * 	Purpose: Gets the sector ids of the stock
	 */
	public List<String> getSectorIdsAsList() {
		return sectorIds;
	}
	
	/*	Name: getSectorIdsAsString
	 * 	Param:
	 * 	Return: String
	 * 
	 * 	Purpose: Gets the sector ids of the stock
	 * 			as a single string with each sector
	 * 			separated by a whitespace.
	 */
	public String getSectorIdsAsString(){
		String result = new String();
		for(int i=0; i<sectorIds.size(); i++){
			result = result + sectorIds.get(i) + " ";
		}
		return result.trim();
	}
	
	/*	Name: setSectorIdsAsList
	 * 	Param: List<String> sectorIds
	 * 	Return: 
	 * 
	 * 	Purpose: Goes through the list and make them all lowercase.
	 * 			Then sets the sector ids of the stock.
	 */
	public void setSectorIdsAsList(List<String> sectorIds) {
		for(int i=0; i<sectorIds.size(); i++){
			sectorIds.set(i, sectorIds.get(i).toLowerCase());
		}
		this.sectorIds = sectorIds;
	}
	
	/*	Name: setSectoridsAsString
	 * 	Param: String sectorIds
	 * 	Return: 
	 * 
	 * 	Purpose: Given an input string, makes string lowercase,
	 * 			separate by whitespace and set the resultant list
	 * 			as this stocks' sectorId
	 */
	public void setSectorIdsAsString(String sectorIds){
		List<String> result = Arrays.asList(sectorIds.trim().toLowerCase().split("\\s+"));
		this.sectorIds = result;
	}

	/* Name: addSectorId
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
	
	/* Name: removeSectorId
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

}
