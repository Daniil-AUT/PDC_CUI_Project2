package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author Daniil
 */
public class TicketController {
    TicketView view;
    TicketModel model;
    
    public TicketController(TicketView view, TicketModel model) {
        this.view = view;
        this.model = model;
        toAccountScreen();
        ticketOperations();
    }

    private void toAccountScreen() {
        view.backButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(false, "");
                switch(view.currentView) {
                    case "Update":
                        WindowManager.getManager().setUserAccountVisible(true);
                        break;
                    case "Create":
                        
                        WindowManager.getManager().setUserAccountVisible(true);
                        break;
                    case "View":
                        WindowManager.getManager().setUserAccountVisible(true);
                        break;
                    default:
                        WindowManager.getManager().setAssistantAccountVisible(true);
                        break;
                }
            }
        });
    }
    private void ticketOperations() {
        createTicket();
        updateTicket();
        viewTicket();
        asViewTicket();
        replyTicket();
    }
    
    private void createTicket() {
        view.createButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Created ticket...");
                if(ticketCreateValid()) {
                    JOptionPane.showMessageDialog(null,"Ticket Has Been Created.");
                    model.createTicket(view.createText.getText());
                    view.createText.setText("");
                    WindowManager.getManager().setUserAccountVisible(true);
                    WindowManager.getManager().setTicketVisible(false, "");
                }
            }
        });
    }
    private boolean ticketCreateValid() {
        String text = view.createText.getText();
        if(text.isEmpty()) {
            view.createText.setBorder(new LineBorder(Color.red, 1));
            return false;
        }
        else {
            view.createText.setBorder(new LineBorder(Color.black, 1));
            return true;
        }
    }
    
    private void updateTicket() {
        view.updateButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(checkTicketUpdateValid()) {
                    WindowManager.getManager().setUserAccountVisible(true);
                    WindowManager.getManager().setTicketVisible(false, "");
                }
                
            }
        });
    }
    private boolean checkTicketUpdateValid() {
        boolean isValid = false;
        if(!view.updateText.getText().isBlank()) {
            isValid = true;
        }
        return isValid;
    }
    private void viewTicket() {
        
    }
    private void asViewTicket() {
        
    }
    private void replyTicket() {
        
    }
    
    public void createEvents() {
    }
}
