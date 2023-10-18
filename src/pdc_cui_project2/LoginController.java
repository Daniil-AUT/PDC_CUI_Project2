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

    private static final String INVALID_PASSWORD_ERROR = "Must Be Greater Than 7 "
            + "Characters In Length and Cannot Contain Blank Spaces";
    private static final String INVALID_ID_BLANK_ERROR = "Cannot Be Left Blank";
    private static final String INVALID_ID_SPACE_ERROR = "Cannot Have Blank Spaces";
    private static final String INVALID_ID_CHARACTERS_ERROR = "Can Only Have Letters "
            + "and Numbers (No Special Characters)";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]+$";
    private static final int MIN_PASSWORD_LENGTH = 8;

    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
        attachListeners();
    }

    public enum UserType {
        ASSISTANT("Assistant"),
        STUDENT("Student"),
        CUSTOMER("Customer");

        private final String stringValue;

        UserType(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }
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
                directToPage(Page.HOME, null);
            }
        });
    }

    private void toAccountScreen() {
        view.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = view.idField.getText();
                char[] comps = view.passField.getPassword();
                String password = new String(comps);

                if (validateId(id) && validatePassword(password)) {
                    setDefault(Field.ID);
                    
                    if (model.checkIdExist(id, selectType())) {
                        if (model.passwordMatch(password, id)) {
                            setDefault(Field.PASSWORD);
                            model.setTicketStatus(id);
                            directToPage(Page.ACCOUNT, selectType());
                        } else {
                            setError("The Password is Invalid, Try Again.", Field.PASSWORD);
                        }
                    } else {
                        setError("No ID Found, Try Different User Category", Field.ID);
                    }
                }
            }
        });
    }
    private String selectType() {
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
    private boolean validateId(String id) {
        Matcher matcher = Pattern.compile(PASSWORD_REGEX).matcher(id);

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
        setDefault(Field.ID);
        return true;
    }

    private boolean validatePassword(String password) {
        if (password.length() <= MIN_PASSWORD_LENGTH || password.contains(" ")) {
            setError(INVALID_PASSWORD_ERROR, Field.PASSWORD);
            return false;
        }
        setDefault(Field.PASSWORD);
        return true;
    }

    private void directToPage(Page page, String type) {
        WindowManager.getManager().setLoginVisible(false);
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
}
