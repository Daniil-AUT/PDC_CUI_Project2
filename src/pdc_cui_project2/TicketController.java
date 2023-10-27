package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * // --CHAT-GPT HELPED CREATE POP UP MESSAGES FOR THIS CLASS--
 * TicketController handles interaction between view and model for ticket class.
 *
 * @author Daniil
 */
public class TicketController {

    // Reference the appropriat view and model for ticket class
    private final TicketView view;
    private final TicketModel model;

    // Constants to indicate whether there was an error and which operation to do.
    private static final Color ERROR_COLOUR = Color.red;
    private static final Color DEFAULT_COLOUR = Color.black;
    private static final String VIEW_UPDATE = "Update";
    private static final String VIEW_CREATE = "Create";
    private static final String VIEW_VIEW = "View";

    // Constructor initialises view and model for ticket class
    public TicketController(TicketView view, TicketModel model) {
        this.view = view;
        this.model = model;
        toAccountScreen();
        ticketOperations();
    }

    // Navigate back to user or assistant account screen based on current view.
    private void toAccountScreen() {
        view.backButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setTicketVisible(false, "");
            if (view.currentView.equals(VIEW_UPDATE)
                    || view.currentView.equals(VIEW_CREATE)
                    || view.currentView.equals(VIEW_VIEW)) {
                WindowManager.getManager().setUserAccountVisible(true);
            } else {
                WindowManager.getManager().setAssistantAccountVisible(true);
            }
        });
    }

    // Set up action listeners for ticket operations (create, update, view, reply).
    private void ticketOperations() {
        createTicket();
        updateTicket();
        asViewTicket();
        replyTicket();
    }

    // Handle ticket operation for ticket creating
    private void createTicket() {
        view.createButton.addActionListener((ActionEvent e) -> {
            System.out.println("Created ticket...");
            if (ticketTextValid(view.createText, view.createLabel)) {
                JOptionPane.showMessageDialog(null, "Ticket Has Been Created.");
                model.createTicket(view.createText.getText());
                view.createText.setText("");
                WindowManager.getManager().setUserAccountVisible(true);
                WindowManager.getManager().setTicketVisible(false, "");
            }
        });
    }

    // Validate text for ticket text area.
    private boolean ticketTextValid(JTextArea textArea, JLabel textLabel) {
        String text = textArea.getText();
        if (text.isBlank() || text.isEmpty()) {
            textLabel.setForeground(ERROR_COLOUR);
            textArea.setBorder(new LineBorder(ERROR_COLOUR, 2));
            return false;
        } else {
            textLabel.setForeground(DEFAULT_COLOUR);
            textArea.setBorder(new LineBorder(DEFAULT_COLOUR, 1));
            return true;
        }
    }
    
    // Handle ticket update for existing ticket.
    private void updateTicket() {
        view.updateButton.addActionListener((ActionEvent e) -> {
            if (ticketTextValid(view.updateText, view.updateLabel)) {
                JOptionPane.showMessageDialog(null, "Ticket Has Been Edited.");
                model.updateTicket(view.updateText.getText());
                WindowManager.getManager().setUserAccountVisible(true);
                WindowManager.getManager().setTicketVisible(false, "");
            }
        });
    }
    
    // Clear ticket table when navigating from assistant ticket page.
    private void asViewTicket() {
        view.backButton.addActionListener((ActionEvent e) -> {
            view.modelTable.setRowCount(0); 
        });
    }

    // Handles reply ticket interaction
    private void replyTicket() {
        view.replyButton.addActionListener((ActionEvent e) -> {
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
        });
    }
    
    // Update reply label based on the input text for assistant's reply.
    private void handleAssistantReply(String assistantReply) {
        if (assistantReply.isBlank() || assistantReply.isEmpty()) {
            view.replyLabel.setText("Reply To User Ticket (No Blanks)");
        } else {
            view.replyLabel.setText("Reply To User Ticket");
        }
    }
    
    //  Handle user ID and update id label based on its input (error or not).
    private void handleUserID(String userID, boolean ticketExists) {
        if (userID.isBlank() || userID.isEmpty()) {
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
    
    // Validate text in a text area fields.
    private boolean isFormValid(JTextArea textArea, JLabel textLabel) {
        String text = textArea.getText();
        if (text.isBlank() || text.isEmpty()) {
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
