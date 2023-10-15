package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class SignUpModel {
    public DataBaseHandler db;
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
                db.userID = assistant.getID();
                directToPage(UserType.ASSISTANT);
                break;
            case STUDENT:
                User student = new Student(name, lname, email, password);
                db.insertRecordUsers(student);
                db.userID = student.getID();
                directToPage(UserType.STUDENT);
                break;
            case CUSTOMER:
                User customer = new Customer(name, lname, email, password);
                db.insertRecordUsers(customer);
                db.userID = customer.getID();
                directToPage(UserType.CUSTOMER);
                break;
                
        }
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

