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
                char[] comps = view.passField.getPassword();
                String password = new String(comps);

                if (idValid(id) == passwordValid(password)) {
                    String type;
                    setDefault("ID");

                    if (view.assistant.isSelected()) {
                        type = "Assistant";
                    } else if (view.customer.isSelected()) {
                        type = "Customer";
                    } else {
                        type = "Student";
                    }

                    if (model.checkIdExist(id, type)) {
                        if (model.passwordMatch(password, id)) {
                            setDefault("Password");
                        } else {
                            setError("The Password is Invalid, Try Again.", "Password");
                        }
                    } else {
                        setError("No ID Found, Try Different User Category", "ID");
                    }
                }
            }
        });
    }

    public void setError(String errorMessage, String op) {
        if (op.equals("ID")) {
            view.idLabel.setText("ID: " + errorMessage);
            view.idLabel.setForeground(Color.red);
            view.idField.setBorder(new LineBorder(Color.red, 1));
        }
        if (op.equals("Password")) {
            view.passLabel.setText("Password: " + errorMessage);
            view.passLabel.setForeground(Color.red);
            view.passField.setBorder(new LineBorder(Color.red, 1));
        }
    }

    public void setDefault(String op) {
        if (op.equals("ID")) {
            view.idLabel.setText("ID:");
            view.idLabel.setForeground(Color.black);
            view.idField.setBorder(new LineBorder(Color.black, 1));
        }
        if (op.equals("Password")) {
            view.passLabel.setText("Password:");
            view.passLabel.setForeground(Color.black);
            view.passField.setBorder(new LineBorder(Color.black, 1));
        }
    }

    public boolean passwordValid(String password) {
        if (password.length() <= 7 || password.contains(" ")) {
            setError("Must Be Greater Than 7 Characters In Length and Cannot "
                    + "Contain Blank Spaces", "Password");
            return false;
        }
        setDefault("Password");
        return true;
    }

    public boolean idValid(String id) {
        String regex = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);

        if (id.isBlank()) {
            setError("Cannot Be Left Blank", "ID");
            return false;
        }
        if (id.contains(" ")) {
            setError("Cannot Have Blank Spaces", "ID");
            return false;
        }
        if (!matcher.matches()) {
            setError("Can Only Have Letters and Numbers (No Special Characters)",
                    "ID");
            return false;
        }
        setDefault("ID");
        return true;
    }
}
