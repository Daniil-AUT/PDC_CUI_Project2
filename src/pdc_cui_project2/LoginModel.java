package pdc_cui_project2;

/**
 * Model class for the login page which handles interaction with the database
 * where it checks if the entered ID exists, matches passwords, and sets
 * ticket status.
 * 
 * @author Daniil
 */
public class LoginModel {
    
    // Reference the DataBaseHandle
    private final DataBaseHandler db;
    
    // Enum which identifies different user types
    public UserType userType;
    
    // Initialise model by geting reference to the database handler.
    public LoginModel() {
        this.db = DataBaseHandler.getDB();
    }
    
    // Shows ENUM for different user types
    public enum UserType {
        ASSISTANT,
        CUSTOMER,
        STUDENT
    }
    
    // Check to see if ID exists for a given user type.
    public boolean checkIdExist(String id, String type) {
        System.out.println(type);
        boolean valid = db.checkIdExist(id.toUpperCase(), type);
        return valid;
    }
    
    // Check if password matches given user ID.
    public boolean passwordMatch(String password, String id) {
        return db.passwordMatch(password, id);
    }
    
    // Set ticket status given user ID.
    public void setTicketStatus(String id) {
        db.checkTicketStatus(id);
    }
}
