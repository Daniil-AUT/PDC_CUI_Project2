package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 *
 * @author Daniil
 */
public class AssistantAccountController {
    AssistantAccountView view;
    AssistantAccountModel model;
    
    public AssistantAccountController(AssistantAccountView view, 
                                      AssistantAccountModel model) {
        this.view = view;
        this.model = model;
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
