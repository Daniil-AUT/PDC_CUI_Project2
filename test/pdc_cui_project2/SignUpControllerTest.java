package pdc_cui_project2;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniil
 */
public class SignUpControllerTest {
    
    // Declare MVC variable classes.
    SignUpView view;
    SignUpModel model;
    SignUpController controller;
    
    // Implement MVC for SingUp class for full testing.
    public SignUpControllerTest() {
        // Instantiate MVC variable classes.
        view = new SignUpView();
        model = new SignUpModel();
        controller = new SignUpController(view, model);
    }
    
    /*
    Tests for whether the fields have been validated, if so
    test for whether the entry has been validated (gives no ErrorExceptions)
    Important for knowing that a record is inserted without any errors.
    */
    @Test
    public void testValidateEntry() {

        // Set Up valid text for all the fields.
        view.name.setText("Daniil");
        view.lastName.setText("Volodin");
        view.email.setText("daniilvolodin@mail.com");
        view.password.setText("12345678");
        view.confPassword.setText("12345678");
        view.assistant.setSelected(true);

        // Check that validateFields method returns true when called.
        assertTrue("Check Entry Fields Are Valid ", controller.validateFields());

        // Check that validateEntry method doesn't give exception errors.
        try {
            // If there are no errors, the catch clause will not activate.
            controller.validateEntry();
        } catch (Exception ex) {
            // Will fail if the exception error is thrown.
            fail("Exception Error Is Thrown: " + ex.getMessage());
        }
    }

    
    /*
    Tests whether the fields have been fully reset where there is no text
    Important for usability and aesthetics of sign up page.
    */
   @Test
    public void testResetFields() {
        
        // Set Up valid text for all the fields (should contain text)
        view.name.setText("Daniil");
        view.lastName.setText("Volodin");
        view.email.setText("daniilvolodin@mail.com");
        view.password.setText("12345678");
        view.confPassword.setText("12345678");
        view.assistant.setSelected(true);
        
        // Call resetFields method to clear text for all fields
        controller.resetFields();
        
        // Create String type variable that store the updated field text
        String name = view.name.getText();
        String lastName = view.lastName.getText();
        String email = view.email.getText();
        String password = new String(view.password.getPassword());
        
        // Store the variables in a array list 
        List<String> entryList = Arrays.asList(name, lastName, email, password);
        
        // Iterate through all fields and check whether they're all empty.
        for (String entry : entryList) {
            System.out.println("Entry ("+entry+")");
            assertEquals("Field should be empty after reset", "", entry);
        }
    }
}
