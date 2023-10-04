package pdc_cui_project2;

import java.util.Random;

/**
 *
 * @author Daniil
 */
public abstract class User {
    // Variables are close for modification
    private final String name;
    private final String lastName;
    private final String email;
    public Random rand;
    
    // Initialise the user constructor with appropriate
    // arguments + instantiate the Random object for generating id's
    public User(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.rand = new Random();
    }
    
    // The abstracts methods will vary from class to class
    // depending on the object
    public abstract String createID();

    public abstract String getFolderName();

    public abstract String getID();
    
    // The getter methods are the only ones
    // allowed for this class and its child classes
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    // Override toString to that the User object
    // will be displayed in the following form in string format
    // Shows name, last name and mail of Users
    @Override
    public String toString() {
        return "Full Name: " + getName() + " " + getLastName() 
                + "\nEmail: " + getEmail();
    }
}