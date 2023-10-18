package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Daniil
 */
public class SignUpController {

    private final SignUpView view;
    private final SignUpModel model;
    private static final int MIN_PASSWORD_LENGTH = 7;
    private static final Color ERROR_COLOUR = Color.red;
    private static final Color DEFAULT_COLOUR = Color.black;

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
        view.backButton.addActionListener((ActionEvent e) -> {
            System.out.println("Home button has been pressed");
            WindowManager.getManager().setHomeVisible(true);
            WindowManager.getManager().setSignUpVisible(false);
        });
    }

    private void toAccountScreen() {
        view.signupButton.addActionListener((ActionEvent e) -> {
            validateEntry();
        });
    }

    private void validateEntry() {
        if (checkFields()) {
            if (validateFields()) {
                String name = view.name.getText();
                String lName = view.lastName.getText();
                String email = view.email.getText();
                char[] passChar = view.password.getPassword();
                String password = new String(passChar);
                getUserType(name, lName, email, password);
                resetFields();
                JOptionPane.showMessageDialog(null, "Your Details:\n\n" + model.getDetails());
                System.out.println(model.getID());
            }
        }
    }
    private void resetFields() {
        view.name.setText("");
        view.lastName.setText("");
        view.email.setText("");
        view.password.setText("");
        view.confPassword.setText("");
        view.student.setSelected(true);
    }
    private void getUserType(String name, String lName, String email, String password) {
        if (view.assistant.isSelected()) {
            model.createUser(name, lName, email, model.types.ASSISTANT, password);
        } else if (view.student.isSelected()) {
            model.createUser(name, lName, email, model.types.STUDENT, password);
        } else {
            model.createUser(name, lName, email, model.types.CUSTOMER, password);
        }
    }

    private boolean validateFields() {
        return isNameValid() && isLNameValid() && isEmailValid() && passwordMatch();
    }

    private boolean checkFields() {
        return isNameValid() == isLNameValid() == isEmailValid() == passwordMatch();
    }

    private void setError(String errorMessage, Field field, boolean error) {
        switch (field) {
            case NAME:
                view.nameLabel.setText(error ? "Name (" + errorMessage + "): "
                        : "Name: ");
                view.nameLabel.setForeground(error ? ERROR_COLOUR : DEFAULT_COLOUR);
                view.name.setBorder(new LineBorder(error ? ERROR_COLOUR : DEFAULT_COLOUR, 1));
                break;
            case LNAME:
                view.lName.setText(error ? "Last Name (" + errorMessage + "): "
                        : "Last Name: ");
                view.lName.setForeground(error ? ERROR_COLOUR : DEFAULT_COLOUR);
                view.lastName.setBorder(new LineBorder(error ? ERROR_COLOUR : DEFAULT_COLOUR, 1));
                break;
            case EMAIL:
                view.emailLabel.setText(error ? "Email  (" + errorMessage + "): "
                        : "Email: ");
                view.emailLabel.setForeground(error ? ERROR_COLOUR : DEFAULT_COLOUR);
                view.email.setBorder(new LineBorder(error ? ERROR_COLOUR : DEFAULT_COLOUR, 1));
                break;
            case PASS:
                view.passLabel.setText(error ? "Password (" + errorMessage + "): "
                        : "Password: ");
                view.passLabel.setForeground(error ? ERROR_COLOUR : DEFAULT_COLOUR);
                view.password.setBorder(new LineBorder(error ? ERROR_COLOUR : DEFAULT_COLOUR, 1));
                break;
            case CPASS:
                view.cPassLabel.setText(error ? "Confirm Password (" + errorMessage + "): "
                        : "Confirm Password: ");
                view.cPassLabel.setForeground(error ? ERROR_COLOUR : DEFAULT_COLOUR);
                view.confPassword.setBorder(new LineBorder(error ? ERROR_COLOUR : DEFAULT_COLOUR, 1));
                view.passLabel.setText(error ? "Password (" + errorMessage + "): "
                        : "Password: ");
                view.passLabel.setForeground(error ? ERROR_COLOUR : DEFAULT_COLOUR);
                view.password.setBorder(new LineBorder(error ? ERROR_COLOUR : DEFAULT_COLOUR, 1));
                break;
        }
    }

    private boolean isNameEmpty() {
        return view.name.getText().isBlank();
    }

    private boolean isNameOnlyLetters() {
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(view.name.getText());
        return matcher.matches();
    }

    private boolean isNameValid() {
        if (isNameEmpty()) {
            setError("Must contain at least 1 character", Field.NAME, true);
            return false;
        }
        if (!isNameOnlyLetters()) {
            setError("Must only contain letters (no blank spaces)", Field.NAME, true);
            return false;
        }
        setError("", Field.NAME, false);
        return true;
    }

    private boolean isLNameEmpty() {
        return view.lastName.getText().isBlank();
    }

    private boolean isLNameOnlyLetters() {
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(view.lastName.getText());
        return matcher.matches();
    }

    private boolean isLNameValid() {
        if (isLNameEmpty()) {
            setError("Must contain at least 1 character", Field.LNAME, true);
            return false;
        }
        if (!isLNameOnlyLetters()) {
            setError("Must only contain letters (no blank spaces)", Field.LNAME, true);
            return false;
        }
        setError("", Field.LNAME, false);
        return true;
    }

    private boolean isEmailEmpty() {
        return view.email.getText().isBlank();
    }

    private boolean isEmailValidFormat() {
        String regex = "^[a-zA-Z0-9]+@mail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(view.email.getText());
        return matcher.matches();
    }

    private boolean isEmailValid() {
        if (isEmailEmpty()) {
            setError("Cannot be left blank", Field.EMAIL, true);
            return false;
        }
        if (view.email.getText().contains(" ")) {
            setError("Cannot have blank spaces", Field.EMAIL, true);
            return false;
        }
        if (!isEmailValidFormat()) {
            setError("Cannot contain special characters and have to end with '@mail.com'",
                    Field.EMAIL, true);
            return false;
        }
        setError("", Field.EMAIL, false);
        return true;
    }

    private boolean isPasswordEmpty() {
        return view.password.getPassword().length == 0;
    }

    private boolean isPasswordLongEnough() {
        return view.password.getPassword().length >= MIN_PASSWORD_LENGTH;
    }

    private boolean isPasswordValidFormat() {
        for (char c : view.password.getPassword()) {
            if (c == ' ') {
                return false;
            }
        }
        setError("", Field.PASS, false);
        return true;
    }

    private boolean passwordMatch() {
        if (isPasswordEmpty()) {
            setError("Cannot be left blank", Field.PASS, true);
            return false;
        }
        if (!isPasswordLongEnough()) {
            setError("Must be greater than " + MIN_PASSWORD_LENGTH
                    + " characters in length", Field.PASS, true);
            return false;
        }
        if (!isPasswordValidFormat()) {
            setError("Blank spaces are not allowed", Field.PASS, true);
            return false;
        }
        if (!Arrays.equals(view.password.getPassword(), view.confPassword.getPassword())) {
            setError("Passwords are not the same", Field.CPASS, true);
            return false;
        }
        setError("", Field.CPASS, false);
        return true;
    }
}
