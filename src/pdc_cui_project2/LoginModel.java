package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class LoginModel {
    public DataBaseHandler db;
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

        if (!valid) {
            return false;
        }
        
        return true;
    }

    public boolean passwordMatch(String password, String id) {
        if(!db.passwordMatch(password, id)) {   
            return false;
        }
        return true;
    }
    
    public void setTicketStatus(String id) {
        db.checkTicketStatus(id);
    }

}