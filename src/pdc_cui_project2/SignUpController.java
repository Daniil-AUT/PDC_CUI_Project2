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
    private final SignUpView view;
    private final SignUpModel model;
    
    public SignUpController(SignUpView view, SignUpModel model) {
        this.view = view;
        this.model = model;
        DataBaseHandler.getDB();
        attachListeners();
    }
    
    private enum UserType {
        ASSISTANT,
        STUDENT,
        CUSTOMER
    }
    
    private enum Field {
        NAME,
        LNAME,
        EMAIL,
        PASS,
        CPASS
    }
    
    private void attachListeners() {
        toHomeScreen();
        toAccountScreen();
    }
    
    private void toHomeScreen() {
        view.backButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Home button has been pressed");
                    WindowManager.getManager().setHomeVisible(true);
                    WindowManager.getManager().setSignUpVisible(false);
                }
            });
    }
    
    private void toAccountScreen() {
        view.signupButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Signup button has been pressed");
                    if(isNameValid() == isLNameValid() ==
                            isEmailValid() == passwordMatch()) {
                        String name = view.name.getText();
                        String lName = view.lastName.getText();
                        String email = view.email.getText();
                        char[] passChar = view.password.getPassword();
                        String password = new String(passChar);
                        if(isNameValid() && isLNameValid() &&
                            isEmailValid() && passwordMatch()) {
                            UserType type = UserType.STUDENT;
                            if (view.assistant.isSelected()) {
                                model.createUser(name, lName, email, 
                                        model.types.ASSISTANT, password);
                                type = UserType.ASSISTANT;
                            } else if (view.student.isSelected()) {
                                model.createUser(name, lName, email, 
                                        model.types.STUDENT, password);
                                type = UserType.STUDENT;
                            } else if (view.customer.isSelected()) {
                                model.createUser(name, lName, email, 
                                        model.types.CUSTOMER, password);
                                type = UserType.CUSTOMER;
                            }
                            directToPage(type);
                        }
                    }
                }
            });
    }
    
    private void directToPage(UserType type) {
        WindowManager.getManager().setSignUpVisible(false);
        if(type.equals(UserType.ASSISTANT)) {
            WindowManager.getManager().setAssistantAccountVisible(true);
        }  
        else {
            WindowManager.getManager().setUserAccountVisible(true);
        }
    }
    private void setError(String errorMessage, Field field) {
        switch(field) {
            case NAME:
                view.nameLabel.setText("Name: " + errorMessage);
                view.nameLabel.setForeground(Color.red);
                view.name.setBorder(new LineBorder(Color.red, 1));
                break;
            case LNAME:
                view.lName.setText("Last Name: " + errorMessage);
                view.lName.setForeground(Color.red);
                view.lastName.setBorder(new LineBorder(Color.red, 1));
                break;
            case EMAIL:
                view.emailLabel.setText("Email: " + errorMessage);
                view.emailLabel.setForeground(Color.red);
                view.email.setBorder(new LineBorder(Color.red, 1));
                break;
            case PASS:
                view.passLabel.setText("Password: " + errorMessage);
                view.passLabel.setForeground(Color.red);
                view.password.setBorder(new LineBorder(Color.red, 1));
                break;
            case CPASS:
                view.cPassLabel.setText("Confirm Password: " + errorMessage);
                view.cPassLabel.setForeground(Color.red);
                view.confPassword.setBorder(new LineBorder(Color.red, 1));
                view.passLabel.setText("Password: " + errorMessage);
                view.passLabel.setForeground(Color.red);
                view.password.setBorder(new LineBorder(Color.red, 1));
                break;
        }
    }

    private void setDefault(Field field) {
        switch(field) {
            case NAME:
                view.nameLabel.setText("Name:");
                view.nameLabel.setForeground(Color.black);
                view.name.setBorder(new LineBorder(Color.black, 1));
                break;
            case LNAME:
                view.lName.setText("Last Name:");
                view.lName.setForeground(Color.black);
                view.lastName.setBorder(new LineBorder(Color.black, 1));
                break;
            case EMAIL:
                view.emailLabel.setText("Email:");
                view.emailLabel.setForeground(Color.black);
                view.email.setBorder(new LineBorder(Color.black, 1));
                break;
            case PASS:
                view.passLabel.setText("Password:");
                view.passLabel.setForeground(Color.black);
                view.password.setBorder(new LineBorder(Color.black, 1));
                break;
            case CPASS:
                view.passLabel.setText("Password:");
                view.passLabel.setForeground(Color.black);
                view.password.setBorder(new LineBorder(Color.black, 1));
                view.cPassLabel.setText("Confirm Password:");
                view.cPassLabel.setForeground(Color.black);
                view.confPassword.setBorder(new LineBorder(Color.black, 1));
                break;            
        }
    }
    
    private boolean isNameValid() {
        String name = view.name.getText();
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        
        if(name.isBlank()) {
            setError("Must Contain At Least 1 Character", Field.NAME);
            return false;
        }
        if (!matcher.matches()) {
            setError("Must Only Contain Letters (No Blank Spaces)", Field.NAME);
            return false;
        }
        
        setDefault(Field.NAME);
        return true;
    }
    
    private boolean isLNameValid() {
        String lastname = view.lastName.getText();
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(lastname);
        
        if(lastname.isBlank()) {
            setError("Must Contain At Least 1 Character", Field.LNAME);
            return false;
        }
        if (!matcher.matches()) {
            setError("Must Only Contain Letters (No Blank Spaces)", Field.LNAME);
            return false;
        }
        setDefault(Field.LNAME);
        return true;
    }
    private boolean isEmailValid() {
        String email = view.email.getText();
        String regex = "^[a-zA-Z0-9]+@mail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        
        if(email.isBlank()) {
            setError("Cannot Be Left Blank", Field.EMAIL);
            return false;
        }
        if(email.contains(" ")) {
            setError("Cannot Have Blank Spaces",Field.EMAIL);
            return false;
        }
        if (!matcher.matches()) {
            setError("Cannot contain special characters "
                    + "and have to end with '@mail.com'", Field.EMAIL);
            return false;
        }
        setDefault(Field.EMAIL);
        return true;
    }
    
    private boolean passwordMatch() {
        char[] password = view.password.getPassword();
        char[] confPassword = view.confPassword.getPassword();
        boolean passwordsMatch = Arrays.equals(password, confPassword);
        if(password.length <= 7) {
            setError("Must Be Greater Than 7 Characters In Length",
                    Field.PASS);
            return false;
        }
        for(char c : password) {
            if(c == ' ') {
                setError("Blank Spaces Are Not Allowed", Field.PASS);
                return false;
            }
        }
        if (!passwordsMatch) {
            setError("Passwords Are Not The Same", Field.CPASS);
            return false;
        }
        
        setDefault(Field.CPASS);
        return true;
    }
}
