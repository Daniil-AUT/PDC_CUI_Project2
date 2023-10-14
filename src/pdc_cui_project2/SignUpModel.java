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
                break;
            case STUDENT:
                User student = new Student(name, lname, email, password);
                db.insertRecordUsers(student);
                break;
            case CUSTOMER:
                User customer = new Customer(name, lname, email, password);
                db.insertRecordUsers(customer);
                break;
                
        }
    }

}

