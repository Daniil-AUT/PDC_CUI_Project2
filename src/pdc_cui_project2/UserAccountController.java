
package pdc_cui_project2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        
    }

    private void toHomeScreen() {

    }
    private void toTicketScreen() {

    }

    public void createEvents() {
        userView.backButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });

        userView.createButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "Create");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
        userView.editButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "Update");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
        userView.viewButton.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowManager.getManager().setTicketVisible(true, "View");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });

    }
}
