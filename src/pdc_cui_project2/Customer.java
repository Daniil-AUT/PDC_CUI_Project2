package pdc_cui_project2;

import java.util.Random;

/**
 *
 * @author Daniil
 */

public final class Customer extends User {

    private final String customerID;
    private final DataBaseHandler db;
    // Constructor will take in arguments passed to super
    // and set the id based on the method
    public Customer(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
        
        this.db = DataBaseHandler.getDB();
        this.customerID = createID();
    }

    // The toString method is overriden
    // to include the toString of the super class
    // and additionally include the id variable of child class
    @Override
    public String toString() {
        return super.toString() + "\nCustomer ID: " + customerID;
    }
    
    // Generates a unique id based on the child class, the
    // id consists of 8 digits/characters and is of string format
    // returns the id string
    @Override
    public String createID() {
        Random rand = new Random();
        String component = "UK";
        for (int i = 0; i < 3; i++) {
            int letter = super.rand.nextInt(26) + 65;
            component += (char) letter;
        }
        for (int i = 0; i < 3; i++) {
            int number = super.rand.nextInt(10);
            component += number;
        }
        if(db.checkIdExist(component, "Customer")) {
            return createID();
        }
        
        return component;
    }
    
    // Override getID method to get customer id
    @Override
    public String getID() {
        return this.customerID;
    }
    // The folder name method is overriden
    // so that it returns the string based
    // on the class

    @Override
    public String getUserClass() {
        return "Customer";
    }
}