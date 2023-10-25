package pdc_cui_project2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniil
 */
public class DataBaseHandlerTest {
    
    public DataBaseHandlerTest() {
    }
    
    /**
     * Test of getDB method, of class DataBaseHandler.
     */
    @Test
    public void testGetDB() {
        System.out.println("getDB");
        DataBaseHandler expResult = null;
        DataBaseHandler result = DataBaseHandler.getDB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnection method, of class DataBaseHandler.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DataBaseHandler instance = null;
        Connection expResult = null;
        Connection result = instance.getConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of establishConnection method, of class DataBaseHandler.
     */
    @Test
    public void testEstablishConnection() {
        System.out.println("establishConnection");
        DataBaseHandler instance = null;
        instance.establishConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeConnections method, of class DataBaseHandler.
     */
    @Test
    public void testCloseConnections() {
        System.out.println("closeConnections");
        DataBaseHandler instance = null;
        instance.closeConnections();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasTicket method, of class DataBaseHandler.
     */
    @Test
    public void testHasTicket() {
        System.out.println("hasTicket");
        DataBaseHandler instance = null;
        boolean expResult = false;
        boolean result = instance.hasTicket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserName method, of class DataBaseHandler.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        DataBaseHandler instance = null;
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserDetails method, of class DataBaseHandler.
     */
    @Test
    public void testGetUserDetails() {
        System.out.println("getUserDetails");
        DataBaseHandler instance = null;
        String expResult = "";
        String result = instance.getUserDetails();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserID method, of class DataBaseHandler.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID");
        DataBaseHandler instance = null;
        String expResult = "";
        String result = instance.getUserID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of myQuery method, of class DataBaseHandler.
     */
    @Test
    public void testMyQuery() {
        System.out.println("myQuery");
        String sql = "";
        DataBaseHandler instance = null;
        ResultSet expResult = null;
        ResultSet result = instance.myQuery(sql);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of myUpdate method, of class DataBaseHandler.
     */
    @Test
    public void testMyUpdate() {
        System.out.println("myUpdate");
        String sql = "";
        DataBaseHandler instance = null;
        instance.myUpdate(sql);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIdExist method, of class DataBaseHandler.
     */
    @Test
    public void testCheckIdExist() {
        System.out.println("checkIdExist");
        String id = "";
        String type = "";
        DataBaseHandler instance = null;
        boolean expResult = false;
        boolean result = instance.checkIdExist(id, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTicketStatus method, of class DataBaseHandler.
     */
    @Test
    public void testCheckTicketStatus() {
        System.out.println("checkTicketStatus");
        String id = "";
        DataBaseHandler instance = null;
        instance.checkTicketStatus(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of passwordMatch method, of class DataBaseHandler.
     */
    @Test
    public void testPasswordMatch() {
        System.out.println("passwordMatch");
        String pass = "";
        String id = "";
        DataBaseHandler instance = null;
        boolean expResult = false;
        boolean result = instance.passwordMatch(pass, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserTickets method, of class DataBaseHandler.
     */
    @Test
    public void testGetUserTickets() {
        System.out.println("getUserTickets");
        DataBaseHandler instance = null;
        HashMap<String, String> expResult = null;
        HashMap<String, String> result = instance.getUserTickets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewTicket method, of class DataBaseHandler.
     */
    @Test
    public void testViewTicket() {
        System.out.println("viewTicket");
        DataBaseHandler instance = null;
        String expResult = "";
        String result = instance.viewTicket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTicketExists method, of class DataBaseHandler.
     */
    @Test
    public void testCheckTicketExists() {
        System.out.println("checkTicketExists");
        String id = "";
        DataBaseHandler instance = null;
        boolean expResult = false;
        boolean result = instance.checkTicketExists(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertRecordUsers method, of class DataBaseHandler.
     */
    @Test
    public void testInsertRecordUsers() {
        System.out.println("insertRecordUsers");
        User user = null;
        DataBaseHandler instance = null;
        instance.insertRecordUsers(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRecordTicket method, of class DataBaseHandler.
     */
    @Test
    public void testDeleteRecordTicket() {
        System.out.println("deleteRecordTicket");
        DataBaseHandler instance = null;
        instance.deleteRecordTicket();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertRecordTicket method, of class DataBaseHandler.
     */
    @Test
    public void testInsertRecordTicket() {
        System.out.println("insertRecordTicket");
        String text = "";
        DataBaseHandler instance = null;
        instance.insertRecordTicket(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTicket method, of class DataBaseHandler.
     */
    @Test
    public void testUpdateTicket() {
        System.out.println("updateTicket");
        String text = "";
        DataBaseHandler instance = null;
        instance.updateTicket(text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replyToTicket method, of class DataBaseHandler.
     */
    @Test
    public void testReplyToTicket() {
        System.out.println("replyToTicket");
        String id = "";
        String text = "";
        DataBaseHandler instance = null;
        instance.replyToTicket(id, text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUserTable method, of class DataBaseHandler.
     */
    @Test
    public void testCreateUserTable() {
        System.out.println("createUserTable");
        DataBaseHandler instance = null;
        instance.createUserTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
