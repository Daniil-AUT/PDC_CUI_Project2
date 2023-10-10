package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Daniil
 */
public class SignUpController {
    private SignUpView view;
    private SignUpModel model;
    private User user;
    
    public SignUpController(SignUpView view, SignUpModel model) {
        this.view = view;
        this.model = model;
        attachListeners();
    }
    private void attachListeners() {
        System.out.println("Attaching listeners");  // Print to confirm that listeners are being attached
        toHomeScreen();
        toAccountScreen();
    }
    public void toHomeScreen() {
        System.out.println("Setting up Home button listener");  // Print to confirm that the Home button listener is being set up
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
        System.out.println("Setting up Signup button listener");  // Print to confirm that the Signup button listener is being set up
        view.signupButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Signup button has been pressed");
                    WindowManager.getManager().setHomeVisible(true);
                    WindowManager.getManager().setUserAccountVisible(false);
                }
            });
    }
    
}
