package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Controller class is managing interactions between the HomeView and FaqView.
 * Create button events to navigate between FAQ and Home Screen. Reads FAQ
 * content from a text file and updates the FaqView accordingly.
 *
 * @author Daniil
 */
public final class HomeFaqController {

    // Reference to the HomeView and FaqView
    private final HomeView homeView;
    private final FaqView faqView;

    // File path for the FAQ text file
    private final static String PATH = "HelpDeskFiles\\faq.txt";

    public HomeFaqController(HomeView homeView, FaqView faqView) {
        this.homeView = homeView;
        this.faqView = faqView;
        showInfo();
        compileOperations();
    }
    
    // compiles all navigation events into one method
    public void compileOperations() {
        toLogInScreen();
        toSignUpScreen();
        toFAQScreen();
        toHomeScreen();
    }
    
    // sets up the action for navigating to the login screen.
    public void toLogInScreen() {
        homeView.loginButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setHomeVisible(false);
            WindowManager.getManager().setLoginVisible(true);
        });
    }
    // set up the action for navigating to the SignUp screen.
    public void toSignUpScreen() {
        homeView.signupButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setSignUpVisible(true);
            WindowManager.getManager().setHomeVisible(false);
        });
    }

    // set up the action event for navigating to the FAQ screen from Home
    public void toFAQScreen() {
        homeView.faqButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setHomeVisible(false);
            WindowManager.getManager().setFAQVisible(true);
        });
    }

    // set up the action event for navigating to the Home screen from FAQ
    public void toHomeScreen() {
        faqView.backButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setFAQVisible(false);
            WindowManager.getManager().setHomeVisible(true);
        });
    }

    /**
     * Reads FAQ content from a text file and displays in TextArea of FAQ page.
     * Effectively handles exceptions for any IO errors.
     */
    private void showInfo() {
        String text = "";
        try ( BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }
            faqView.faqInfo.setText("\n\n" + text);
            faqView.faqInfo.setSize(60, 50);
            reader.close();
            
            /*
            Give possible exceptions errors and handle them through showing
            them in the text area
            */
        } catch (FileNotFoundException e) {
            faqView.faqInfo.setText("Sorry, FAQ is unavailable at the moment.");
        } catch (IOException e) {
            faqView.faqInfo.setText("Apologies, An Error Occured While Loading FAQ");
        }
    }
}
