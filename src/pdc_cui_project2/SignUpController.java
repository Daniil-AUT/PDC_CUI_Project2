package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.LineBorder;

/**
 *
 * @author Daniil
 */
public class SignUpController {
    private SignUpView view;
    private SignUpModel model;
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
                    if(isNameValid() == isLNameValid()==
                            isEmailValid() == passwordMatch() == true) {
                        String name = view.name.getText();
                        String lName = view.lastName.getText();
                        String email = view.email.getText();
                        char[] passChar = view.password.getPassword();
                        String password = new String(passChar);
                        
                        if(view.assistant.isSelected()) {
                            model.createUser(name, lName, email, "Assistant", password);
                        }
                        if(view.student.isSelected()) {
                            model.createUser(name, lName, email, "Student", password);
                        }
                        if(view.customer.isSelected()) {
                            model.createUser(name, lName, email, "Customer", password);
                        }
                    }
                }
            });
    }
    
    public void setError(String errorMessage, String op) {
        switch(op) {
            case "name":
                view.nameLabel.setText("Name: " + errorMessage);
                view.nameLabel.setForeground(Color.red);
                view.name.setBorder(new LineBorder(Color.red, 1));
                break;
            case "lname":
                view.lName.setText("Last Name: " + errorMessage);
                view.lName.setForeground(Color.red);
                view.lastName.setBorder(new LineBorder(Color.red, 1));
                break;
            case "email":
                view.emailLabel.setText("Email: " + errorMessage);
                view.emailLabel.setForeground(Color.red);
                view.email.setBorder(new LineBorder(Color.red, 1));
                break;
            case "pass":
                view.passLabel.setText("Password: " + errorMessage);
                view.passLabel.setForeground(Color.red);
                view.password.setBorder(new LineBorder(Color.red, 1));
                break;
            case "cpass":
                view.cPassLabel.setText("Confirm Password: " + errorMessage);
                view.cPassLabel.setForeground(Color.red);
                view.confPassword.setBorder(new LineBorder(Color.red, 1));
                view.passLabel.setText("Password: " + errorMessage);
                view.passLabel.setForeground(Color.red);
                view.password.setBorder(new LineBorder(Color.red, 1));
                break;
        }
    }

    public void setDefault(String op) {
        switch(op) {
            case "name":
                view.nameLabel.setText("Name:");
                view.nameLabel.setForeground(Color.black);
                view.name.setBorder(new LineBorder(Color.black, 1));
                break;
            case "lname":
                view.nameLabel.setText("Last Name:");
                view.nameLabel.setForeground(Color.black);
                view.name.setBorder(new LineBorder(Color.black, 1));
                break;
            case "email":
                view.lName.setText("Email:");
                view.lName.setForeground(Color.black);
                view.lastName.setBorder(new LineBorder(Color.black, 1));
                break;
            case "pass":
                view.passLabel.setText("Password:");
                view.passLabel.setForeground(Color.black);
                view.password.setBorder(new LineBorder(Color.black, 1));
                break;
            case "cpass":
                view.passLabel.setText("Password:");
                view.passLabel.setForeground(Color.black);
                view.password.setBorder(new LineBorder(Color.black, 1));
                view.cPassLabel.setText("Confirm Password:");
                view.cPassLabel.setForeground(Color.black);
                view.confPassword.setBorder(new LineBorder(Color.black, 1));
                break;            
        }
    }
    
    public boolean isNameValid() {
        String name = view.name.getText();
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        
        if(name.isBlank()) {
            setError("Must Contain Atleast 1 Character", "name");
            return false;
        }
        if (!matcher.matches()) {
            setError("Must Only Have Letters", "name");
            return false;
        }
        
        setDefault("name");
        return true;
    }
    
    public boolean isLNameValid() {
        String lastname = view.lastName.getText();
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(lastname);
        
        if(lastname.isBlank()) {
            setError("Must Contain Atleast 1 Character", "lname");
            return false;
        }
        if (!matcher.matches()) {
            setError("Must Only Have Letters", "lname");
            return false;
        }
        setDefault("lname");
        return true;
    }
    public boolean isEmailValid() {
        String email = view.email.getText();
        String regex = "^[a-zA-Z0-9]+@mail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        
        if(email.isBlank()) {
            setError("Cannot Be Left Blank", "email");
            return false;
        }
        if(email.contains(" ")) {
            setError("Cannot Have Blank Spaces","email");
            return false;
        }
        if (!matcher.matches()) {
            setError("Cannot contain special characters "
                    + "and have to end with '@mail.com'", "email");
            return false;
        }
        setDefault("email");
        return true;
    }
    
    public boolean passwordMatch() {
        char[] password = view.password.getPassword();
        char[] confPassword = view.confPassword.getPassword();
        boolean passwordsMatch = Arrays.equals(password, confPassword);
        if(password.length <= 7) {
            setError("Must Be Greater Than 7 Characters In Length",
                    "pass");
            return false;
        }
        for(char c : password) {
            if(c == ' ') {
                setError("Blank Spaces Are Not Allowed", "pass");
                return false;
            }
        }
        if (!passwordsMatch) {
            setError("Passwords Are Not The Same", "cpass");
            return false;
        }
        
        setDefault("cpass");
        return true;
    }
}
