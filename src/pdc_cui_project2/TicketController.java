package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
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
        asViewTicket();
        replyTicket();
    }
    
    private void createTicket() {
        view.createButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Created ticket...");
                if(ticketTextValid(view.createText)) {
                    JOptionPane.showMessageDialog(null,"Ticket Has Been Created.");
                    model.createTicket(view.createText.getText());
                    view.createText.setText("");
                    WindowManager.getManager().setUserAccountVisible(true);
                    WindowManager.getManager().setTicketVisible(false, "");
                }
            }
        });
    }
    
    private boolean ticketTextValid(JTextArea textArea) {
        String text = textArea.getText();
        if(text.isEmpty()) {
            textArea.setBorder(new LineBorder(Color.red, 2));
            return false;
        }
        else {
            textArea.setBorder(new LineBorder(Color.black, 1));
            return true;
        }
    }
    
    private void updateTicket() {
        view.updateButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(ticketTextValid(view.updateText)) {
                    JOptionPane.showMessageDialog(null,"Ticket Has Been Updated.");
                    model.db.updateTicket(view.updateText.getText());
                    WindowManager.getManager().setUserAccountVisible(true);
                    WindowManager.getManager().setTicketVisible(false, "");
                }     
            }
        });
    }
    private void asViewTicket() {
        view.asViewButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Will add onto the ticket");
            }
        }
        );
    }
    
    private boolean checkTicketIDExist(String id) {
        
        return model.db.checkTicketExists(id);
    }
    
    private void replyTicket() {
        view.replyButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = view.idField.getText();
                String replyText = view.replyText.getText();
                if(ticketTextValid(view.replyText) && checkTicketIDExist(id)) {
                    JOptionPane.showMessageDialog(null,"Ticket Has Been Replied.");
                    model.db.replyToTicket(id, replyText);
                }
            }
        }
        );
    }
}
