package crpoon.nwHacks.database;

import crpoon.nwHacks.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.sql.*;

public class StockDao {
	private static StockDao instance;
	
	private static String connectionString =
    		"jdbc:sqlserver://memestock.database.windows.net:1433;"
    		+ "database=MEMESTOCK;"
    		+ "user=sojuiswater@memestock;"
    		+ "password=JustifiablePolham2017;"
    		+ "encrypt=true;"
    		+ "trustServerCertificate=false;"
    		+ "hostNameInCertificate=*.database.windows.net;"
    		+ "loginTimeout=30;";
	
	private StockDao() {
	}
	
	public static StockDao getInstance() {
		if (instance == null) {
			instance = new StockDao();
		}
		return instance;
	}

	/*	Name: getAllStock
	 * 	Params:
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all stocks and 
	 * 			 their info from the database.
	 */
	public List<Stock> getAllStock(){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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

	/*	Name: getAllStockByName
	 * 	Params: String name
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all stocks
	 * 			 with StockName = name
	 */
	public List<Stock> getAllStockByName(String name){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock "
					+ "WHERE StockName = '" + name + "';";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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

	/*	Name: getAllStockByTicker
	 * 	Params: String ticker
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all stocks
	 * 			 with StockTicker = ticker
	 */
	public List<Stock> getAllStockByTicker(String ticker){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock "
					+ "WHERE StockTicker = '" + ticker + "';";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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

	/*	Name: getAllStockAfterDate
	 * 	Params: Date date
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all stocks after
	 * 			 given date
	 */
	public List<Stock> getAllStockAfterDate(Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE StockDate>" + date.getTime();
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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
	
	/*	Name: getAllStockBeforeDate
	 * 	Params:	Date date
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all stocks before
	 * 			 given date
	 */
	public List<Stock> getAllStockBeforeDate(Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE StockDate<" + date.getTime();
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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
	
	/*	Name: getAllStockByNameAfterDate
	 * 	Params:	String name, Date date
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all name stocks after
	 * 			 given date
	 */
	public List<Stock> getAllStockByNameAfterDate(String name, Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE "
					+ "StockName='" + name + "' AND "
					+ "StockDate>" + date.getTime();
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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
	
	/*	Name: getAllStockByNameBeforeDate
	 * 	Params:	String name, Date date
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all namestocks before
	 * 			 given date
	 */
	public List<Stock> getAllStockByNameBeforeDate(String name, Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE "
					+ "StockName='" + name + "' AND "
					+ "StockDate<" + date.getTime();
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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

	/*	Name: getAllStockByTickerAfterDate
	 * 	Params:	String ticker, Date date
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all ticker stocks after
	 * 			 given date
	 */
	public List<Stock> getAllStockByTickerAfterDate(String ticker, Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE "
					+ "StockTicker='" + ticker + "' AND "
					+ "StockDate>" + date.getTime();
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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
	
	/*	Name: getAllStockByTickerBeforeDate
	 * 	Params:	String ticker, Date date
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all ticker stocks before
	 * 			 given date
	 */
	public List<Stock> getAllStockByTickerBeforeDate(String ticker, Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE "
					+ "StockTicker='" + ticker + "' AND "
					+ "StockDate<" + date.getTime();
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                listOfStocks.add(new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price")));
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
	
	/*	Name: insertStock
	 * 	Params: Stock stock
	 * 	Return: 
	 * 
	 * 	Params: Insert given stock into database
	 */
	public void insertStock(Stock stock){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String insertSql = "INSERT INTO Stock (StockName, StockTicker, StockDate, Price)"
            		+ " VALUES ('" + stock.getName() + "', "
					+ "'" + stock.getTicker() + "', "
					+ stock.getDateAsLong() + ", "
					+ stock.getPrice() + ");";
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

	/*	#### SHOULD NOT BE USED ####
	 * 
	 * 	Name: updateStockPrice
	 * 	Params: Stock stock
	 * 	Return: 
	 * 
	 * 	Params: Update existing stock price by name and date
	 */
	public void updateStockPrice(Stock stock){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String updateSql = "UPDATE Stock SET "
					+ "Price = '" + stock.getPrice() + "' "
            		+ "WHERE StockName='" + stock.getName() + "' "
            		+ "AND StockDate=" + stock.getDateAsLong() + ";";
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

	/*	Name: removeAllStocks
	 * 	Params: Stock stock
	 * 	Return:
	 * 
	 * 	Params: Remove all stocks by name
	 */
	public void removeAllStocks(Stock stock){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM Stock "
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
	
	/*	Name: removeAllStocks
	 * 	Params: String name
	 * 	Return:
	 * 
	 * 	Params: Remove all stocks by name
	 */
	public void removeAllStocksByName(String name){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM Stock "
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
	
	/*	Name: removeAllStocks
	 * 	Params: String ticker
	 * 	Return:
	 * 
	 * 	Params: Remove all stocks by ticker
	 */
	public void removeAllStocksByTicker(String ticker){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM Stock "
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
