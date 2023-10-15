package pdc_cui_project2;

import java.awt.Color;

/**
 *
 * @author Daniil
 */
public class LoginModel {
    private DataBaseHandler db;
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
        boolean valid = false;
        System.out.println(type);

        valid = db.checkIdExist(id, type);

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

}