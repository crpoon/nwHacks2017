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
			
			String selectSql = "SELECT * from Stock ORDER BY StockDate DESC;";
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
					+ "WHERE StockName = '" + name + "' "
					+ "ORDER BY StockDate DESC;";
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
					+ "WHERE StockTicker = '" + ticker + "' "
					+ "ORDER BY StockDate DESC;";
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
			
			String selectSql = "SELECT * from Stock WHERE StockDate>" + date.getTime() + " "
							+ "ORDER BY StockDate DESC;";
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
			
			String selectSql = "SELECT * from Stock WHERE StockDate<" + date.getTime() + " "
							+ "ORDER BY StockDate DESC;";
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
	
	/*	Name: getAllStockBetweenDates
	 * 	Params:	Date date1, Date date2
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all stocks between
	 * 			 given dates
	 */
	public List<Stock> getAllStockBetweenDates(Date date1, Date date2){
		long low;
		long high;
		if(date1.getTime() > date2.getTime()){
			high = date1.getTime();
			low = date2.getTime();
		} else {
			high = date2.getTime();
			low = date1.getTime();
		}
		
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock "
					+ "WHERE StockDate<" + high + " "
					+ "AND StockDate>" + low + " "
					+ "ORDER BY StockDate DESC;";
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
					+ "StockDate>" + date.getTime() + " "
					+ "ORDER BY StockDate DESC;";
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
					+ "StockDate<" + date.getTime() + " "
							+ "ORDER BY StockDate DESC;";
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

	/*	Name: getAllStockBetweenDates
	 * 	Params:	String name, Date date1, Date date2
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all name stocks between
	 * 			 given dates
	 */
	public List<Stock> getAllStockByNameBetweenDates(String name, Date date1, Date date2){
		long low;
		long high;
		if(date1.getTime() > date2.getTime()){
			high = date1.getTime();
			low = date2.getTime();
		} else {
			high = date2.getTime();
			low = date1.getTime();
		}
		
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock "
					+ "WHERE StockName='" + name + "' "
					+ "AND StockDate<" + high + " "
					+ "AND StockDate>" + low + " "
					+ "ORDER BY StockDate DESC;";
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
					+ "StockDate>" + date.getTime() + " "
					+ "ORDER BY StockDate DESC;";
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
					+ "StockDate<" + date.getTime() + " "
					+ "ORDER BY StockDate DESC;";
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
	
	/*	Name: getAllStockBetweenDates
	 * 	Params:	String name, Date date1, Date date2
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all name stocks between
	 * 			 given dates
	 */
	public List<Stock> getAllStockByTickerBetweenDates(String ticker, Date date1, Date date2){
		long low;
		long high;
		if(date1.getTime() > date2.getTime()){
			high = date1.getTime();
			low = date2.getTime();
		} else {
			high = date2.getTime();
			low = date1.getTime();
		}
		
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<Stock> listOfStocks = new ArrayList<Stock>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock "
					+ "WHERE StockTicker='" + ticker + "' "
					+ "AND StockDate<" + high + " "
					+ "AND StockDate>" + low + " "
					+ "ORDER BY StockDate DESC;";
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

	/*	Name: getStockByNameAndDate
	 * 	Params:	String name, Date date
	 * 	Return: Stock
	 * 
	 * 	Purpose: Finds a Stock by Name and Date
	 */
	public Stock getStockByNameAndDate(String name, Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        Stock returnStock = null;
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE "
					+ "StockName='" + name + "' AND "
					+ "StockDate=" + date.getTime();
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                returnStock = new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price"));
                break;
            } 
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return returnStock;	
	}
	
	/*	Name: getStockByTickerAndDate
	 * 	Params:	String ticker, Date date
	 * 	Return: Stock
	 * 
	 * 	Purpose: Finds a Stock by Ticker and Date
	 */
	public Stock getStockByTickerAndDate(String ticker, Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        Stock returnStock = null;
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE "
					+ "StockTicker='" + ticker + "' AND "
					+ "StockDate=" + date.getTime();
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                returnStock = new Stock(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getLong("StockDate"),
                							resultSet.getDouble("Price"));
                break;
            } 
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return returnStock;	
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

	/*	#### WARNING WILL DELETE ALL RECORDS ####
	 * 
	 * 	Name: removeAllStocks
	 * 	Params: Stock stock
	 * 	Return:
	 * 
	 * 	Params: Remove all stocks
	 */
	public void removeAllStocks(){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM Stock;";
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
	
	/*	Name: removeAllStocksByName
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
	
	/*	Name: removeStockByNameAndDate
	 * 	Params: String name, Date date
	 * 	Return:
	 * 
	 * 	Params: Remove a stock given name and date
	 */
	public void removeStockByNameAndDate(String name, Date date){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM Stock "
            		+ "WHERE StockName='" + name + "'"
            		+ "AND StockDate=" + date.getTime() + ";";
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

	/*	Name: removeAllStocksByTicker
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

	/*	Name: removeAllStocksByTickerAndDate
	 * 	Params: String ticker, Date date
	 * 	Return:
	 * 
	 * 	Params: Remove a stock given ticker and date
	 */
	public void removeAllStocksByTickerAndDate(String ticker, Date date){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM Stock "
            		+ "WHERE StockTicker='" + ticker + "'"
            		+ "AND StockDate=" + date.getTime() + ";";
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
