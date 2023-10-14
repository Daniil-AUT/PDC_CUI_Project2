package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.LineBorder;

/**
 *
 * @author Daniil
 */
public class LoginController {

    private final LoginView view;
    private final LoginModel model;

    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
        attachListeners();
    }

    private enum UserType {
        ASSISTANT,
        STUDENT,
        CUSTOMER
    }
    
    private enum Field {
        ID,
        PASSWORD
    }
    
    private enum Page {
        HOME,
        ACCOUNT
    }
    
    private void attachListeners() {
        toHomeScreen();
        toAccountScreen();
    }

    private void toHomeScreen() {
        view.backButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked on Home");
                directToPage(Page.HOME, null);
            }
        });
    }
    
    private String searchType(UserType type) {
        switch(type) {
            case ASSISTANT:
                return "Assistant";
            case CUSTOMER:
                return "Customer";
            case STUDENT:
                return "Student";
            default:
                return "";
        }
    }
    
    private void toAccountScreen() {
        view.loginButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = view.idField.getText();
                char[] comps = view.passField.getPassword();
                String password = new String(comps);

                if (idValid(id) == passwordValid(password)) {
                    UserType type = UserType.STUDENT;
                    setDefault(Field.ID);
                    if(idValid(id) && passwordValid(password)) {
                        if (view.assistant.isSelected()) {
                            type = UserType.ASSISTANT;
                        } else if (view.customer.isSelected()) {
                            type = UserType.CUSTOMER;
                        } else if (view.student.isSelected()) {
                            type = UserType.STUDENT;
                        }
                        
                        String search = searchType(type);
                        System.out.println("ENTERING AS: " + search);
                        if (model.checkIdExist(id, search)) {
                            if (model.passwordMatch(password, id)) {
                                setDefault(Field.PASSWORD);
                                model.db.checkTicketStatus(id.toUpperCase());
                                directToPage(Page.ACCOUNT, type);
                            } 
                            else {
                                setError("The Password is Invalid, Try Again.", 
                                        Field.PASSWORD);
                            }
                        } else {
                            setError("No ID Found, Try Different User Category", 
                                    Field.ID);
                        }
                    }
                }
            }
        });
    }
    
    private void directToPage(Page page, UserType type) {
        WindowManager.getManager().setLoginVisible(false);
        switch(page) {
            case ACCOUNT:        
                if(type.equals(UserType.ASSISTANT)) {
                    WindowManager.getManager().setAssistantAccountVisible(true);
                }
                else {
                    WindowManager.getManager().setUserAccountVisible(true); 
                }
                break;
            case HOME:
                WindowManager.getManager().setHomeVisible(true);
                break;
            default:
                break;
        }   
    }
    
    private void setError(String errorMessage, Field field) {
        if (field.equals(Field.ID)) {
            view.idLabel.setText("ID: " + errorMessage);
            view.idLabel.setForeground(Color.red);
            view.idField.setBorder(new LineBorder(Color.red, 1));
        }
        if (field.equals(Field.PASSWORD)) {
            view.passLabel.setText("Password: " + errorMessage);
            view.passLabel.setForeground(Color.red);
            view.passField.setBorder(new LineBorder(Color.red, 1));
        }
    }

    private void setDefault(Field field) {
        if (field.equals(Field.ID)) {
            view.idLabel.setText("ID:");
            view.idLabel.setForeground(Color.black);
            view.idField.setBorder(new LineBorder(Color.black, 1));
        }
        if (field.equals(Field.PASSWORD)) {
            view.passLabel.setText("Password:");
            view.passLabel.setForeground(Color.black);
            view.passField.setBorder(new LineBorder(Color.black, 1));
        }
    }

    private boolean passwordValid(String password) {
        if (password.length() <= 7 || password.contains(" ")) {
            setError("Must Be Greater Than 7 Characters In Length and Cannot "
                    + "Contain Blank Spaces", Field.PASSWORD);
            return false;
        }
        setDefault(Field.PASSWORD);
        return true;
    }

    private boolean idValid(String id) {
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);

        if (id.isBlank()) {
            setError("Cannot Be Left Blank", Field.ID);
            return false;
        }
        if (id.contains(" ")) {
            setError("Cannot Have Blank Spaces", Field.ID);
            return false;
        }
        if (!matcher.matches()) {
            setError("Can Only Have Letters and Numbers (No Special Characters)",
                    Field.ID);
            return false;
        }
        setDefault(Field.ID);
        return true;
    }
}
