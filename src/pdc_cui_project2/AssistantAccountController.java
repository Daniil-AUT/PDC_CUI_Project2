package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AssistantAccountController handle interactions between view and model classes.
 * It creates events for buttons in the Assistant's account interface.
 *
 * @author Daniil
 */
public class AssistantAccountController {
    
    // Reference to appropriate view class
    private final AssistantAccountView view;
    
    /*
    Initialises view class and calls the ticket screen method which handles
    button events for ticket operation.
    */
    public AssistantAccountController(AssistantAccountView view) {
        this.view = view;
        toTicketScreen();
    }
    
    // Set up events for navigating and interacting with tickets.
    private void toTicketScreen() {
        toHomeScreen();
        replyTicket();
        viewAllTickets();
    }
    
    // Navigate back to home screen.
    private void toHomeScreen() {
        view.backButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setAssistantAccountVisible(false);
            }
        });
    }
    
    // Navigate back to ticket reply page.
    private void replyTicket() {
        view.assistantReply.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "Reply");
                WindowManager.getManager().setAssistantAccountVisible(false);
            }
        });
    }
    
    //  Navigate to view all tickets screen.
    private void viewAllTickets() {
        view.assistantView.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "AsView");
                WindowManager.getManager().setAssistantAccountVisible(false);
            }
        });
    }
}
