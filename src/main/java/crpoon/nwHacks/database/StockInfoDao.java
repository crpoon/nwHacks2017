package crpoon.nwHacks.database;

import crpoon.nwHacks.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class StockInfoDao {
	private static StockInfoDao instance;
	
	private static String connectionString =
    		"jdbc:sqlserver://memestock.database.windows.net:1433;"
    		+ "database=MEMESTOCK;"
    		+ "user=sojuiswater@memestock;"
    		+ "password=JustifiablePolham2017;"
    		+ "encrypt=true;"
    		+ "trustServerCertificate=false;"
    		+ "hostNameInCertificate=*.database.windows.net;"
    		+ "loginTimeout=30;";
	
	private StockInfoDao() {
	}
	
	public static StockInfoDao getInstance() {
		if (instance == null) {
			instance = new StockInfoDao();
		}
		return instance;
	}

	public boolean checkConnectionExists(){
		Connection connection = null;
		boolean connectionExist  = true;
		try {  
			connection = DriverManager.getConnection(connectionString);  
		}  
        catch (Exception e) {  
            e.printStackTrace();
            connectionExist = false;
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        } 
		return connectionExist;
	}
	
	public List<StockInfo> getAllStockInfo(){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<StockInfo> listOfStocks = new ArrayList<StockInfo>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from StockInfo";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
            	System.out.println(resultSet.getString("StockName") + " " + resultSet.getString("StockTicker") + " " + resultSet.getString("ImgUrl"));
            	List<String> sectorList = Arrays.asList(resultSet.getString("Sector").trim().split("\\s+"));
                listOfStocks.add(new StockInfo(resultSet.getString("StockName"),
                							   resultSet.getString("StockTicker"),
                							   sectorList,
                							   resultSet.getString("ImgUrl")));
            } 
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return listOfStocks;
	}
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
