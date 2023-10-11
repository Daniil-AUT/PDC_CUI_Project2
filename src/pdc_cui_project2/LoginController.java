
package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Daniil
 */
public class LoginController {
    private LoginView view;
    private LoginModel model;
    private User user;
    private DataBaseHandler db;
    
    public LoginController(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
        this.db = DataBaseHandler.getDB();
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
                    System.out.println("Home button has been pressed");
                    WindowManager.getManager().setHomeVisible(true);
                    WindowManager.getManager().setSignUpVisible(false);
                }
            });
    }
    
    public void toAccountScreen() {
        System.out.println("Setting up Signup button listener");  
        view.loginButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Clicked on Login");
                }
            });
    }
    
}
