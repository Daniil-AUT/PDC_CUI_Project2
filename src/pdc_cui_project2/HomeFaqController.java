
package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Daniil
 */
public final class HomeFaqController {
    private final HomeView homeView;
    private final FaqView faqView;
    
    private final static String PATH = "HelpDeskFiles\\faq.txt";
    
    public HomeFaqController(HomeView homeView, FaqView faqView) {
        this.homeView = homeView;
        this.faqView = faqView;
        showInfo();
        compileOperations();
    }
    public void compileOperations() {
        toLogInScreen();
        toSignUpScreen();
        toFAQScreen();
        toHomeScreen();
    }
    
    public void toLogInScreen() {
        homeView.loginButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setHomeVisible(false);
            WindowManager.getManager().setLoginVisible(true);
        }); 
    }
    public void toSignUpScreen() {
        homeView.signupButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setSignUpVisible(true);
            WindowManager.getManager().setHomeVisible(false);
        });
    }
    public void toFAQScreen() {
        homeView.faqButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setHomeVisible(false);
            WindowManager.getManager().setFAQVisible(true);
        });   
    }
    
    public void toHomeScreen() {
        faqView.backButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setFAQVisible(false);
            WindowManager.getManager().setHomeVisible(true);
        });
    }
    
    private void showInfo() {
        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }
            faqView.faqInfo.setText("\n\n"+text);
            faqView.faqInfo.setSize(60, 50);
            reader.close();
            // Give possible exceptions errors and handle them through print statements
        } catch (FileNotFoundException e) {
            faqView.faqInfo.setText("Sorry, FAQ is unavailable at the moment.");
        } catch (IOException e) {
            faqView.faqInfo.setText("Apologies, An Error Occured While Loading FAQ");
        }
    }
}
