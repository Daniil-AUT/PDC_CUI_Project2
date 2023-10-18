package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class SignUpModel {
    private final DataBaseHandler db;
    public UserType types;
    
    public SignUpModel() {
        this.db = DataBaseHandler.getDB();
    }
    public enum UserType {
        ASSISTANT,
        STUDENT,
        CUSTOMER
    }

    public void createUser(String name, String lname, String email, UserType type,
            String password) {
        // Validation logic can be added here if needed
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();
        switch (type) {
            case ASSISTANT:
                User assistant = new Assistant(name, lname, email, password);
                db.insertRecordUsers(assistant);
                directToPage(UserType.ASSISTANT);
                break;
            case STUDENT:
                User student = new Student(name, lname, email, password);
                db.insertRecordUsers(student);
                directToPage(UserType.STUDENT);
                break;
            case CUSTOMER:
                User customer = new Customer(name, lname, email, password);
                db.insertRecordUsers(customer);
                directToPage(UserType.CUSTOMER);
                break;
                
        }
    }
    
    public String getID() {
        return db.getUserID();
    }
    public String getDetails() {
        return db.getUserDetails();
    }
    
    private void directToPage(UserType type) {
        
        if(type.equals(UserType.ASSISTANT)) {
            WindowManager.getManager().setAssistantAccountVisible(true);
        }  
        else {
            WindowManager.getManager().setUserAccountVisible(true);
        }
        WindowManager.getManager().setSignUpVisible(false);
    }
}

