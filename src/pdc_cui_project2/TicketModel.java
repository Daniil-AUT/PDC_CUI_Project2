package pdc_cui_project2;

/**
 * TicketModel handles all ticket-related operations
 * It interacts with the database through the DataBaseHandler to create, update,
 * check if ticket exists, and reply to them.
 * 
 * @author Daniil
 */
public class TicketModel {
    // Reference the DataBaseHandler class to handle loigc
    private final DataBaseHandler db;
    
    // Initialise constructor with reference to database handler (singleton)
    public TicketModel() {
        this.db = DataBaseHandler.getDB();
    }
    
    // Create new ticket with given text.
    public void createTicket(String text) {
        db.insertRecordTicket(text);
    }
    
    // Update content of existing ticket.
    public void updateTicket(String text) {
        db.updateTicket(text);
    }
    
    // Check if ticket with given ID exists in database.
    public boolean checkTicketExists(String id) {
        return db.checkTicketExists(id);
    }
    
    // Add a reply to existing ticket.
    public void replyToTicket(String id, String reply) {
        db.replyToTicket(id, reply);
    }
}
