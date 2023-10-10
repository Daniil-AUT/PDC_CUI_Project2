package pdc_cui_project2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Daniil
 */
public final class Customer extends User {

    private final String unknownID;
    // Constructor will take in arguments passed to super
    // and set the id based on the method
    public Customer(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
        this.unknownID = createID();

    }

    /**
     * @return the unknownID
     */
    // The folder name method is overriden
    // so that it returns the string based
    // on the class
    @Override
    public String getFolderName() {
        return "customers";
    }
    // The toString method is overriden
    // to include the toString of the super class
    // and additionally include the id variable of child class
    @Override
    public String toString() {
        return super.toString() + "\nUser ID: " + unknownID;
    }
    
    // Generates a unique id based on the child class, the
    // id consists of 8 digits/characters and is of string format
    @Override
    public String createID() {
        return "";
    }
    
    // Override getID method to get unknown customer id
    @Override
    public String getID() {
        return this.unknownID;
    }
}
