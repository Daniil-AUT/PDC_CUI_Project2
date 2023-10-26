package pdc_cui_project2;

import java.util.Random;

/**
 * User is a high-level class for the user management.
 * This class is abstract which  provides template for other user 
 * categories.
 * This class has common attributes and methods shared to other user types.
 * @author Daniil
 */
public abstract class User {
    // Variables are close for modification
    private final String name;
    private final String lastName;
    private final String email;
    private final String password;
    protected Random rand;
    protected DataBaseHandler db;
    
    /*
    Initialise the user constructor with appropriate
    arguments + instantiate the Random object for generating id's
    */
    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rand = new Random();
        this.db = DataBaseHandler.getDB();
    }
    
    /*
    The abstracts methods will vary from class to class
    depending on the object
    */
    public abstract String createID();
    public abstract String getUserClass();

    public abstract String getID();
    
    
    /*
    Provide getter and setter methods only
    the primitive types are not to be modified
    */
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    /*
    Override toString of User object to display
    general user details (name, last name, email)
    which sets a template for further extension
    */
    @Override
    public String toString() {
        return "Full Name: " + getName() + " " + getLastName() 
                + "\nEmail: " + getEmail();
    }
}