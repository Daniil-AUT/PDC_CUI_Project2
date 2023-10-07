package pdc_cui_project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniil
 */
public final class DataBaseHandler {
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:HelpDeskDB;create=true";
    
    Connection conn;
    public DataBaseHandler() {
        establishConnection();
    }
    
    //Establish connection
    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                //System.out.println(URL + "   CONNECTED....");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Unable to close connection...");
            }
        }
    }

    public ResultSet myQuery(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void myUpdate(String sql) {

        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void createTable() {
        try {
            Statement statement = conn.createStatement();
            String newTableName = "USERS";

            String sqlCreate = "create table " + newTableName + " (ID int not null,"
                    + "Brand varchar(20), Model varchar(20),"
                    + "Price int, PRIMARY KEY (ID))";
            statement.executeUpdate(sqlCreate);

            String sqlInsert = "insert into " + newTableName + " values("
                    + "1, 'Toyota', 'Camry', 18000),"
                    + "("
                    + "2, 'Toyota', 'Corolla', 9800),"
                    + "("
                    + "3, 'Nissan', 'Pulsar', 6800)";
            statement.executeUpdate(sqlInsert);

            String sqlUpdateTable = "update " + newTableName + " set price=15000 "
                    + "where brand='Toyota' and model='camry'";
            statement.executeUpdate(sqlUpdateTable);

            //statement.close();
            System.out.println("Table created");

        } catch (SQLException ex) {
            System.out.println("Failed..");
        }
    }
    
}
