package pdc_cui_project2;

/**
 * SignUpModel manages creation of user accounts and directs to 
 * appropriate page based on user type. The model interacts with database.
 * 
 * @author Daniil
 */
public class SignUpModel {
    
    // Reference to the database handler
    private final DataBaseHandler db;
    
    // ENUM for identifyinh user types
    public UserType types;
    
    // Constructor intialises the databasehandler by referencing the class 
    public SignUpModel() {
        this.db = DataBaseHandler.getDB();
    }
    
    // ENUM shows different categories for user types
    public enum UserType {
        ASSISTANT,
        STUDENT,
        CUSTOMER
    }

    /*
    Creates a user account based on given information.
    Validates input, formats names, and inserts the user record into database.
    Directs user to the appropriate page based on their user type. 
    */
    public void createUser(String name, String lname, String email, UserType type,
            String password) {
        
        // Format names to capitalise first letter
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();
        
        // Create user based on their type
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
    
    // Get user id from the database
    public String getID() {
        return db.getUserID();
    }
    
    // Get user details from database
    public String getDetails() {
        return db.getUserDetails();
    }
    
    // Direct user to appropriate page based on their user type.
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

