package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class SignUpModel {
    private DataBaseHandler db;
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
                db.insertRecordUsers(new Assistant(name, lname, email, password));
                break;
            case STUDENT:
                db.insertRecordUsers(new Student(name, lname, email, password));
                break;
            case CUSTOMER:
                db.insertRecordUsers(new Customer(name, lname, email, password));
                break;
            // Handle other user types if needed
        }
    }

    // Validation methods can be added here if they are generic and reusable
}

