package crpoon.nwHacks.database;

import crpoon.nwHacks.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;
//import com.microsoft.sqlserver.jdbc.*;

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
	
	/*	Name: getAllStockInfo()
	 * 	Params:
	 * 	Return: List<StockInfo>
	 * 
	 * 	Purpose: Queries and returns all stocks and 
	 * 			 their info from the database.
	 */
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
            	//System.out.println(resultSet.getString("StockName") + " " + resultSet.getString("StockTicker") + " " + resultSet.getString("ImgUrl"));
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
	
	/*Name: getStockInfoByName
	 * 	Params:	String name
	 * 	Return: StockInfo
	 * 
	 * 	Purpose: Returns StockInfo given the name,
	 * 			 if it does not exist, return null
	 */
	public StockInfo getStockInfoByName(String name){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        StockInfo result = null;
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from StockInfo WHERE StockName='" + name + "'";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
            	if(name.equals(resultSet.getString("StockName"))){
            		List<String> sectorList = Arrays.asList(resultSet.getString("Sector").trim().split("\\s+"));
                    result = new StockInfo(resultSet.getString("StockName"),
                    							   resultSet.getString("StockTicker"),
                    							   sectorList,
                    							   resultSet.getString("ImgUrl"));
                    break;
            	}
            }
            
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return result;
	}
	
	/*	Name: getStockInfoByTicker
	 * 	Params:	String name
	 * 	Return: StockInfo
	 * 
	 * 	Purpose: Returns StockInfo given the ticker,
	 * 			 if it does not exist, return null
	 */
	public StockInfo getStockInfoByTicker(String ticker){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        StockInfo result = null;
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from StockInfo WHERE StockTicker='" + ticker + "'";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
            	if(ticker.equals(resultSet.getString("StockTicker"))){
            		List<String> sectorList = Arrays.asList(resultSet.getString("Sector").trim().split("\\s+"));
                    result = new StockInfo(resultSet.getString("StockName"),
                    							   resultSet.getString("StockTicker"),
                    							   sectorList,
                    							   resultSet.getString("ImgUrl"));
                    break;
            	}
            }
            
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return result;
	}
	
	/*	Name: insertStockInfo
	 * 	Params: StockInfo stock
	 * 	Return:
	 * 
	 * 	Purpose: Inserts a stock into the StockInfo table
	 */
	public void insertStockInfo(StockInfo stock){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String insertSql = "INSERT INTO StockInfo (StockName, StockTicker, Sector, ImgUrl)"
            		+ " VALUES ('" + stock.getName() + "', "
					+ "'" + stock.getTicker() + "', "
					+ "'" + stock.getSectorIdsAsString() + "', "
					+ "'" + stock.getImgUrl() + "');";
			statement = connection.createStatement();  
            statement.executeUpdate(insertSql);
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
	}
	
	/*	Name: updateStockInfo
	 * 	Params: StockInfo stock
	 * 	Return: 
	 * 
	 * 	Purpose: Updates the database with information about
	 * 			the StockInfo stock
	 */
	public void updateStockInfo(StockInfo stock){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String updateSql = "UPDATE StockInfo SET "
					+ "Sector = '" + stock.getSectorIdsAsString() + "', "
					+ "ImgUrl = '" + stock.getImgUrl() + "' "
            		+ "WHERE StockName='" + stock.getName() + "';";
			statement = connection.createStatement();  
            statement.executeUpdate(updateSql);
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
	}

	/*	Name: removeStockInfo
	 * 	Params: StockInfo stock
	 * 	Return: 
	 * 
	 * 	Purpose: Removes the StockInfo stock
	 * 			from the database.
	 */
	public void removeStockInfo(StockInfo stock){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM StockInfo "
            		+ "WHERE StockName='" + stock.getName() + "';";
			statement = connection.createStatement();  
            statement.executeUpdate(deleteSql);
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
	}
	
	/*	Name: removeStockInfoByName
	 * 	Params: String name
	 * 	Return: 
	 * 
	 * 	Purpose: Given String name,
	 * 			Removes the StockInfo with StockName=name
	 * 			from the database.
	 */
	public void removeStockInfoByName(String name){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM StockInfo "
            		+ "WHERE StockName='" + name + "';";
			statement = connection.createStatement();  
            statement.executeUpdate(deleteSql);
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
	}
	
	/*	Name: removeStockInfoByTicker
	 * 	Params: String ticker
	 * 	Return: 
	 * 
	 * 	Purpose: Given String ticker,
	 * 			Removes the StockInfo with StockTicker=ticker
	 * 			from the database.
	 */
	public void removeStockInfoByTicker(String ticker){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM StockInfo "
            		+ "WHERE StockTicker='" + ticker + "';";
			statement = connection.createStatement();  
            statement.executeUpdate(deleteSql);
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
	}
}
