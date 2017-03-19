package crpoon.nwHacks.database;

import crpoon.nwHacks.model.InstagramInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstagramInfoDao {
	private static InstagramInfoDao instance;

	private static String connectionString =
    		"jdbc:sqlserver://memestock.database.windows.net:1433;"
    		+ "database=MEMESTOCK;"
    		+ "user=sojuiswater@memestock;"
    		+ "password=JustifiablePolham2017;"
    		+ "encrypt=true;"
    		+ "trustServerCertificate=false;"
    		+ "hostNameInCertificate=*.database.windows.net;"
    		+ "loginTimeout=30;";

	private InstagramInfoDao() {
	}
	
	public static InstagramInfoDao getInstance() {
		if (instance == null) {
			instance = new InstagramInfoDao();
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
	public List<InstagramInfo> getAllStock(){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<InstagramInfo> listOfInstagramInfo = new ArrayList<InstagramInfo>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from InstagramInfo ORDER BY StockDate DESC;";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
				listOfInstagramInfo.add(new InstagramInfo(resultSet.getString("StockName"),
                							resultSet.getString("StockTicker"),
                							resultSet.getInt("TotalMention"),
                							resultSet.getLong("StockDate")));
            } 
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return listOfInstagramInfo;
	}

	/*	Name: getAllStockByName
	 * 	Params: String name
	 * 	Return: List<Stock>
	 * 
	 * 	Purpose: Queries and returns all stocks
	 * 			 with StockName = name
	 */
	public List<InstagramInfo> getAllInstagramInfoByName(String name){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<InstagramInfo> listOfInstagramInfo = new ArrayList<InstagramInfo>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from InstagramInfo "
					+ "WHERE StockName = '" + name + "' "
					+ "ORDER BY StockDate DESC;";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
				listOfInstagramInfo.add(new InstagramInfo(resultSet.getString("StockName"),
						resultSet.getString("StockTicker"),
						resultSet.getInt("TotalMention"),
						resultSet.getLong("StockDate")));
            } 
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return listOfInstagramInfo;
	}


	public List<InstagramInfo> getAllInstagramInfoByTicker(String ticker){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
        List<InstagramInfo> listOfInstagramInfo = new ArrayList<InstagramInfo>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from InstagramInfo "
					+ "WHERE StockTicker = '" + ticker + "' "
					+ "ORDER BY StockDate DESC;";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
				listOfInstagramInfo.add(new InstagramInfo(resultSet.getString("StockName"),
						resultSet.getString("StockTicker"),
						resultSet.getInt("TotalMention"),
						resultSet.getLong("StockDate")));
            } 
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return listOfInstagramInfo;
	}


	public List<InstagramInfo> getAllInstagramInfoAfterDate(Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
		List<InstagramInfo> listOfInstagramInfo = new ArrayList<InstagramInfo>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from Stock WHERE StockDate>" + date.getTime() + " "
							+ "ORDER BY StockDate DESC;";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
				listOfInstagramInfo.add(new InstagramInfo(resultSet.getString("StockName"),
						resultSet.getString("StockTicker"),
						resultSet.getInt("TotalMention"),
						resultSet.getLong("StockDate")));
            } 
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return listOfInstagramInfo;
	}
	

	public List<InstagramInfo> getAllInstagramInfoBeforeDate(Date date){
		Connection connection = null;
		Statement statement = null;   
        ResultSet resultSet = null;
		List<InstagramInfo> listOfInstagramInfo = new ArrayList<InstagramInfo>();
        
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String selectSql = "SELECT * from InstagramInfo WHERE StockDate<" + date.getTime() + " "
							+ "ORDER BY StockDate DESC;";
			statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
				listOfInstagramInfo.add(new InstagramInfo(resultSet.getString("StockName"),
						resultSet.getString("StockTicker"),
						resultSet.getInt("TotalMention"),
						resultSet.getLong("StockDate")));
            } 
		}  
        catch (Exception e) {  
            e.printStackTrace();
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }
		return listOfInstagramInfo;
	}
	

	public void insertInstagramInfo(InstagramInfo info){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String insertSql = "INSERT INTO InstagramInfo (StockName, StockTicker, TotalMention, StockDate)"
            		+ " VALUES ('" + info.getName() + "', "
					+ "'" + info.getTicker() + "', "
					+ info.getTotalMention() + ", "
					+ info.getDateAsLong() + ");";
			System.out.println(insertSql);
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


	public void removeAllInstagramInfos(){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM InstagramInfo;";
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

	public void removeAllInstagramInfosByName(String name){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM InstagramInfo "
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
	

	public void removeInstagramInfoByNameAndDate(String name, Date date){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM InstagramInfo "
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


	public void removeAllInstagramInfosByTicker(String ticker){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM InstagramInfo "
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

	public void updateInstagramInfo(InstagramInfo info){
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(connectionString);

			String updateSql = "UPDATE InstagramInfo SET "
					+ "TotalMention = '" + info.getTotalMention() + "' "
					+ "WHERE StockName='" + info.getName() + "';";
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


	public void removeAllInstagramInfosByTickerAndDate(String ticker, Date date){
		Connection connection = null;
		Statement statement = null;   
		try {  
			connection = DriverManager.getConnection(connectionString);
			
			String deleteSql = "DELETE FROM InstagramInfo "
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
