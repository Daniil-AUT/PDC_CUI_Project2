package pdc_cui_project2;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniil
 */
public class DataBaseHandlerTest {
    DataBaseHandler dbHandler;
    
    public DataBaseHandlerTest() {
        this.dbHandler = DataBaseHandler.getDB();
    }
    @Test
    public void testSingletonPattern() {
        DataBaseHandler dbHandler1 = DataBaseHandler.getDB();
        DataBaseHandler dbHandler2 = DataBaseHandler.getDB();
        assertEquals(dbHandler1, dbHandler2);
    }
    /**
     * Test of establishConnection method, of class DataBaseHandler.
     */
    @Test
    public void testEstablishConnection() {
        dbHandler.establishConnection();
        assertNotNull(dbHandler.getConnection());
    }
    @Test
    public void testCreateUserRecord() {
        // Create Student Instance
        User userTest = new Student("Daniil", "Volodin", 
                "daniilvolodin@mail.com", "12345678");
        dbHandler.insertRecordUsers(userTest);
        assertTrue(dbHandler.checkIdExist(userTest.getID(), 
                                          userTest.getUserClass()));
    }
    
    @Test
    public void testPasswordMatch() {
        User userTest = new Student("Daniil", "Volodin", 
                "daniilvolodin@mail.com", "CORRECT_PASSWORD");
        dbHandler.insertRecordUsers(userTest);
        assertTrue(dbHandler.passwordMatch("CORRECT_PASSWORD",userTest.getID()));
        assertFalse(dbHandler.passwordMatch("INCORRECT", userTest.getID()));
    }
    
    // This is a test case to test the functionality of inserting, updating, and deleting a ticket.
    /*
    
    */
    @Test
    public void testTicketInsertUpdateAndDelete() {
        // Create a new student user for testing.
        User userTest = new Student("Daniil", "Volodin", "daniilvolodin@mail.com", "CORRECT_PASSWORD");

        // Insert the new user into the database.
        dbHandler.insertRecordUsers(userTest);

        // Define a description for a test ticket.
        String testTicketDescription = "TESTING TICKET...";

        // Insert the test ticket into the database.
        dbHandler.insertRecordTicket(testTicketDescription);

        // Retrieve all the tickets of the current user.
        HashMap<String, String> userTickets = dbHandler.getUserTickets();

        // Check if the inserted ticket is in the returned map.
        assertTrue(userTickets.containsKey(userTest.getID()));

        // Check if the description of the inserted ticket is correct.
        assertEquals(testTicketDescription, userTickets.get(userTest.getID()));

        // Define a new description for the test ticket.
        String testUpdatedDescription = "TESTING UPDATED TICKET...";

        // Update the description of the test ticket.
        dbHandler.updateTicket(testUpdatedDescription);

        // Retrieve the updated tickets of the current user.
        userTickets = dbHandler.getUserTickets();

        // Check if the description of the updated ticket is correct.
        assertEquals(testUpdatedDescription, userTickets.get(userTest.getID()));

        // Delete the test ticket.
        dbHandler.deleteRecordTicket();

        // Retrieve the updated tickets of the current user.
        userTickets = dbHandler.getUserTickets();

        // Check if the test ticket is deleted.
        assertTrue(!userTickets.containsKey(userTest.getID()));
    }


}
