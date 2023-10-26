package pdc_cui_project2;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniil
 */
public class DataBaseHandlerTest {

    // Declare DataBaseHandler class
    DataBaseHandler db;

    public DataBaseHandlerTest() {

        // Instatiate the DataBaseHandler
        this.db = DataBaseHandler.getDB();
    }

    /*
    It's important that getDB() always returns the same instance of the 
    DataBaseHandl;er class no matter how many times it was called. Make sure
    that the two variable classes point to the same object.
    Calling the same database class many times will give connection errors.
     */
    @Test
    public void testSingletonPattern() {
        DataBaseHandler db1 = DataBaseHandler.getDB();
        DataBaseHandler db2 = DataBaseHandler.getDB();

        assertEquals(db1, db2);
    }

    /*
    Test to ensure that database connection is successfully established
    Important to know whether GUI app has successfully connected to the database.
    Good for knowing that embedded database is working without starting the server.
     */
    @Test
    public void testEstablishConnection() {
        // Establish a HelpDesk database connection.
        db.establishConnection();

        // Check that connection is not null which means it was successful.
        assertNotNull(db.getConnection());
    }

    @Test
    public void testCreateUserRecord() {
        // Create Student Instance
        User userTest = new Student("Daniil", "Volodin",
                "daniilvolodin@mail.com", "12345678");
        db.insertRecordUsers(userTest);
        assertTrue(db.checkIdExist(userTest.getID(),
                userTest.getUserClass()));
    }

    /*
    Test to verify the password match method in database works as exepcted.
    Important for matching password with user id which will confirm user identity.
     */
    @Test
    public void testPasswordMatch() {

        /*
        Create new user and insert his credintials in database, will be used
        for testing the password match method.
         */
        User userTest = new Student("Daniil", "Volodin", "daniilvolodin@mail.com", "CORRECT_PASSWORD");
        db.insertRecordUsers(userTest);

        /*
        Check if password matching method works by comparing correct and incorrect
        passwords.
        Valid password, should return true.
        Incorrect password, should return false
         */
        assertTrue(db.passwordMatch("CORRECT_PASSWORD", userTest.getID()));
        assertFalse(db.passwordMatch("INCORRECT", userTest.getID())); 
    }


    /*
    This is a test case to test the functionality of inserting, updating, and 
    deleting a ticket.
     */
    @Test
    public void testTicketInsertUpdateAndDelete() {
        // Create a new user for testing.
        User userTest = new Student("Daniil", "Volodin", "daniilvolodin@mail.com", "CORRECT_PASSWORD");
        db.insertRecordUsers(userTest);
        String testTicketDescription = "TESTING TICKET...";
        db.insertRecordTicket(testTicketDescription);

        /*
        Retrieve all the tickets of the current user.
        Check if the inserted ticket is in the returned map.
        Check if the description of the inserted ticket is correct.
        */
        HashMap<String, String> userTickets = db.getUserTickets();
        assertTrue(userTickets.containsKey(userTest.getID()));
        assertEquals(testTicketDescription, userTickets.get(userTest.getID()));
        
        /*
        Update existing description for the test ticket for test user.
        Get updated ticket for test user.
        Compare description of the updated ticket to the expected description.
        */
        String testUpdatedDescription = "TESTING UPDATED TICKET...";
        db.updateTicket(testUpdatedDescription);
        userTickets = db.getUserTickets();
        assertEquals(testUpdatedDescription, userTickets.get(userTest.getID()));

        /*
        Delete test user ticket.
        Try to get deleted ticket from database.
        Check if the test ticket is deleted by checking the updated table based
        on user id.
        */
        db.deleteRecordTicket();
        userTickets = db.getUserTickets();
        assertTrue(!userTickets.containsKey(userTest.getID()));
    }

}
