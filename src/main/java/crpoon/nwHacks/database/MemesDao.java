package crpoon.nwHacks.database;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;

public class MemesDao {
	// Replace server name, username, and password with your credentials  
    public static void main(String[] args) {
        String connectionString =
        		"jdbc:sqlserver://memestock.database.windows.net:1433;"
        		+ "database=MEMESTOCK;"
        		+ "user=sojuiswater@memestock;"
        		+ "password=JustifiablePolham2017;"
        		+ "encrypt=true;"
        		+ "trustServerCertificate=false;"
        		+ "hostNameInCertificate=*.database.windows.net;"
        		+ "loginTimeout=30;";
        
        // Declare the JDBC objects.  
        Connection connection = null;  
        Statement statement = null;   
        ResultSet resultSet = null;  

        try {  
            connection = DriverManager.getConnection(connectionString);  
            System.out.println("Database Success");
            
            String selectSql = "SELECT * from StockInfo";
            statement = connection.createStatement();  
            resultSet = statement.executeQuery(selectSql);
            PreparedStatement prepsInsertProduct = null;
            
            // Print results from select statement  
            while (resultSet.next())   
            {  
                System.out.println(resultSet.getString(2) + " "  
                    + resultSet.getString(3));  
            } 
            
            String addSql = "INSERT INTO StockInfo (StockName, StockTicker, Sector, ImgUrl)"
            		+ " VALUES ('You only live once', 'YOLO', 'Facebook', 'randomImgur');";
            
            prepsInsertProduct = connection.prepareStatement(  
                    addSql,  
                    Statement.RETURN_GENERATED_KEYS);  
            prepsInsertProduct.execute();  

            // Retrieve the generated key from the insert.  
            resultSet = prepsInsertProduct.getGeneratedKeys();  

            // Print the ID of the inserted row.  
            while (resultSet.next()) {  
                System.out.println("Generated: " + resultSet.getString(1));  
            } 
        }  
        catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally {  
            if (connection != null) try { connection.close(); } catch(Exception e) {}  
        }  
    }
}
