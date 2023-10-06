package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 *
 * @author Daniil
 */
public class TicketView extends JPanel implements Page {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    private JLabel updateLabel;
    private JButton updateButton;
    private JLabel replyLabel;
    private JButton replyButton;
    private JTextArea replyText;
    private JButton asViewButton;
    private JLabel viewLabel;
    private JButton viewButton;
    private JButton createButton;
    private JButton deleteButton;
    private JButton backButton;

    public TicketView() {
        createComponents();
        createEvents();
    }

    @Override
    public void createComponents() {
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add("Reply", windowReply());
        cardPanel.add("AsView", windowAsView());
        cardPanel.add("View", windowView());
        cardPanel.add("Create", windowCreate());
        cardPanel.add("Update", windowUpdate());
        cardPanel.add("Delete", windowDelete());

        backButton = new JButton("Home");
        GUIStyle.styleButton(backButton);
        add(backButton, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    public void showAsReplyWindow() {
        cardLayout.show(cardPanel, "Reply");
    }

    public void showAsViewWindow() {
        cardLayout.show(cardPanel, "AsView");
    }
    public JPanel windowReply() {
        JPanel asViewPanel = new JPanel();
        
        // Add components for AsView window
        return asViewPanel;
    }
    public JPanel windowAsView() {
        JPanel asViewPanel = new JPanel();
        asViewButton = new JButton("View All Tickets");
        asViewPanel.add(asViewButton);
        // Add components for AsView window
        return asViewPanel;
    }

    public JPanel windowView() {
        JPanel viewPanel = new JPanel();
        // Add components for View window
        return viewPanel;
    }

    public JPanel windowCreate() {
        JPanel createPanel = new JPanel();
        // Add components for Create window
        return createPanel;
    }

    public JPanel windowUpdate() {
        JPanel updatePanel = new JPanel();
        // Add components for Update window
        return updatePanel;
    }

    public JPanel windowDelete() {
        JPanel deletePanel = new JPanel();
        // Add components for Delete window
        return deletePanel;
    }

    @Override
    public void createEvents() {
        backButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("reply button has been pressed");
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setTicketVisible(false, "");
            }
        });
    }
}
