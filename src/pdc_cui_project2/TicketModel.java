package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class TicketModel {
    DataBaseHandler db;
    
    public TicketModel() {
        this.db = DataBaseHandler.getDB();
    }
    
    public void createTicket(String text) {
        this.db.insertRecordTicket(text);
    }
}
