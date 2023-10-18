package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class LoginModel {

    private final DataBaseHandler db;
    public UserType userType;

    public LoginModel() {
        this.db = DataBaseHandler.getDB();
    }

    public enum UserType {
        ASSISTANT,
        CUSTOMER,
        STUDENT
    }

    public boolean checkIdExist(String id, String type) {
        System.out.println(type);
        boolean valid = db.checkIdExist(id.toUpperCase(), type);
        return valid;
    }

    public boolean passwordMatch(String password, String id) {
        return db.passwordMatch(password, id);
    }

    public void setTicketStatus(String id) {
        db.checkTicketStatus(id);
    }
}
