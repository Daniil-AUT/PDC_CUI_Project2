package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Log In View is a page that displays GUI components for user to interact with
 * login process. The class extends JPanel and implement Page interface to be
 * considered a page of an app.
 *
 * @author Daniil
 */
public final class LoginView extends JPanel implements Page {

    // Initialise GUI components for buttons,text area, label and etc. 
    protected JButton backButton;
    protected JLabel loginLabel;
    protected JTextArea loginExplain;
    protected JTextField idField;
    protected JLabel idLabel;
    protected JPasswordField passField;
    protected JLabel passLabel;
    protected JButton loginButton;
    protected ButtonGroup users;
    protected JRadioButton student;
    protected JRadioButton assistant;
    protected JRadioButton customer;

    // Constructor for the LoginView class which calls overridable method
    public LoginView() {
        createComponents();
    }
    
     // Override method creates GUI components
    @Override
    public void createComponents() {
        
        // Sets the initial layout for components
        setLayout(new BorderLayout());
        
        // Button to navigate back to the home page
        backButton = new JButton("Home");
        GUIStyle.styleButton(backButton);
        add(backButton, BorderLayout.NORTH);

        // Center panel for login label, instructions, radio buttons, and entry fields
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // Panel for login label
        JPanel topCenter = new JPanel();
        loginLabel = new JLabel("\nLog In\n");
        GUIStyle.styleLabel(loginLabel);
        loginLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        topCenter.add(loginLabel);
        centerPanel.add(topCenter, BorderLayout.NORTH);

        // Panel for login instructions
        JPanel midCenter = new JPanel();
        loginExplain = new JTextArea(InstructionsHandler.userLogIn());
        loginExplain.setEditable(false);
        loginExplain.setOpaque(false);
        midCenter.add(loginExplain);
        centerPanel.add(midCenter, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(0, 1, 1, 1));
        // Panel for radio buttons
        JPanel radioPanel = new JPanel();
        
        // Create a button group for all user types
        users = new ButtonGroup();
        student = new JRadioButton("Student", true);
        assistant = new JRadioButton("Assistant", false);
        customer = new JRadioButton("Customer", false);
        
        // Add the radio buttons to the group
        users.add(student);
        users.add(assistant);
        users.add(customer);
        
        // Add radio buttons to the radio panel
        radioPanel.add(student);
        radioPanel.add(assistant);
        radioPanel.add(customer);
        
        // Style each radio button
        GUIStyle.styleRadioButton(student);
        GUIStyle.styleRadioButton(customer);
        GUIStyle.styleRadioButton(assistant);
        
        // Add button panel to the input panel
        inputPanel.add(radioPanel);
        
        // Text field for entering user ID
        idField = new JTextField("", 24);
        idLabel = new JLabel("ID:");
        idField.setBorder(new LineBorder(Color.BLACK, 1));
        GUIStyle.styleTextField(idField);
        inputPanel.add(idLabel);  // Add label

        inputPanel.add(idField);
        
        // Password field for entering the password 
        passField = new JPasswordField("", 24);
        passField.setBorder(new LineBorder(Color.BLACK, 1));
        GUIStyle.styleTextField(passField);
        passLabel = new JLabel("Password:");
        inputPanel.add(passLabel);  // Add label
        inputPanel.add(passField);
        
        // Button to initiate the login process
        loginButton = new JButton("Log In");
        GUIStyle.styleButton(loginButton);

        inputPanel.add(loginButton, BorderLayout.CENTER);
        centerPanel.add(inputPanel, BorderLayout.SOUTH);
    }
}
