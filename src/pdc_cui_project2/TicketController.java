package pdc_cui_project2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
                switch (view.currentView) {
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
            textLabel.setForeground(Color.red);
            textArea.setBorder(new LineBorder(Color.red, 2));
            return false;
        } else {
            textLabel.setForeground(Color.black);
            textArea.setBorder(new LineBorder(Color.black, 1));
            return true;
        }
    }

    private boolean ticketFieldValid(JTextField textField, JLabel fieldLabel) {
        String idEntry = textField.getText();
        String labelInfo = fieldLabel.getText();

        if (idEntry.isEmpty()) {
            fieldLabel.setForeground(Color.red);
            textField.setBorder(new LineBorder(Color.red, 2));
            return false;
        }

        if (!checkTicketIDExist(idEntry)) {
            fieldLabel.setForeground(Color.red);
            textField.setBorder(new LineBorder(Color.red, 2));
            return false;
        }
        fieldLabel.setText(labelInfo); // Reset the label text to its original state
        fieldLabel.setForeground(Color.black);
        textField.setBorder(new LineBorder(Color.black, 1));
        return true;
    }

    private void updateTicket() {
        view.updateButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ticketTextValid(view.updateText, view.updateLabel)) {
                    JOptionPane.showMessageDialog(null, "Ticket Has Been Updated.");
                    model.db.updateTicket(view.updateText.getText());
                    WindowManager.getManager().setUserAccountVisible(true);
                    WindowManager.getManager().setTicketVisible(false, "");
                }
            }
        });
    }

    private void asViewTicket() {
        view.backButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.modelTable.setRowCount(0);
            }
        });

    }

    //new LineBorder(Color.BLACK, 1)
    private boolean checkTicketIDExist(String id) {
        return model.db.checkTicketExists(id);
    }

    private void replyTicket() {
        view.replyButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = view.idField.getText();
                String reply = view.replyText.getText();
                    
                    
                if(ticketTextValid(view.replyText, view.replyLabel) ==
                   ticketFieldValid(view.idField, view.idLabel)) {
                    if(view.replyText.getText().isEmpty()) {
                        view.replyLabel.setText("Reply To User Ticket (No Blanks)");
                    }
                    if(view.idField.getText().isBlank()) {
                        view.idLabel.setText("Enter User ID (No Blanks)");
                    } 
                    else if(!checkTicketIDExist(view.idField.getText())) {
                        view.idLabel.setText("Enter User ID (ID Not Found)");
                    }
                    
                   if(ticketTextValid(view.replyText, view.replyLabel) &&
                   ticketFieldValid(view.idField, view.idLabel)) {
                       JOptionPane.showMessageDialog(null, "Reply Was Sent.");
                       model.db.replyToTicket(id, reply);
                       WindowManager.getManager().setAssistantAccountVisible(true);
                       WindowManager.getManager().setTicketVisible(false, "");
                   }
                }
            }
        }
        );
    }
}
