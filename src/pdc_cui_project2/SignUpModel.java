package pdc_cui_project2;

/**
 *
 * @author Daniil
 */
public class SignUpModel {
    private DataBaseHandler db;

    public SignUpModel() {
        this.db = DataBaseHandler.getDB();
    }

    public void createUser(String name, String lname, String email, String type,
            String password) {
        // Validation logic can be added here if needed
        switch (type) {
            case "Assistant":
                db.insertRecordUsers(new Assistant(name, lname, email, password));
                break;
            case "Student":
                db.insertRecordUsers(new Student(name, lname, email, password));
                break;
            case "Customer":
                db.insertRecordUsers(new Customer(name, lname, email, password));
                break;
            // Handle other user types if needed
        }
    }

    // Validation methods can be added here if they are generic and reusable
}

