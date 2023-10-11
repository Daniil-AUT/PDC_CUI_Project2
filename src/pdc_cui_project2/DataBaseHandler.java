package pdc_cui_project2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
/**
 *
 * @author Daniil
 */
public final class DataBaseHandler {
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:HelpDeskDB;create=true";
    //Practice: jdbc:derby://localhost:1527/HelpDeskDB;create=true
    
    //Main: jdbc:derby:HelpDeskDB;create=true
    Connection conn;
    private static DataBaseHandler db;
    
    private DataBaseHandler() {
        establishConnection();
        createUserTable();
    }
    
    
    public static synchronized DataBaseHandler getDB() {
        if (db == null) {
            db = new DataBaseHandler();
            System.out.println("Instance Created..");
        }
        return db;
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

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                // Shut down the Derby database
                DriverManager.getConnection("jdbc:derby:;shutdown=true");
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Unable to close connection...");
            }
        }
    }
    public void insertRecordUsers(User user) {
        try (Statement statement = conn.createStatement()) {
            // INSERT INTO USERS (ID, Name, LastName, Email, Password, HasTicket, Type) 
            // VALUES ('id', 'name', 'lname', 'email', 'password', false, 'type')
            String sql = "INSERT INTO USERS (ID, Name, LastName, Email, Password, HasTicket, Type)"
        + " VALUES ('" + user.getID() + "', '" + user.getName() + "', '" + user.getLastName()
        + "', '" + user.getEmail() + "', '" + user.getPassword() + "', false, '"
        + user.getUserClass() + "')";
            statement.executeUpdate(sql);
            System.out.println("Record Sucessfully Inserted..");
        }
        catch(SQLException ex) {
            System.out.println("Error Inserting Record..");
        }
    }
    public boolean passwordMatch(String pass, String id) {
        ResultSet rs = myQuery("SELECT PASSWORD FROM USERS WHERE ID = '" + id + "'");
        try {
            while (rs.next()) {
                String password = rs.getString("PASSWORD");
                if (password != null && password.equalsIgnoreCase(pass)) {
                    System.out.println("PASSWORD MATCH");
                    return true;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Failed Reading from USER Table: " + ex.getMessage());
        } 
        return false;
    }
    
    public boolean checkIdExist(String id, String type) {
        ResultSet rs = myQuery("SELECT ID FROM USERS WHERE Type = '" + type + "'");
        try {
            while (rs.next()) {
                String currentID = rs.getString("ID");
                if (currentID != null && currentID.equalsIgnoreCase(id)) {
                    System.out.println("ID Found: " + id);
                    return true;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Failed Reading from USER Table: " + ex.getMessage());
        } 
        
        return false;
    }

    
/*
CREATE TABLE USERS (
    ID VARCHAR(10),
    Name VARCHAR(20),
    LastName VARCHAR(25),
    Email VARCHAR(50),
    Password VARCHAR(20),
    HasTicket BOOLEAN,
    Type VARCHAR(50),
    PRIMARY KEY (ID)
); 
*/ 
    private boolean tableExists(String tableName, DatabaseMetaData dbmd) throws SQLException {
        try (ResultSet rsDBMeta = dbmd.getTables(null, null, null, null)) {
            while (rsDBMeta.next()) {
                String existingTableName = rsDBMeta.getString("TABLE_NAME");
                if (existingTableName.equalsIgnoreCase(tableName)) {
                    System.out.println("Table already exists");
                    return true;
                }
            }
            return false;
        }
    }

    private void createUser() {
        String createTableSQL = "CREATE TABLE USERS ("
                + "ID VARCHAR(10), "
                + "Name VARCHAR(20), "
                + "LastName VARCHAR(25), "
                + "Email VARCHAR(50), "
                + "Password VARCHAR(20), "
                + "HasTicket BOOLEAN, "
                + "Type VARCHAR(20), "
                + "PRIMARY KEY (ID))";

        String insertDataSQL = "INSERT INTO USERS (ID, Name, LastName, Email, Password, HasTicket, Type) " +
                "VALUES ('UKDS1234', 'John', 'Doe', 'john.doe@email.com', 'password123', false, 'Customer'), " +
                "('AS123232', 'Jane', 'Doe', 'jane.doe@email.com', 'securePwd', false, 'Assistant'), " +
                "('ABCD1234', 'Bob', 'Bill', 'bob.bill@email.com', 'bobPass', false, 'Student')";

        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(createTableSQL);
            statement.executeUpdate(insertDataSQL);
            System.out.println("Table created and data inserted");
        }
        catch(SQLException ex) {
            System.out.println("Unable To Create USERS table...");
        }
    }

    public void createUserTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            if (!tableExists("USERS", dbmd)) {
                createUser();
            }
        } catch (SQLException ex) {
            System.out.println("Failed: " + ex.getMessage());
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
}
