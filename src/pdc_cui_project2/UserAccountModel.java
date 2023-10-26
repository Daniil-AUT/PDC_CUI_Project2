package pdc_cui_project2;

/**
 * UserAccountModel class handles all logic for the User Account. Interacts with
 * the DataBaseHandler to delete ticket.
 *
 * @author Daniil
 */
public class UserAccountModel {

    // Reference to  DataBaseHandler to interact with database
    private final DataBaseHandler db;

    /**
     * Constructor initializes model with a reference to shared DataBaseHandler
     * instance (singleton).
     */
    public UserAccountModel() {
        this.db = DataBaseHandler.getDB();
    }

    public void deleteTicket() {

        // deletes a ticket through DataBaseHandler
        db.deleteRecordTicket();
    }

}
