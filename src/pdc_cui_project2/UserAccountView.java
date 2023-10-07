package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Daniil
 */
public class UserAccountView extends JPanel implements Page {
    private JButton backButton;
    private JButton viewButton;
    private JButton createButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel greetLabel;
    private JTextArea userInstruction;
    
    public UserAccountView() {
        createComponents();
        createEvents();
    }
    
    @Override
    public void createComponents() { 
        setLayout(new BorderLayout());
        backButton = new JButton("Home");
        GUIStyle.styleButton(backButton);
        add(backButton, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel for login label
        JPanel topCenter = new JPanel();
        greetLabel = new JLabel("\nHello, User!\n");
        GUIStyle.styleLabel(greetLabel);
        greetLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        topCenter.add(greetLabel);
        centerPanel.add(topCenter, BorderLayout.NORTH);

        // Panel for login instructions
        JPanel midCenter = new JPanel();
        userInstruction = new JTextArea(InstructionsHandler.userAccount());
        userInstruction.setEditable(false);
        userInstruction.setOpaque(false);
        midCenter.add(userInstruction);
        centerPanel.add(midCenter, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new GridLayout(0, 4, 1, 0));
        add(buttonPanel, BorderLayout.SOUTH);
        viewButton = new JButton("View Ticket");
        GUIStyle.styleButton(viewButton);
        createButton = new JButton("Create Ticket");
        GUIStyle.styleButton(createButton);
        editButton = new JButton("Edit Ticket");
        GUIStyle.styleButton(editButton);
        deleteButton = new JButton("Delete Ticket");
        deleteButton.setEnabled(false);
        GUIStyle.styleButton(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
    }
    @Override
    public void createEvents() { 
        backButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("faq button has been pressed");
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
        
        createButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create button has been pressed");
                WindowManager.getManager().setTicketVisible(true, "Create");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
        editButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create button has been pressed");
                WindowManager.getManager().setTicketVisible(true, "Update");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
        viewButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create button has been pressed");
                WindowManager.getManager().setTicketVisible(true, "View");
                WindowManager.getManager().setUserAccountVisible(false);
            }
        });
        
    }
}
