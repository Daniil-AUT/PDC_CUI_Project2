package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * // --CHAT-GPT HAS HELPED WITH CREATING POP UP MESSAGE FOR EACH OPERATION--
 * UserAccountController class is responsible for handling user interactions and
 * events in the User Account View. The class is connects the model and view
 * while controlling navigation to other views (Mainly Ticket Operations).
 *
 * @author Daniil
 */
public class UserAccountController {

    // Reference the associated view and model. 
    private final UserAccountView userView;
    private final UserAccountModel userModel;

    // ENUM responsible for indicating differnet ticket operations.
    public enum Ticket {
        UPDATE,
        CREATE,
        DELETE,
        VIEW
    }

    /*
    Constructor for initialising UserAccountController.
    Initialises the controller with references to the model and view.
    Set up initial navigation to the Ticket Screen and Home Screen.
     */
    public UserAccountController(UserAccountView userView,
            UserAccountModel userModel) {
        this.userView = userView;
        this.userModel = userModel;

        // Set up navigation to Home Screen
        toTicketScreen();

        // Set up navigation to Ticket Screen with associated operation
        toHomeScreen();
    }

    // Set up event handler for navigating back to Home Screen
    private void toHomeScreen() {
        userView.backButton.addActionListener((ActionEvent e) -> {

            // Show  Home Screen and hide User Account Screen
            WindowManager.getManager().setHomeVisible(true);
            WindowManager.getManager().setUserAccountVisible(false);
        });
    }

    // Set up navigation with operations related to Ticket Screen.
    private void toTicketScreen() {

        // Set up actions for viewing, creating, updating, and deleting tickets
        viewTicket();
        createTicket();
        updateTicket();
        deleteTicket();
    }

    /**
     * Set up the event for viewing ticket. Navigate to ticket view and hide
     * UserAccountVIew.
     */
    private void viewTicket() {
        userView.viewButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setTicketVisible(true, "View");
            WindowManager.getManager().setUserAccountVisible(false);
        });

    }

    /**
     * Set up the event for creating ticket. Navigate to ticket page with
     * "Create" state.
     */
    private void createTicket() {
        userView.createButton.addActionListener((ActionEvent e) -> {

            // Show ticket page for create and hide the UserAccountView
            WindowManager.getManager().setTicketVisible(true, "Create");
            WindowManager.getManager().setUserAccountVisible(false);
        });
    }

    /**
     * Set up event for updating ticket. Navigate to ticket page with "Update"
     * state.
     */
    private void updateTicket() {
        userView.editButton.addActionListener((ActionEvent e) -> {

            // Show Ticket page for update and hide the UserAccountView
            WindowManager.getManager().setTicketVisible(true, "Update");
            WindowManager.getManager().setUserAccountVisible(false);
        });
    }

    /**
     * Set up event for deleting user ticket. Delete the ticket through model
     * and display notification.
     */
    private void deleteTicket() {
        userView.deleteButton.addActionListener((ActionEvent e) -> {

            // Delete ticket through model
            userModel.deleteTicket();

            // Show User Account Screen and display a pop-up delete notification
            WindowManager.getManager().setUserAccountVisible(true);
            JOptionPane.showMessageDialog(null, "Ticket Has Been Deleted.");
        });
    }
}
