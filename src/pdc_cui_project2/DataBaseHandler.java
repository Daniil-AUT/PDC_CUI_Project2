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

    private static final String TICKET_TABLE = "TICKET";
    private static final String USERS_TABLE = "USERS";
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:HelpDeskDB;create=true";
    public boolean hasTicket;
    public String currentName;
    public String userDetails;
    public String userID;
    //Practice: jdbc:derby://localhost:1527/HelpDeskDB;create=true

    //Main: jdbc:derby:HelpDeskDB;create=true
    Connection conn;
    private static DataBaseHandler db;

    private DataBaseHandler() {
        establishConnection();
        createUserTable();
        createTicketTable();
        this.hasTicket = false;
        this.currentName = "<Unknown>";
        this.userID = "<Unknown>";
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

    public void insertRecordTicket(String text) {
        String sqlCreateTicket = "INSERT INTO " + TICKET_TABLE
                + " (TICKET_ID, DESCRIPTION) "
                + " VALUES ('" + this.userID + "', '" + text + "')";
        myUpdate(sqlCreateTicket);

        String sqlUpdateUser = "UPDATE " + USERS_TABLE + " SET HASTICKET = "
                + "true WHERE ID = '"
                + this.userID + "'";
        this.hasTicket = true;
        myUpdate(sqlUpdateUser);
    }

    /*
    SELECT DESCRIPTION
    FROM TICKET
    WHERE TICKET_ID = 'id';
     */
    public String viewTicket() {
        String sqlViewTicket = "SELECT DESCRIPTION FROM " + TICKET_TABLE +
                " WHERE TICKET_id = '" + this.userID.toUpperCase() + "'";
        try (ResultSet rs = myQuery(sqlViewTicket)) {
            if (rs.next()) {
                String description = rs.getString("DESCRIPTION");
                return formatTicket(description, 75); 
            }
        } catch (SQLException ex) {
            System.out.println("Failed Reading from " + TICKET_TABLE + ": " + ex.getMessage());
        }
        return "";
    }
    private String formatTicket(String input, int limit) {
        StringBuilder result = new StringBuilder();
        int length = input.length();

        for (int i = 0; i < length; i += limit) {
            result.append(input, i, Math.min(i + limit, length)).append("\n");
        }

        return result.toString();
    }

    public void deleteRecordTicket() {
        String sqlDeleteTicket = "DELETE FROM " + TICKET_TABLE
                + " WHERE TICKET_ID = '"
                + this.userID + "'";
        myUpdate(sqlDeleteTicket);

        String sqlUpdateUser = "UPDATE " + USERS_TABLE + " SET HASTICKET = "
                + "false WHERE ID = '"
                + this.userID + "'";
        this.hasTicket = false;
        myUpdate(sqlUpdateUser);
    }

    public void insertRecordUsers(User user) {
        this.currentName = user.getName();
        this.userDetails = user.toString();
        this.userID = user.getID();
        this.hasTicket = false;
        // INSERT INTO USERS (ID, Name, LastName, Email, Password, HasTicket, Type) 
        // VALUES ('id', 'name', 'lname', 'email', 'password', false, 'type')
        String sql = "INSERT INTO " + USERS_TABLE + " (ID, Name, LastName, Email, "
                + "Password, "
                + "HasTicket, Type)"
                + " VALUES ('" + user.getID() + "', '" + user.getName() + "', '" + user.getLastName()
                + "', '" + user.getEmail() + "', '" + user.getPassword() + "', false, '"
                + user.getUserClass() + "')";
        this.userDetails = "Full Name: " + user.getName() + " " + user.getLastName() + 
                "\nEmail: " + user.getEmail() + "\nID: " + user.getID();
        myUpdate(sql);
        System.out.println("Record Sucessfully Inserted..");
    }

    public boolean passwordMatch(String pass, String id) {
        ResultSet rs = myQuery("SELECT * FROM " + USERS_TABLE
                + " WHERE ID = '" + id + "'");
        try {
            while (rs.next()) {
                String password = rs.getString("PASSWORD");
                if (password != null && password.equalsIgnoreCase(pass)) {
                    String name = rs.getString("NAME");
                    String lname = rs.getString("LASTNAME");
                    String email = rs.getString("EMAIL");
                    String identification = rs.getString("ID");
                    this.userDetails = "Full Name: " + name + " " + lname + 
                            "\nEmail: " + email + "\nID: " + identification;
                    return true;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Failed Reading from " + USERS_TABLE + " Table: "
                    + ex.getMessage());
        }
        return false;
    }

    public void checkTicketStatus(String id) {
        ResultSet rs = myQuery("SELECT NAME, HASTICKET FROM " + USERS_TABLE
                + " WHERE ID = '" + id + "'");
        try (rs) {
            if (rs.next()) {
                this.hasTicket = rs.getBoolean("HASTICKET");
                this.currentName = rs.getString("NAME");
                this.userID = id;
            }
        } catch (SQLException ex) {
            System.out.println("Error Reading ID: " + ex.getMessage());
        }
    }

    public boolean checkIdExist(String id, String type) {
        ResultSet rs = myQuery("SELECT ID FROM " + USERS_TABLE + " WHERE Type = '"
                + type + "'");
        try {
            while (rs.next()) {
                String currentID = rs.getString("ID");
                if (currentID != null && currentID.equalsIgnoreCase(id)) {
                    System.out.println("ID Found: " + id);
                    this.userID = id;
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
        try ( ResultSet rsDBMeta = dbmd.getTables(null, null, null, null)) {
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

    /*
 CREATE TABLE Ticket (
    ticket_id VARCHAR(50) PRIMARY KEY,
    description TEXT
);   
     */
    private void createTicketTable() {
        String createTableSQL = "CREATE TABLE " + TICKET_TABLE + " ("
                + "ticket_id VARCHAR(50) PRIMARY KEY,"
                + "description CLOB"
                + ")";

        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            if (!tableExists(TICKET_TABLE, dbmd)) {
                myUpdate(createTableSQL);
                System.out.println("Ticket Table Created.");
            }
        } catch (SQLException ex) {
            System.out.println("Failed: " + ex.getMessage());
        }
    }

    private void createUser() {
        String createTableSQL = "CREATE TABLE " + USERS_TABLE + " ("
                + "ID VARCHAR(10), "
                + "Name VARCHAR(20), "
                + "LastName VARCHAR(25), "
                + "Email VARCHAR(50), "
                + "Password VARCHAR(20), "
                + "HasTicket BOOLEAN, "
                + "Type VARCHAR(20), "
                + "PRIMARY KEY (ID))";

        String insertDataSQL = "INSERT INTO " + USERS_TABLE + " (ID, Name, LastName, Email, Password, HasTicket, Type) "
                + "VALUES ('UKDS1234', 'John', 'Doe', 'john.doe@email.com', 'password123', false, 'Customer'), "
                + "('AS123232', 'Jane', 'Doe', 'jane.doe@email.com', 'securePwd', false, 'Assistant'), "
                + "('ABCD1234', 'Bob', 'Bill', 'bob.bill@email.com', 'bobPass', false, 'Student')";

        myUpdate(createTableSQL);
        myUpdate(insertDataSQL);
        System.out.println("Table created and data inserted");

    }

    public void createUserTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            if (!tableExists(USERS_TABLE, dbmd)) {
                createUser();
            }
        } catch (SQLException ex) {
            System.out.println("Failed: " + ex.getMessage());
        }
    }

    public ResultSet myQuery(String sql) {
        try {
            Statement statement = this.conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void myUpdate(String sql) {
        try {
            Statement statement = this.conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
