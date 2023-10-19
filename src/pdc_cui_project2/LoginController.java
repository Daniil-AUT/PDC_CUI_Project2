package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.border.LineBorder;

/**
 * Controller class for the login page. Handles user input validation, button
 * events, and navigation between pages. Acts as an mediator between the GUI and
 * data handling.
 *
 * @author Daniil
 */
public class LoginController {

    // Reference view and model components
    private final LoginView view;
    private final LoginModel model;

    // Constants for error messages and input validation (easy to understand)
    private static final String INVALID_PASSWORD_ERROR = "Must Be Greater Than 7 "
            + "Characters In Length and Cannot Contain Blank Spaces";
    private static final String INVALID_ID_BLANK_ERROR = "Cannot Be Left Blank";
    private static final String INVALID_ID_SPACE_ERROR = "Cannot Have Blank Spaces";
    private static final String INVALID_ID_CHARACTERS_ERROR = "Can Only Have Letters "
            + "and Numbers (No Special Characters)";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]+$";
    private static final String PASSWORD_MISMATCH = "The Password is Invalid, "
            + "Try Again.";
    private static final String INVALID_ID_NOT_FOUND = "No ID Found, Try Different "
            + "User Category";
    private static final int MIN_PASSWORD_LENGTH = 7;

    /*
    * Instatiate Constructor for LoginController
    * Reference the Login view and model.
    * Attach listeners to GUI components for button/field events
     */
    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
        attachListeners();
    }

    // ENUM for user types set string value for each
    public enum UserType {
        ASSISTANT("Assistant"),
        STUDENT("Student"),
        CUSTOMER("Customer");

        private final String stringValue;

        UserType(String stringValue) {
            this.stringValue = stringValue;
        }
    }

    // ENUMs for input fields and pages
    private enum Field {
        ID,
        PASSWORD
    }

    private enum Page {
        HOME,
        ACCOUNT
    }

    // Attaches listeners to GUI components (view)
    private void attachListeners() {
        toHomeScreen();
        toAccountScreen();
    }

    // Set up the listener for navigating to home screen
    private void toHomeScreen() {
        view.backButton.addActionListener((ActionEvent e) -> {
            directToPage(Page.HOME, null);
        });
    }

    // Set up the listener for logging in and navigating to the account screen
    private void toAccountScreen() {
        view.loginButton.addActionListener((ActionEvent e) -> {
            String id = view.idField.getText();
            char[] comps = view.passField.getPassword();
            String password = new String(comps);
            validateEntries(id, password);
        });
    }

    // Validate user entries and handle navigation based conditions
    private void validateEntries(String id, String password) {

        // Check the logic before proceeding to account page
        if (validateId(id) == validatePassword(password)) {
            if (validateId(id) && validatePassword(password)) {
                setDefault(Field.ID);
                if (model.checkIdExist(id, selectType())) {
                    if (model.passwordMatch(password, id)) {
                        setDefault(Field.PASSWORD);
                        model.setTicketStatus(id);
                        directToPage(Page.ACCOUNT, selectType());
                    } else {
                        setError(PASSWORD_MISMATCH, Field.PASSWORD);
                    }
                }
            }
        }
    }

    // Select user type based on the selected radio button
    private String selectType() {

        // Use conditional statements to set user type
        String type = UserType.STUDENT.stringValue;
        if (view.assistant.isSelected()) {
            type = UserType.ASSISTANT.stringValue;
        } else if (view.customer.isSelected()) {
            type = UserType.CUSTOMER.stringValue;
        } else if (view.student.isSelected()) {
            type = UserType.STUDENT.stringValue;
        }
        return type;
    }

    // Validate user ID
    private boolean validateId(String id) {
        Matcher matcher = Pattern.compile(PASSWORD_REGEX).matcher(id);

        // Use condition statements to validate ID
        if (id.isBlank()) {
            setError(INVALID_ID_BLANK_ERROR, Field.ID);
            return false;
        }
        if (id.contains(" ")) {
            setError(INVALID_ID_SPACE_ERROR, Field.ID);
            return false;
        }
        if (!matcher.matches()) {
            setError(INVALID_ID_CHARACTERS_ERROR, Field.ID);
            return false;
        }
        if (!model.checkIdExist(id, selectType())) {
            setError(INVALID_ID_NOT_FOUND, Field.ID);
            return false;
        }
        setDefault(Field.ID);
        return true;
    }

    // Validate user password
    private boolean validatePassword(String password) {

        // Conditional statement to check whether the password is valid
        if (password.length() <= MIN_PASSWORD_LENGTH || password.contains(" ")) {
            setError(INVALID_PASSWORD_ERROR, Field.PASSWORD);
            return false;
        }
        setDefault(Field.PASSWORD);
        return true;
    }

    // Navigate to the specific page based on the user type
    private void directToPage(Page page, String type) {
        WindowManager.getManager().setLoginVisible(false);
        
        // Switch to introduce any new potential pages on LogIn page
        switch (page) {
            case ACCOUNT:
                if (type.equals(UserType.ASSISTANT.stringValue)) {
                    WindowManager.getManager().setAssistantAccountVisible(true);
                } else {
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

    // Display error messages for input fields 
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

    // Set the field labels to default
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
}
