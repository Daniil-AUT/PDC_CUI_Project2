package pdc_cui_project2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniil
 */
public class LoginControllerTest {

    // Declar MVC of Login class
    LoginView view;
    LoginModel model;
    LoginController controller;

    public LoginControllerTest() {

        // Instatiate the MVC of login class
        view = new LoginView();
        model = new LoginModel();
        controller = new LoginController(view, model);
    }

    /*
    Test functionality of radio buttons for user type through select type method.
    Important for determining whether the selected radio button
    corresponds to the correct user type.
     */
    @Test
    public void testSelectType() {

        // Select the assistant radio button.
        view.assistant.setSelected(true);
        
        // Create string type variable and set it to the select type method.
        String type = controller.selectType();
        
        //Check whether the selected type corresponds to the expected string type.
        assertEquals("Assistant", type);
    }

    /*
    Tests whether the input for id, password, and type match those
    from the database, if yes the test should pass.
    Important for validating the user by checking whether entries
    do exist in the database, and the user type is valid too.
     */
    @Test
    public void testValidateEntries() {

        /*
        Set up fields with existing id, matching password, and
        appropriate user type.
         */
        view.idField.setText("UKDS1234");
        view.passField.setText("password123");
        view.customer.setSelected(true);

        // Create String variables for id and password 
        String id = view.idField.getText();
        String password = new String(view.passField.getPassword());

        // Test Whether user already exists in the database.
        assertTrue("Check User Exists In Database",
                controller.validateEntries(id, password));
    }
}
