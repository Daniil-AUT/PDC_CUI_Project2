package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
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
    private static final Color ERROR_COLOUR = Color.red;
    private static final Color DEFAULT_COLOUR = Color.black;
    private static final String VIEW_UPDATE = "Update";
    private static final String VIEW_CREATE = "Create";
    private static final String VIEW_VIEW = "View";
    
    public TicketController(TicketView view, TicketModel model) {
        this.view = view;
        this.model = model;
        toAccountScreen();
        ticketOperations();
    }

    private void toAccountScreen() {
        view.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(false, "");
                if(view.currentView.equals(VIEW_UPDATE) || 
                            view.currentView.equals(VIEW_CREATE) || 
                            view.currentView.equals(VIEW_VIEW)) {
                            WindowManager.getManager().setUserAccountVisible(true);
                }
                else {
                     WindowManager.getManager().setAssistantAccountVisible(true);
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
                if (ticketTextValid(view.createText, view.createLabel)) {
                    JOptionPane.showMessageDialog(null, "Ticket Has Been Created.");
                    model.createTicket(view.createText.getText());
                    view.createText.setText("");
                    WindowManager.getManager().setUserAccountVisible(true);
                    WindowManager.getManager().setTicketVisible(false, "");
                }
            }
        });
    }

    private boolean ticketTextValid(JTextArea textArea, JLabel textLabel) {
        String text = textArea.getText();
        if (text.isEmpty()) {
            textLabel.setForeground(ERROR_COLOUR);
            textArea.setBorder(new LineBorder(ERROR_COLOUR, 2));
            return false;
        } else {
            textLabel.setForeground(DEFAULT_COLOUR);
            textArea.setBorder(new LineBorder(DEFAULT_COLOUR, 1));
            return true;
        }
    }

    private void updateTicket() {
        view.updateButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ticketTextValid(view.updateText, view.updateLabel)) {
                    JOptionPane.showMessageDialog(null, "Ticket Has Been Updated.");
                    model.updateTicket(view.updateText.getText());
                    WindowManager.getManager().setUserAccountVisible(true);
                    WindowManager.getManager().setTicketVisible(false, "");
                }
            }
        });
    }

    private void asViewTicket() {
        view.backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.modelTable.setRowCount(0); // This clears the table
            }
        });
    }

    private void replyTicket() {
        view.replyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = view.idField.getText();
                String reply = view.replyText.getText();
                String assistantReply = view.replyText.getText();
                String userID = view.idField.getText().toUpperCase();

                boolean ticketExists = model.checkTicketExists(id.toUpperCase());

                handleAssistantReply(assistantReply);
                handleUserID(userID, ticketExists);

                if (isFormValid(view.replyText, view.replyLabel) && ticketExists) {
                    JOptionPane.showMessageDialog(null, "Reply Was Sent.");
                    model.replyToTicket(id, reply);
                    view.replyText.setText("");
                    view.idField.setText("");
                    WindowManager.getManager().setAssistantAccountVisible(true);
                    WindowManager.getManager().setTicketVisible(false, "");
                }
            }
        });
    }

    private void handleAssistantReply(String assistantReply) {
        if (assistantReply.isEmpty()) {
            view.replyLabel.setText("Reply To User Ticket (No Blanks)");
        } else {
            view.replyLabel.setText("Reply To User Ticket");
        }
    }

    private void handleUserID(String userID, boolean ticketExists) {
        if (userID.isEmpty()) {
            view.idLabel.setText("Enter User ID (No Blanks)");
            view.idLabel.setForeground(ERROR_COLOUR);  
        } else if (!ticketExists) {
            view.idLabel.setText("Enter User ID (ID Not Found)");
            view.idLabel.setForeground(ERROR_COLOUR); 
        } else {
            view.idLabel.setText("Enter User ID");
            view.idLabel.setForeground(DEFAULT_COLOUR); 
        }
    }


    private boolean isFormValid(JTextArea textArea, JLabel textLabel) {
        String text = textArea.getText();
        if (text.isEmpty()) {
            textLabel.setForeground(ERROR_COLOUR);
            textArea.setBorder(new LineBorder(ERROR_COLOUR, 2));
            return false;
        } else {
            textLabel.setForeground(DEFAULT_COLOUR);
            textArea.setBorder(new LineBorder(DEFAULT_COLOUR, 1));
            return true;
        }
    }


}
