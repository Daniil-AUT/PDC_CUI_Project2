package pdc_cui_project2;

import java.awt.*;
import java.util.ArrayList;
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
    private TicketView ticketView;
    private SignUpModel signupModel;
    private SignUpController signupController;
    private LoginController loginController;
    private LoginModel loginModel;
    private UserAccountModel userModel;
    private UserAccountController userController;
    private TicketController ticketController;
    private TicketModel ticketModel;
    private DataBaseHandler db;
    // Apply singleton pattern
    public static synchronized WindowManager getManager() {
        if (manager == null) {
            manager = new WindowManager();
            
        }
        return manager;
    }
    
    private WindowManager() {
        this.db = DataBaseHandler.getDB();
        createPanels();
        createModels();
        createControllers();
        createMainFrame();
    }
    
    private void createMainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(718, 540);
        setTitle("AUT Helpdesk");
        setLayout(new CardLayout());
        setVisible(true);
    }
    
    private void createModels() {
        signupModel = new SignUpModel();
        loginModel = new LoginModel();
        userModel = new UserAccountModel();
        ticketModel = new TicketModel();
    } 
    private void createControllers() {
        signupController = new SignUpController(signupView, signupModel);
        loginController = new LoginController(loginView, loginModel);
        userController = new UserAccountController(userAccountView, userModel);
        ticketController = new TicketController(ticketView, ticketModel);
    }
    private void createPanels() {
        ArrayList<JPanel> views = new ArrayList<>();
        
        homeView = new HomeView();
        views.add(homeView);
        faqView = new FaqView();
        views.add(faqView);
        loginView = new LoginView();
        views.add(loginView);
        signupView = new SignUpView();
        views.add(signupView);
        userAccountView = new UserAccountView();
        views.add(userAccountView);
        assistantAccountView = new AssistantAccountView();
        views.add(assistantAccountView);
        ticketView = new TicketView();
        views.add(ticketView);
        
        for(JPanel view : views) {
            if(view instanceof HomeView) {
                addPanel(view, true);
            }
            else {
                addPanel(view, false);
            }
        }
    }
    private void addPanel(JPanel panel, boolean isVisible) {
        if (panel != null) {
            panel.setVisible(isVisible);
            add(panel);
        }
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
        
        boolean hasTicket = db.hasTicket;
        userAccountView.deleteButton.setEnabled(hasTicket);
        userAccountView.greetLabel.setText("Welcome, "+ db.currentName);
        userAccountView.editButton.setEnabled(hasTicket);
        userAccountView.viewButton.setEnabled(hasTicket);
        userAccountView.createButton.setEnabled(!hasTicket);
        userAccountView.setVisible(visible);
    }
    public void setAssistantAccountVisible(boolean visible) {
        assistantAccountView.setVisible(visible);
    }
    
    public void setTicketVisible(boolean visible, String window) {
        switch (window) {
            case "asReply":
                ticketView.showAsReplyWindow();
                break;
            case "asView":
                ticketView.showAsViewWindow();
                break;
            case "Create":
                ticketView.showCreateWindow();
                break;
            case "View":
                ticketView.showViewWindow();
                ticketView.viewText.setText(db.userDetails+"\n\n"+db.viewTicket());
                break;
            case "Update":
                ticketView.showUpdateWindow();
                ticketView.updateText.setText(db.viewTicket());
                break;
            default:
                break;
        }
        ticketView.setVisible(visible);
    }
}

