package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class TicketModel {
    private final DataBaseHandler db;

    public TicketModel() {
        this.db = DataBaseHandler.getDB();
    }

    public void createTicket(String text) {
        db.insertRecordTicket(text);
    }

    public void updateTicket(String text) {
        db.updateTicket(text);
    }

    public boolean checkTicketExists(String id) {
        return db.checkTicketExists(id);
    }

    public void replyToTicket(String id, String reply) {
        db.replyToTicket(id, reply);
    }
}
