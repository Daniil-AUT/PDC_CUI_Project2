package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;

/**
 * Shows a home page for the AUT HelpDesk App which extends JPanel and
 * implements the Page interface. Provides components to access login, sign up,
 * and FAQ pages The page also contains the instructions to guide user. The
 * components use GUIStyle class to look universal.
 *
 * This is a view of a Home/FAQ MVC pattern.
 *
 * @author Daniil
 */
public final class HomeView extends JPanel implements Page {

    // Buttons and labels for the home view
    protected JButton loginButton;
    protected JButton signupButton;
    protected JButton faqButton;
    protected JLabel greetLabel;
    protected JTextArea instructionText;

    // Instantiate Constructor with a method which compiles components
    public HomeView() {
        createComponents();
    }

    @Override
    public void createComponents() {
        // Set layout for the overall panel
        setLayout(new BorderLayout());

        // Create and style FAQ button
        faqButton = new JButton("View FAQ");
        GUIStyle.styleButton(faqButton);
        add(faqButton, BorderLayout.NORTH);

        // Center panel for login label, instructions, radio buttons, and entry fields
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // Panel for login label
        JPanel topCenter = new JPanel();
        greetLabel = new JLabel("\nWelcome to AUT HelpDesk!\n");
        GUIStyle.styleLabel(greetLabel);
        greetLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        topCenter.add(greetLabel);
        centerPanel.add(topCenter, BorderLayout.NORTH);

        // Panel for login instructions
        JPanel midCenter = new JPanel();
        instructionText = new JTextArea(InstructionsHandler.userGreeting());
        instructionText.setEditable(false);
        instructionText.setOpaque(false);
        midCenter.add(instructionText);
        centerPanel.add(midCenter, BorderLayout.CENTER);
        
        // Panel for login and sign up buttons
        JPanel bottomPanel = new JPanel(new GridLayout(0, 2, 1, 0));
        loginButton = new JButton("Log In");
        GUIStyle.styleButton(loginButton);
        signupButton = new JButton("Sign Up");
        GUIStyle.styleButton(signupButton);
        bottomPanel.add(loginButton);
        bottomPanel.add(signupButton);
        add(bottomPanel, BorderLayout.SOUTH);

    }

}
