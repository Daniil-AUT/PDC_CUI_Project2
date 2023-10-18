package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Daniil
 */
public class AssistantAccountController {
    private final AssistantAccountView view;
    
    public AssistantAccountController(AssistantAccountView view) {
        this.view = view;
        toTicketScreen();
    }
    
    private void toTicketScreen() {
        toHomeScreen();
        replyTicket();
        viewAllTickets();
    }
    
    private void toHomeScreen() {
        view.backButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setAssistantAccountVisible(false);
            }
        });
    }
    
    private void replyTicket() {
        view.assistantReply.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "Reply");
                WindowManager.getManager().setAssistantAccountVisible(false);
            }
        });
    }
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
