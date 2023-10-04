package pdc_cui_project2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Daniil
 */
/**
 *
 * @author Daniil
 */
public final class Student extends User {

    private final String studentID;
    
    // Constructor will take in arguments passed to super
    // and set the id based on the method
    public Student(String name, String lastName, String email) {
        super(name, lastName, email);
        this.studentID = createID();
    }

    // The toString method is overriden
    // to include the toString of the super class
    // and additionally include the id variable of child class
    @Override
    public String toString() {
        return super.toString() + "\nStudent ID: " + studentID;
    }
    
    // Generates a unique id based on the child class, the
    // id consists of 8 digits/characters and is of string format
    // returns the id string
    @Override
    public String createID() {

        return "";
    }
    
    // Override getID method to get student id
    @Override
    public String getID() {
        return this.studentID;
    }
    // The folder name method is overriden
    // so that it returns the string based
    // on the class
    @Override
    public String getFolderName() {
        return "students";
    }
}