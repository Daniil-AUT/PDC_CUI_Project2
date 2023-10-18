package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniil
 */
public class UserAccountController {

    private final UserAccountView userView;
    private final UserAccountModel userModel;

    public enum Ticket {
        UPDATE,
        CREATE,
        DELETE,
        VIEW
    }

    public UserAccountController(UserAccountView userView,
            UserAccountModel userModel) {
        this.userView = userView;
        this.userModel = userModel;
        toTicketScreen();
        toHomeScreen();
    }

    private void toHomeScreen() {
        userView.backButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setHomeVisible(true);
            WindowManager.getManager().setUserAccountVisible(false);
        });
    }

    private void toTicketScreen() {
        viewTicket();
        createTicket();
        updateTicket();
        deleteTicket();
    }

    private void viewTicket() {
        userView.viewButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setTicketVisible(true, "View");
            WindowManager.getManager().setUserAccountVisible(false);
        });

    }

    private void createTicket() {
        userView.createButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setTicketVisible(true, "Create");
            WindowManager.getManager().setUserAccountVisible(false);
        });
    }

    private void updateTicket() {
        userView.editButton.addActionListener((ActionEvent e) -> {
            WindowManager.getManager().setTicketVisible(true, "Update");
            WindowManager.getManager().setUserAccountVisible(false);
        });
    }

    private void deleteTicket() {
        userView.deleteButton.addActionListener((ActionEvent e) -> {
            userModel.deleteTicket();
            WindowManager.getManager().setUserAccountVisible(true);
            JOptionPane.showMessageDialog(null, "Ticket Has Been Deleted.");
        });

    }
}
