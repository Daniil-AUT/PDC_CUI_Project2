package pdc_cui_project2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

/**
 * WindowManager class manages the different views, models and controllers.
 * It uses the Mediator pattern to build communication between components and 
 * follows the Singleton pattern to ensure only one instance exists so no extra
 * classes are built.
 * @author Daniil
 */

public class WindowManager extends JFrame {
    
    // Create a single instance of a manager class
    private static WindowManager manager;
    
    // Views for the Help Desk App
    protected HomeView homeView;
    protected FaqView faqView;
    protected LoginView loginView;
    protected SignUpView signupView;
    protected UserAccountView userAccountView;
    protected AssistantAccountView assistantView;
    protected TicketView ticketView;
    
    // Models for the Help Desk App
    private SignUpModel signupModel;
    private LoginModel loginModel;
    private UserAccountModel userModel;
    private TicketModel ticketModel;
    
    // Controllers for the Help Desk App
    private SignUpController signupController;
    private UserAccountController userController;
    private TicketController ticketController;
    private AssistantAccountController assistantController;
    private LoginController loginController;
    private HomeFaqController homeFaqController;
    
    // Reference the DataBaseHandler class (also singleton)
    private final DataBaseHandler db;
    
    // Create Constants for different ticket operation navigation
    private static final String AS_VIEW = "AsView"; 
    private static final String REPLY = "Reply"; 
    private static final String CREATE = "Create"; 
    private static final String UPDATE = "Update"; 
    private static final String VIEW = "View"; 

    // Apply singleton pattern
    public static synchronized WindowManager getManager() {
        if (manager == null) {
            manager = new WindowManager();

        }
        return manager;
    }
    
    /*
    Instatiate Window Manager Constructor which is set to private
    Include the main frame, all the controllers, models, and views (Panels)
    Reference the Database class
    */
    private WindowManager() {
        this.db = DataBaseHandler.getDB();
        createPanels();
        createModels();
        createControllers();
        createMainFrame();
    }
    
    //Instantiate the main frame of the Help Desk App.
    private void createMainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(718, 540);
        setTitle("AUT Helpdesk");
        setLayout(new CardLayout());
        setVisible(true);
    }
    
    // Create instances of models used for Help Desk.
    private void createModels() {
        signupModel = new SignUpModel();
        loginModel = new LoginModel();
        userModel = new UserAccountModel();
        ticketModel = new TicketModel();
    }
    
    // Creates instances of all controllers in Help Desk.
    private void createControllers() {
        signupController = new SignUpController(signupView, signupModel);
        loginController = new LoginController(loginView, loginModel);
        userController = new UserAccountController(userAccountView, userModel);
        ticketController = new TicketController(ticketView, ticketModel);
        assistantController = new AssistantAccountController(assistantView);
        homeFaqController = new HomeFaqController(homeView, faqView);
    }
    
    // Creates instances of panels (views) used in Help Desk.
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
        assistantView = new AssistantAccountView();
        views.add(assistantView);
        ticketView = new TicketView();
        views.add(ticketView);
        
        // Add panels to the main frame
        for (JPanel view : views) {
            if (view instanceof HomeView) {
                addPanel(view, true);
            } else {
                addPanel(view, false);
            }
        }
    }
    
    // Adds a panel to the main frame while also setting visibility.
    private void addPanel(JPanel panel, boolean isVisible) {
        if (panel != null) {
            panel.setVisible(isVisible);
            add(panel);
        }
    }
    
    // Sets visibility of HomeView.
    public void setHomeVisible(boolean visible) {
        homeView.setVisible(visible);
    }

    // Sets visibility of FAQView.
    public void setFAQVisible(boolean visible) {
        faqView.setVisible(visible);
    }

    // Sets visibility of LogInView.
    public void setLoginVisible(boolean visible) {
        loginView.setVisible(visible);
    }

    // Sets visibility of SignUpView.
    public void setSignUpVisible(boolean visible) {
        signupView.setVisible(visible);
    }

    // Sets visibility of UserAccountView.
    public void setUserAccountVisible(boolean visible) {
        
        // Set up UserAccountView based on database information
        boolean hasTicket = db.hasTicket();
        userAccountView.deleteButton.setEnabled(hasTicket);
        userAccountView.greetLabel.setText("Welcome, " + db.getUserName());
        userAccountView.editButton.setEnabled(hasTicket);
        userAccountView.viewButton.setEnabled(hasTicket);
        userAccountView.createButton.setEnabled(!hasTicket);
        userAccountView.setVisible(visible);
    }
    
    // Sets visibility of AssistantAccountView.
    public void setAssistantAccountVisible(boolean visible) {
        
        // Set up AssistantAccountView based on database information.
        assistantView.greetLabel.setText("Welcome, " + db.getUserName());
        assistantView.setVisible(visible);
    }
    
    // Set up visibility for TicketView based on the window state.
    public void setTicketVisible(boolean visible, String window) {
        switch (window) {
            case REPLY:
                
                // Show TicketView through reply window
                ticketView.showAsReplyWindow();
                ticketView.idField.setText("");
                ticketView.replyText.setText("");
                break;
            case AS_VIEW:
                
                // --CHAT-GPT assisted method--
                // Show TicketView through view window while displaying user tickets
                ticketView.showAsViewWindow();
                for (Map.Entry<String, String> entry : db.getUserTickets().entrySet()) {
                    String id = entry.getKey();
                    String description = entry.getValue().split("Full Name:")[0];
                    ticketView.modelTable.addRow(new Object[]{id, description});
                }
                break;
            case CREATE:
                
                // Show TicketView through create window
                ticketView.showCreateWindow();
                break;
            case VIEW:
                
                /*
                Show TicketView through view window while 
                displaying details of a specific ticket
                */
                ticketView.showViewWindow();
                ticketView.viewText.setText(db.getUserDetails() + "\n\n" + db.viewTicket());
                break;
            case UPDATE:
                
                // Show TicketView through update window
                ticketView.showUpdateWindow();
                ticketView.updateText.setText(db.viewTicket());
                break;
            default:
                
                // Handle unexpected input (error)
                break;
        }
        ticketView.setVisible(visible);
    }
}
