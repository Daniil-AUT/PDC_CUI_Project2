package pdc_cui_project2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Daniil
 */
public final class Assistant extends User {

    private final String assistantID;
    
    // Constructor will take in arguments passed to super
    // and set the id based on the method
    public Assistant(String name, String lastName, String email) {
        super(name, lastName, email);
        this.assistantID = createID();
    }

    /*
     * @return the assistantID
     */
    public String getAssistantID() {
        return assistantID;
    }
    
    // The toString method is overriden
    // to include the toString of the super class
    // and additionally include the id variable of child class
    @Override
    public String toString() {
        return super.toString() + "\nAssistant ID: " + assistantID;
    }
    // The folder name method is overriden
    // so that it returns the string based
    // on the class
    @Override
    public String getFolderName() {
        return "assistants";
    }
    
    // Generates a unique id based on the child class, the
    // id consists of 7 digits/characters and is of string format
    // returns the id string
    @Override
    public String createID() {
        return "";
    }
    // Override getID method to get assistant id
    @Override
    public String getID() {
        return this.assistantID;
    }
}