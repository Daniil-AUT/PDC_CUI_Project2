package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Daniil
 */
public class LoginController {

    private LoginView view;
    private LoginModel model;

    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
        attachListeners();
    }

    private void attachListeners() {
        toHomeScreen();
        toAccountScreen();
    }

    // 1. Check ID is valid (Combine with radio buttons)
    // 2. Check Password is valid
    public void toHomeScreen() {

        view.backButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked on Home");
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setLoginVisible(false);
            }
        });
    }

    public void toAccountScreen() {
        view.loginButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = view.idField.getText();

                if (idValid(id)) {
                    String type;
                    
                    view.idLabel.setText("ID:");
                    view.idLabel.setForeground(Color.black);
                    
                    if (view.assistant.isSelected()) {
                        type = "Assistant";
                    } else if (view.customer.isSelected()) {
                        type = "Customer";
                    } else {
                        type = "Student";
                    }
                    if (model.checkIdExist(id, type)) {
                        char[] comps = view.passField.getPassword();
                        String password = new String(comps);
                        if(passwordValid(password)) {
                            if(model.passwordMatch(password, id)) {
                                view.passLabel.setText("Password:");
                                view.passLabel.setForeground(Color.black);
                            }
                            else {
                                view.passLabel.setText("Password: The Password is not correct, try again.");
                                view.passLabel.setForeground(Color.red);
                            }
                        }
                    }
                    else {
                        view.idLabel.setText("ID: No ID Found, Try With Different User Category");
                        view.idLabel.setForeground(Color.red);
                    }
                }
            }
        });
    }
    
    public boolean passwordValid(String password) {
        if (password.length() <= 7) {
            view.passLabel.setText("Password: Must Be Greater "
                    + "Than 7 Characters In Length");
            view.passLabel.setForeground(Color.red);
            return false;
        }
        if (password.contains(" ")) {
            view.passLabel.setText("Password: Cannot Contain Blank Spaces");
            view.passLabel.setForeground(Color.red);
            return false;
        }
        return true;
    }
    
    public boolean idValid(String id) {
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);

        if (id.isBlank()) {
            view.idLabel.setText("ID: Cannot Be Left Blank");
            view.idLabel.setForeground(Color.red);
            return false;
        }
        if (id.contains(" ")) {
            view.idLabel.setText("ID: Cannot Have Blank Spaces");
            view.idLabel.setForeground(Color.red);
            return false;
        }
        if (!matcher.matches()) {
            view.idLabel.setText("ID: Can Only Have Letters "
                    + "and Numbers (No Special Characters)");
            view.idLabel.setForeground(Color.red);
            return false;
        }
        view.idLabel.setText("ID:");
        view.idLabel.setForeground(Color.black);
        return true;
    }
}