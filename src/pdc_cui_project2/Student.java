package pdc_cui_project2;

/**
 * Responsible for setting up student type by extending the User class.
 * Each customer has a unique ID following format (4 letters and 4 numbers)
 * The Customer class is a child class of User parent class.
 * @author Daniil
 */


public final class Student extends User {

    private final String studentID;
    
    /*
    Instantiate the Student constructor with user super class
    and unqiue ID by calling the createID method
    */
    public Student(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
        this.studentID = createID();
    }
    
    // Override toString method so that it prints all details for Student type.
    @Override
    public String toString() {
        return super.toString() + "\nStudent ID: " + studentID;
    }
    
    /*
    Implements abstract method which creates id based on specific format
    and checks the id existence in the table, if exists, the function calls
    itself again.
    */
    @Override
    public String createID() {
        String component = "";
        for (int i = 0; i < 4; i++) {
            int letter = super.rand.nextInt(26) + 65;
            component += (char) letter;
        }
        for (int i = 0; i < 4; i++) {
            int number = super.rand.nextInt(10);
            component += number;
        }
        if(db.checkIdExist(component, "Student")) {
            return createID();
        }
        
        return component;
    }
    
    //Create a getter method to get the id of a Student class
    @Override
    public String getID() {
        return this.studentID;
    }

    // Getter method for the sub-user class (Student)
    @Override
    public String getUserClass() {
        return "Student";
    }
}