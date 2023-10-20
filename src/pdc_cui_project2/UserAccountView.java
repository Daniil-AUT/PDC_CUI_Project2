package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Daniil
 */
public final class UserAccountView extends JPanel implements Page {
    
    // Buttons for user to interact with.
    protected JButton backButton;
    protected JButton viewButton;
    protected JButton createButton;
    protected JButton editButton;
    protected JButton deleteButton;
    
    // Label for greeting message
    protected JLabel greetLabel;
    protected JTextArea userInstruction;
    
    public UserAccountView() {
        createComponents();
    }
    
    @Override
    public void createComponents() { 
        
        // Set the layout for the main panel
        setLayout(new BorderLayout());
        
        // Button for navigating back to Home page
        backButton = new JButton("Home");
        GUIStyle.styleButton(backButton);
        add(backButton, BorderLayout.NORTH);
        
        // Panel for main content in the center
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel for displaying a greeting message
        JPanel topCenter = new JPanel();
        greetLabel = new JLabel("\nHello, <Error>");
        GUIStyle.styleLabel(greetLabel);
        greetLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        topCenter.add(greetLabel);
        centerPanel.add(topCenter, BorderLayout.NORTH);

        // Panel for displaying user instructions
        JPanel midCenter = new JPanel();
        userInstruction = new JTextArea(InstructionsHandler.userAccount());
        userInstruction.setEditable(false);
        userInstruction.setOpaque(false);
        midCenter.add(userInstruction);
        centerPanel.add(midCenter, BorderLayout.CENTER);
        
        // Panel for buttons at the bottom
        JPanel buttonPanel = new JPanel(new GridLayout(0, 4, 1, 0));
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Create and Style Buttons for user interaction    
        viewButton = new JButton("View Ticket");
        GUIStyle.styleButton(viewButton);
        createButton = new JButton("Create Ticket");
        GUIStyle.styleButton(createButton);
        editButton = new JButton("Edit Ticket");
        GUIStyle.styleButton(editButton);
        deleteButton = new JButton("Delete Ticket");
        GUIStyle.styleButton(deleteButton);
        
        // Add buttons to buttonPanel
        buttonPanel.add(viewButton);
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
    }
}

