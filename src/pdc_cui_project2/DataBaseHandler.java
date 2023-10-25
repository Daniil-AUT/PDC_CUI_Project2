package pdc_cui_project2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages all database operations for the AUT HelpDesk app.
 * Handles establishing connection to java db, ticket/user operations, 
 * and table creation.
 * The Singleton pattern is used to ensure a single database instance so that
 * every class using it is working with the shared instance.
 * Use Derby embedded database to connect without starting java server.
 * 
 * @author Daniil
 */
public final class DataBaseHandler {
    
    // Constants for table names and database details.
    private static final String TICKET_TABLE = "TICKET";
    private static final String USERS_TABLE = "USERS";
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:HelpDeskDB;create=true";
    
    // Instance variables will track user details (for user identification).
    private boolean hasTicket;
    private String currentName;
    private String userDetails;
    private String userID;

    //Practice: jdbc:derby://localhost:1527/HelpDeskDB;create=true
    //Main: jdbc:derby:HelpDeskDB;create=true
    Connection conn;
    
    // Create a single instance of DataBaseHandler class.
    private static DataBaseHandler db;
    
    // Set to private constructor to ensure singleton pattern is followed.
    private DataBaseHandler() {
        establishConnection();
        createUserTable();
        createTicketTable();
        this.hasTicket = false;
        this.currentName = "<Unknown>";
        this.userID = "<Unknown>";
    }

    // Ensure the same class is accessed once at a time.
    public static synchronized DataBaseHandler getDB() {
        if (db == null) {
            db = new DataBaseHandler();
            System.out.println("Instance Created..");
        }
        return db;
    }

    // Get connection to the HelpDeskDB.
    public Connection getConnection() {
        return this.conn;
    }
    
    // Establish connection to the HelpDeskDB.
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            }
        }
    }
    
    // Close connection to the HelpDeskDB.
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

    /* --GETTER METHODS-- */
    public boolean hasTicket() {
        return this.hasTicket;
    }

    public String getUserName() {
        return this.currentName;
    }

    public String getUserDetails() {
        return this.userDetails;
    }

    public String getUserID() {
        return this.userID;
    }
    
    /*                      */
    /* --GENERAL METHODS--  */
    /*                      */
    
    // Executes the SQL Query by passing in the sql String type.
    public ResultSet myQuery(String sql) {
        try {
            Statement statement = this.conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Executes the SQL Update by passing in the sql String type.
    public void myUpdate(String sql) {
        try {
            Statement statement = this.conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Formats a string into multiple lines based on a specified limit.
     * Used for text to be appropriately in the text area of java swing.
     */
    private String formatTicket(String input, int limit) {
        StringBuilder result = new StringBuilder();
        int length = input.length();

        for (int i = 0; i < length; i += limit) {
            result.append(input, i, Math.min(i + limit, length)).append("\n");
        }

        return result.toString();
    }

    /*                      */
    /*    --READ METHODS--  */
    /*                      */
    
    // Checks if an ID exists in the USERS table for a user type. If exists, return true
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

    // Checks ticket status for a given ID and updates user details
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
    
    //Checks if entered password matches the password for a given ID. If yes, return true.
    public boolean passwordMatch(String pass, String id) {
        ResultSet rs = myQuery("SELECT * FROM " + USERS_TABLE
                + " WHERE ID = '" + id.toUpperCase() + "'");
        try {
            while (rs.next()) {
                String password = rs.getString("PASSWORD");
                if (password != null && password.equals(pass)) {
                    String name = rs.getString("NAME");
                    String lname = rs.getString("LASTNAME");
                    String email = rs.getString("EMAIL");
                    String identification = rs.getString("ID");
                    this.userDetails = "Full Name: " + name + " " + lname
                            + "\nEmail: " + email + "\nID: " + identification;
                    this.currentName = name;
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
    
    // Returns all user tickets from the TICKET table.
    public HashMap<String, String> getUserTickets() {
        HashMap<String, String> ticketTable = new HashMap<>();
        String sqlGetTickets = "SELECT TICKET_ID, DESCRIPTION FROM " + TICKET_TABLE;

        try ( ResultSet rs = myQuery(sqlGetTickets)) {
            while (rs.next()) {
                String id = rs.getString("TICKET_ID");
                String description = rs.getString("DESCRIPTION");
                // Assuming formatTicket returns a 1D array for each ticket
                ticketTable.put(id, description);
            }
        } catch (SQLException ex) {
            System.out.println("Failed Reading from " + TICKET_TABLE + ": " + ex.getMessage());
        }
        return ticketTable;
    }
    
    // Returns all the details (including reply) of a specific ticket.
    public String viewTicket() {
        String sqlViewTicket = "SELECT DESCRIPTION, REPLY FROM " + TICKET_TABLE
                + " WHERE TICKET_ID = '" + this.userID.toUpperCase() + "'";

        try ( ResultSet rs = myQuery(sqlViewTicket)) {
            if (rs.next()) {
                String description = rs.getString("DESCRIPTION");
                String reply = rs.getString("REPLY");

                if (reply != null) {
                    // Include a blank line between description and reply for clarity
                    return formatTicket(description
                            + "\n\n--REPLY--\n\n" + userDetails + "\n\n" + reply, 150);
                } else {
                    return formatTicket(description, 70);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Failed Reading from " + TICKET_TABLE + ": " + ex.getMessage());
        }
        return "";
    }
    
    // Checks if ticket given its ID exists. If yes, return true
    public boolean checkTicketExists(String id) {
        String sqlCheckTicket = "SELECT TICKET_ID FROM " + TICKET_TABLE
                + " WHERE TICKET_ID = '" + id + "'";

        try ( ResultSet rs = myQuery(sqlCheckTicket)) {
            if (rs.next()) {
                return true;
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Failed Reading from TICKET Table: " + ex.getMessage());
        }
        return false;
    }

    /*                      */
    /*   --WRITE METHODS--  */
    /*                      */
    
    /*
    Insert new user record into the USERS table while setting current user 
    variable to the ones that have been passed in the by the user class.
    */
    
    public void insertRecordUsers(User user) {
        this.currentName = user.getName();
        this.userDetails = user.toString();
        this.userID = user.getID();
        this.hasTicket = false;
        
        // SQL query to insert a new user record
        String sql = "INSERT INTO " + USERS_TABLE + " (ID, Name, LastName, Email, "
                + "Password, "
                + "HasTicket, Type)"
                + " VALUES ('" + user.getID() + "', '" + user.getName() + "', '" + user.getLastName()
                + "', '" + user.getEmail() + "', '" + user.getPassword() + "', false, '"
                + user.getUserClass() + "')";
        this.userDetails = "\nFull Name: " + user.getName() + " " + user.getLastName()
                + "\nEmail: " + user.getEmail() + "\nID: " + user.getID() + "\n";
        
        myUpdate(sql);
        System.out.println("Record Sucessfully Inserted..");
    }

    /*
    Delete user ticket record from the TICKET table 
    use the local user id variable to locate the ticket.
    */
    
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
    
    // Insert new ticket record into the TICKET table.
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
    
    // Update the description of a ticket in the TICKET table.
    public void updateTicket(String text) {
        String sqlUpdateTicket = "UPDATE " + TICKET_TABLE + " SET DESCRIPTION = '"
                + text + "' WHERE TICKET_ID = '"
                + this.userID + "'";
        myUpdate(sqlUpdateTicket);
    }
    
    // Add reply to a specific ticket in the TICKET table based on id.
    public void replyToTicket(String id, String text) {
        try {
            String sqlSelectTicket = "SELECT DESCRIPTION FROM " + TICKET_TABLE
                    + " WHERE TICKET_ID = '" + id.toUpperCase() + "'";
            ResultSet rs = myQuery(sqlSelectTicket);

            if (rs.next()) {
                String sqlUpdateReply = "UPDATE " + TICKET_TABLE + " SET REPLY = '"
                        + text + "' WHERE TICKET_ID = '" + id + "'";
                myUpdate(sqlUpdateReply);
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*                      */
    /*   --TABLE METHODS--  */
    /*                      */
    
    // Check to see if table name already exists in the database.
    private boolean tableExists(String tableName, DatabaseMetaData dbmd) {
        try ( ResultSet rsDBMeta = dbmd.getTables(null, null, null, null)) {
            while (rsDBMeta.next()) {
                String existingTableName = rsDBMeta.getString("TABLE_NAME");
                if (existingTableName.equalsIgnoreCase(tableName)) {
                    System.out.println("Table already exists");
                    return true;
                }
            }   
        }
        catch(SQLException ex) {
            System.out.println("SQL Error Occured: "+ex.getMessage());
        }
        return false;
    }
    
    // Create TICKET table in the database. 
    private void createTicketTable() {
        
        // SQL query to create the TICKET table
        String createTableSQL = "CREATE TABLE " + TICKET_TABLE + " ("
                + "TICKET_ID VARCHAR(50) PRIMARY KEY,"
                + "DESCRIPTION CLOB, "
                + "REPLY CLOB"
                + ")";

        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            
            // Check if the table already exists
            if (!tableExists(TICKET_TABLE, dbmd)) {
                
                // If not - create the table
                myUpdate(createTableSQL);
                System.out.println("Ticket Table Created.");
            }
        } catch (SQLException ex) {
            System.out.println("Failed: " + ex.getMessage());
        }
    }
    
    // Create USERS table inserts some records into it.
    private void createUser() {
        
        // SQL query to create the USERS table
        String createTableSQL = "CREATE TABLE " + USERS_TABLE + " ("
                + "ID VARCHAR(10), "
                + "Name VARCHAR(50), "
                + "LastName VARCHAR(50), "
                + "Email VARCHAR(100), "
                + "Password VARCHAR(50), "
                + "HasTicket BOOLEAN, "
                + "Type VARCHAR(20), "
                + "PRIMARY KEY (ID))";
        
        // SQL query to insert recrods to USERS table
        String insertDataSQL = "INSERT INTO " + USERS_TABLE + " (ID, Name, "
                + "LastName, Email, Password, HasTicket, Type) "
                + "VALUES ('UKDS1234', 'John', 'Doe', 'john.doe@email.com', "
                + "'password123', false, 'Customer'), "
                + "('AS123232', 'Jane', 'Doe', 'jane.doe@email.com', 'securePwd', "
                + "false, 'Assistant'), "
                + "('ABCD1234', 'Bob', 'Bill', 'bob.bill@email.com', 'bobPass', "
                + "false, 'Student')";
        
        // Create USERS table
        myUpdate(createTableSQL);
        // Insert records USERS table
        myUpdate(insertDataSQL);
        System.out.println("Table created and data inserted");

    }
    //  Create USERS table if it doesn't exist.
    public void createUserTable() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            // Check if the table already exists
            if (!tableExists(USERS_TABLE, dbmd)) {
                createUser();
            }
        } catch (SQLException ex) {
            System.out.println("Failed: " + ex.getMessage());
        }
    }
}