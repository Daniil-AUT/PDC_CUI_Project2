package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Daniil
 */
public class SignUpController {
    private SignUpView view;
    private SignUpModel model;
    private User user;
    private DataBaseHandler db;
    
    public SignUpController(SignUpView view, SignUpModel model) {
        this.view = view;
        this.model = model;
        this.db = DataBaseHandler.getDB();
        attachListeners();
    }
    private void attachListeners() {
        System.out.println("Attaching listeners");  
        toHomeScreen();
        toAccountScreen();
    }
    public void toHomeScreen() {
        System.out.println("Setting up Home button listener"); 
        view.backButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Home button has been pressed");
                    WindowManager.getManager().setHomeVisible(true);
                    WindowManager.getManager().setSignUpVisible(false);
                }
            });
    }
    
    public void toAccountScreen() {
        System.out.println("Setting up Signup button listener");  
        view.signupButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Signup button has been pressed");
                    if(isNameValid() && isLNameValid()&&
                            isEmailValid() && passwordMatch()) {
                        String name = view.name.getText();
                        String lName = view.lastName.getText();
                        String email = view.email.getText();
                        char[] passChar = view.password.getPassword();
                        String password = new String(passChar);
                        
                        if(view.assistant.isSelected()) {
                            System.out.println("REACHED ASSISTANT");
                            User assistant = new Assistant(name, lName, email, password);
                            db.insertRecordUsers(assistant);
                        }
                        if(view.student.isSelected()) {
                            System.out.println("REACHED STUDENT");
                            User student = new Student(name, lName, email, password);
                            db.insertRecordUsers(student);
                        }
                        if(view.customer.isSelected()) {
                            System.out.println("REACHED CUSTOMER");
                            User customer = new Customer(name, lName, email, password);
                            System.out.println(customer.getUserClass());
                            db.insertRecordUsers(customer);
                        }
                    }
                }
            });
    }
    public boolean isNameValid() {
        String name = view.name.getText();
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        
        if(name.isBlank()) {
            view.nameLabel.setText("Name: Must Contain Atleast 1 Character");
            view.nameLabel.setForeground(Color.red);
            return false;
        }
        if (!matcher.matches()) {
            view.nameLabel.setText("Name: Must Only Have Letters");
            view.nameLabel.setForeground(Color.red);
            return false;
        }
        else {
            view.nameLabel.setText("Name:");
            view.nameLabel.setForeground(Color.black);
        }
        return true;
    }
    public boolean isLNameValid() {
        String lastname = view.lastName.getText();
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(lastname);
        
        if(lastname.isBlank()) {
            view.lName.setText("Last Name: Must Contain Atleast 1 Character");
            view.lName.setForeground(Color.red);
            return false;
        }
        if (!matcher.matches()) {
            view.lName.setText("Last Name: Must Only Have Letters");
            view.lName.setForeground(Color.red);
            return false;
        }
        else {
            view.lName.setText("Last Name:");
            view.lName.setForeground(Color.black);
        }
        return true;
    }
    public boolean isEmailValid() {
        String email = view.email.getText();
        String regex = "^[a-zA-Z0-9]+@mail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        
        if(email.isBlank()) {
            view.emailLabel.setText("Email: Cannot Be Left Blank");
            view.emailLabel.setForeground(Color.red);
            return false;
        }
        if(email.contains(" ")) {
            view.emailLabel.setText("Email: Cannot Have Blank Spaces");
            view.emailLabel.setForeground(Color.red);
        }
        if (!matcher.matches()) {
            view.emailLabel.setText("Email: Cannot contain special characters "
                    + "and have to end with '@mail.com'");
            view.emailLabel.setForeground(Color.red);
            return false;
        }
        else {
            view.emailLabel.setText("Email:");
            view.emailLabel.setForeground(Color.black);
        }
        return true;
    }
    
    public boolean passwordMatch() {
        char[] password = view.password.getPassword();
        char[] confPassword = view.confPassword.getPassword();
        boolean passwordsMatch = Arrays.equals(password, confPassword);
        if(password.length <= 7) {
            view.passLabel.setText("Password: The Password Must Be Greater "
                    + "Than 7 Characters In Length");
            view.passLabel.setForeground(Color.red);
            return false;
        }
        for(char c : password) {
            if(c == ' ') {
                view.passLabel.setText("Password: Blank Spaces Are Not Allowed");
                view.passLabel.setForeground(Color.red);
                return false;
            }
        }
        if (!passwordsMatch) {
            view.passLabel.setText("Password: Passwords Are Not The Same");
            view.passLabel.setForeground(Color.red);
            view.cPassLabel.setText("Confirm Password: Passwords Are Not The Same");
            view.cPassLabel.setForeground(Color.red);
            return false;
        }
        
        view.passLabel.setText("Password:");
        view.passLabel.setForeground(Color.black);
        view.cPassLabel.setText("Confirm Password:");
        view.cPassLabel.setForeground(Color.black);
        return true;
    }
}
