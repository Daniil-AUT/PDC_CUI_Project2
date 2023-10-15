package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniil
 */
public class UserAccountController {

    UserAccountView userView;
    UserAccountModel userModel;

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
        userView.backButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
    }

    private void toTicketScreen() {
        viewTicket();
        createTicket();
        updateTicket();
        deleteTicket();
    }

    private void viewTicket() {
        userView.viewButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "View");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });

    }

    private void createTicket() {
        userView.createButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "Create");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
    }

    private void updateTicket() {
        userView.editButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "Update");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
    }

    private void deleteTicket() {
        userView.deleteButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userModel.db.deleteRecordTicket();
                WindowManager.getManager().setUserAccountVisible(true);
                JOptionPane.showMessageDialog(null, "Ticket Has Been Deleted.");
            }
        });

    }
}
