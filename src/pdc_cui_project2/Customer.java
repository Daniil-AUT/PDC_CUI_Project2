package pdc_cui_project2;

/**
 * Responsible for setting up customer type by extending the User class.
 * Each customer has a unique ID following format (letters UK, 3 letters, and 3
 * numbers). The Customer class is a child class of User parent class.
 * @author Daniil
 */

public final class Customer extends User {

    private final String customerID;
    
    /*
    Instantiate the Customer constructor with user super class
    and unqiue ID by calling the createID method
    */
    public Customer(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
        this.customerID = createID();
    }
    
    // Override toString method so that it prints all details for Customer type.
    @Override
    public String toString() {
        return super.toString() + "\nCustomer ID: " + customerID;
    }
    
    /*
    Implements abstract method which creates id based on specific format
    and checks the id existence in the table, if exists, the function calls
    itself again.
    */
    @Override
    public String createID() {
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
    
    //Create a getter method to get the id of a Customer class
    @Override
    public String getID() {
        return this.customerID;
    }
    
    // Getter method for the sub-user class (Customer)
    @Override
    public String getUserClass() {
        return "Customer";
    }
}