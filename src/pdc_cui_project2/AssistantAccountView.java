package pdc_cui_project2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * AssistantAccountView includes components for buttons, labels, and 
 * text area to display instructions.
 *
 * @author Daniil
 */
public final class AssistantAccountView extends JPanel implements Page {

    // Buttons for navigating
    protected JButton backButton;
    protected JButton assistantReply;
    protected JButton assistantView;
    
    // Label to greet the user 
    protected JLabel greetLabel;

    // Text area to display user instructions
    protected JTextArea userInstruction;

    // Initialise class by calling the overridable method from page interface
    public AssistantAccountView() {
        createComponents();
    }

    // Set up the GUI components for AssistantAccountView.
    @Override
    public void createComponents() {

        // Set layout to BorderLayout for all components
        setLayout(new BorderLayout());

        // Create and style the home button for navigation
        backButton = new JButton("Home");
        GUIStyle.styleButton(backButton);
        add(backButton, BorderLayout.NORTH);
        
        // Create panel for the main content in center
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // Panel for greeting label (create and style greet label)
        JPanel topCenter = new JPanel();
        greetLabel = new JLabel("\nHello, User!\n");
        GUIStyle.styleLabel(greetLabel);
        greetLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        topCenter.add(greetLabel);
        centerPanel.add(topCenter, BorderLayout.NORTH);

        // Panel for displaying user instructions (disable editable mode, remove bg)
        JPanel midCenter = new JPanel();
        userInstruction = new JTextArea(InstructionsHandler.assistantTicket());
        userInstruction.setEditable(false);
        userInstruction.setOpaque(false);
        midCenter.add(userInstruction);
        centerPanel.add(midCenter, BorderLayout.CENTER);
        
        // Panel for buttons at the bottom
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 1, 0));
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Create and style buttons for viewing and replying to user tickets
        assistantView = new JButton("View User Tickets");
        GUIStyle.styleButton(assistantView);
        assistantReply = new JButton("Reply To User Tickets");
        GUIStyle.styleButton(assistantReply);

        // Add buttons to the buttonPanel
        buttonPanel.add(assistantView);
        buttonPanel.add(assistantReply);
    }
}
