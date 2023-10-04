package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Daniil
 */
public class WindowManager extends JFrame {
    private static WindowManager manager;
    private HomeView homeView;
    private FaqView faqView;
    private LoginView loginView;
    private SignUpView signupView;
    private UserAccountView userAccountView;
    private AssistantAccountView assistantAccountView;
    
    // Apply singleton pattern
    public static synchronized WindowManager getManager() {
        if (manager == null) {
            manager = new WindowManager();
        }
        return manager;
    }
    
    private WindowManager() {
        
        createPanels();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(718, 540);
        setTitle("AUT Helpdesk");
        setLayout(new CardLayout());
        setVisible(true);
    }
    private void createPanels() {
        homeView = new HomeView();
        homeView.setVisible(true);
        
        faqView = new FaqView();
        faqView.setVisible(false);
        
        loginView = new LoginView();
        loginView.setVisible(false);
        
        signupView = new SignUpView();
        signupView.setVisible(false);
        
        userAccountView = new UserAccountView();
        userAccountView.setVisible(false);
        
        assistantAccountView = new AssistantAccountView();
        assistantAccountView.setVisible(false);
        
        add(homeView);
        add(faqView);
        add(loginView);
        add(signupView);
        add(userAccountView);
        add(assistantAccountView);
    }

    public void setHomeVisible(boolean visible) {
        homeView.setVisible(visible);
    }

    public void setFAQVisible(boolean visible) {
        faqView.setVisible(visible);
    }
    public void setLoginVisible(boolean visible) {
        loginView.setVisible(visible);
    }
    public void setSignUpVisible(boolean visible) {
        signupView.setVisible(visible);
    }
    public void setUserAccountVisible(boolean visible) {
        userAccountView.setVisible(visible);
    }
    public void setAssistantAccountVisible(boolean visible) {
        assistantAccountView.setVisible(visible);
    }
}

