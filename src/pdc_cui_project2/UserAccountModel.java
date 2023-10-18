package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class UserAccountModel {
    private final DataBaseHandler db;
    
    public UserAccountModel() {
        this.db = DataBaseHandler.getDB();
    }
    
    public void deleteTicket() {
        db.deleteRecordTicket();
    }

}
