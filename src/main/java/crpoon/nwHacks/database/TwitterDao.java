package crpoon.nwHacks.database;

import crpoon.nwHacks.model.TwitterSince;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crpoon on 2017-03-19.
 */
public class TwitterDao {
    private static TwitterDao instance;

    private static String connectionString =
            "jdbc:sqlserver://memestock.database.windows.net:1433;"
                    + "database=MEMESTOCK;"
                    + "user=sojuiswater@memestock;"
                    + "password=JustifiablePolham2017;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "hostNameInCertificate=*.database.windows.net;"
                    + "loginTimeout=30;";

    private TwitterDao() {
    }

    public static TwitterDao getInstance() {
        if (instance == null) {
            instance = new TwitterDao();
        }
        return instance;
    }

    public TwitterSince getTwitterSince(String name) {
        Connection connection = null;
        Statement statement;
        ResultSet resultSet;
        TwitterSince ts = null;

        try {
            connection = DriverManager.getConnection(connectionString);

            String selectSql = "SELECT * from Twitter "
                    + "WHERE StockName = '" + name + "' ";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                ts = new TwitterSince(resultSet.getString("StockName"),
                        resultSet.getLong("SinceId"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); } catch(Exception e) {}
        }
        return ts;
    }

    public void updateTwitterSince(TwitterSince ts) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(connectionString);

            String updateSql = "UPDATE Twitter SET "
                    + "SinceId = '" + ts.getSinceId() + "' "
                    + "WHERE StockName='" + ts.getStockName() + "' ;";
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

    public void insertTwitterSince(TwitterSince ts) {
        Connection connection = null;
        Statement statement;
        try {
            connection = DriverManager.getConnection(connectionString);

            String insertSql = "INSERT INTO Twitter (StockName, SinceId)"
                    + " VALUES ('" + ts.getStockName() + "', "
                    + "'" + ts.getSinceId() + "');";
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
}
