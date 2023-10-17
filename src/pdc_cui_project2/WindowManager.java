package pdc_cui_project2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
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
    private AssistantAccountView assistantView;
    private TicketView ticketView;

    private SignUpModel signupModel;
    private LoginModel loginModel;
    private UserAccountModel userModel;
    private TicketModel ticketModel;

    private SignUpController signupController;
    private UserAccountController userController;
    private TicketController ticketController;
    private AssistantAccountController assistantController;
    private LoginController loginController;
    private HomeFaqController homeFaqController;

    private final DataBaseHandler db;
    
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
        assistantController = new AssistantAccountController(assistantView);
        homeFaqController = new HomeFaqController(homeView, faqView);
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
        assistantView = new AssistantAccountView();
        views.add(assistantView);
        ticketView = new TicketView();
        views.add(ticketView);

        for (JPanel view : views) {
            if (view instanceof HomeView) {
                addPanel(view, true);
            } else {
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

        boolean hasTicket = db.hasTicket();
        userAccountView.deleteButton.setEnabled(hasTicket);
        userAccountView.greetLabel.setText("Welcome, " + db.getUserName());
        userAccountView.editButton.setEnabled(hasTicket);
        userAccountView.viewButton.setEnabled(hasTicket);
        userAccountView.createButton.setEnabled(!hasTicket);
        userAccountView.setVisible(visible);
    }

    public void setAssistantAccountVisible(boolean visible) {
        assistantView.greetLabel.setText("Welcome, " + db.getUserName());
        assistantView.setVisible(visible);
    }

    public void setAssistantReply(String[] replyText) {

    }

    public void setTicketVisible(boolean visible, String window) {
        switch (window) {
            case REPLY:
                ticketView.showAsReplyWindow();
                break;
            case AS_VIEW:
                ticketView.showAsViewWindow();
                for (Map.Entry<String, String> entry : db.getUserTickets().entrySet()) {
                    String id = entry.getKey();
                    String description = entry.getValue().split("Full Name:")[0];
                    ticketView.modelTable.addRow(new Object[]{id, description});
                }
                break;
            case CREATE:
                ticketView.showCreateWindow();
                break;
            case VIEW:
                ticketView.showViewWindow();
                ticketView.viewText.setText(db.getUserDetails() + "\n\n" + db.viewTicket());
                break;
            case UPDATE:
                ticketView.showUpdateWindow();
                ticketView.updateText.setText(db.viewTicket());
                break;
            default:
                break;
        }
        ticketView.setVisible(visible);
    }
}
