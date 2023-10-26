package pdc_cui_project2;


/**
 * Responsible for setting up assistant type by extending the User class.
 * Each assistant has a unique ID following format (letters AS, and 6 numbers).
 * The Assistant class is a child class of User parent class.
 * @author Daniil
 */

public final class Assistant extends User {
    private final String assistantID;
    
    /*
    Instantiate the Assistant constructor with user super class
    and unqiue ID by calling the createID method
    */
    public Assistant(String name, String lastName, String email, String password) {
        super(name, lastName, email, password);
        this.assistantID = createID();
    }
    
    // Override toString method so that it prints all details for Assistant type.
    @Override
    public String toString() {
        return super.toString() + "\nAssistant ID: " + assistantID;
    }
    
    /*
    Implements abstract method which creates id based on specific format
    and checks the id existence in the table, if exists, the function calls
    itself again.
    */
    @Override
    public String createID() {
        String component = "AS";
        for (int i = 0; i < 6; i++) {
            int number = this.rand.nextInt(10);
            component += number;
        }
        if(this.db.checkIdExist(component, "Assistant")) {
            return createID();
        }
        
        return component;
    }
    
    //Create a getter method to get the id of Assistant class
    @Override
    public String getID() {
        return this.assistantID;
    }
    
    // Getter method for the sub-user class (Assistant)
    @Override
    public String getUserClass() {
        return "Assistant";
    }
}