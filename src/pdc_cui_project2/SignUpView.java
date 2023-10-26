package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * SignUpView show GUI for sign-up page.
 * Extends JPanel and implements the Page interface for consistent styling.
 *
 * @author Daniil
 */
public final class SignUpView extends JPanel implements Page {

    // Label components for directing
    protected JLabel signupLabel;
    protected JLabel nameLabel;
    protected JLabel lName;
    protected JLabel emailLabel;
    protected JLabel passLabel;
    protected JLabel cPassLabel;

    // Button components for navigating
    protected JButton backButton;
    protected JButton signupButton;

    // Text fields for user input
    protected JTextField name;
    protected JTextField lastName;
    protected JTextField email;

    // Password fields for user input (confidential)
    protected JPasswordField password;
    protected JPasswordField confPassword;

    // Radio buttons to choose user type
    protected ButtonGroup users;
    protected JRadioButton student;
    protected JRadioButton assistant;
    protected JRadioButton customer;

    // Initialises Constructs with overridable method from page interface
    public SignUpView() {
        createComponents();
    }

    @Override
    public void createComponents() {
        // Create layout for the panel
        setLayout(new BorderLayout());

        // Create and style button for navigating back to the home page
        backButton = new JButton("Home");
        GUIStyle.styleButton(backButton);
        add(backButton, BorderLayout.NORTH);

        // Create center panel for content
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // Panel for the sign-up label
        JPanel topCenter = new JPanel();
        signupLabel = new JLabel("\nSign Up\n");
        GUIStyle.styleLabel(signupLabel);
        signupLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        topCenter.add(signupLabel);
        centerPanel.add(topCenter, BorderLayout.NORTH);

        // Group of radio buttons for user type selection
        users = new ButtonGroup();
        student = new JRadioButton("Student", true);
        assistant = new JRadioButton("Assistant", false);
        customer = new JRadioButton("Customer", false);

        // Add radio buttons to the same group
        users.add(student);
        users.add(assistant);
        users.add(customer);

        // Style radio buttons
        GUIStyle.styleRadioButton(student);
        GUIStyle.styleRadioButton(customer);
        GUIStyle.styleRadioButton(assistant);

        // Middle panel for radio buttons
        JPanel middlePanel = new JPanel();
        middlePanel.add(student);
        middlePanel.add(assistant);
        middlePanel.add(customer);

        // Text fields for user input
        name = new JTextField();
        name.setBorder(new LineBorder(Color.BLACK, 1));
        GUIStyle.styleTextField(name);
        lastName = new JTextField();
        lastName.setBorder(new LineBorder(Color.BLACK, 1));
        GUIStyle.styleTextField(lastName);
        email = new JTextField();
        email.setBorder(new LineBorder(Color.BLACK, 1));
        GUIStyle.styleTextField(email);
        password = new JPasswordField();
        password.setBorder(new LineBorder(Color.BLACK, 1));
        GUIStyle.styleTextField(password);
        confPassword = new JPasswordField();
        confPassword.setBorder(new LineBorder(Color.BLACK, 1));
        GUIStyle.styleTextField(confPassword);

        // Bottom panel for name, last name, email, password fields, and signup button
        JPanel bottomPanel = new JPanel(new GridLayout(0, 1, 1, 0));
        JPanel fullName = new JPanel(new GridLayout(1, 4, 1, 0));
        bottomPanel.add(middlePanel);
        
        // Panel for name field (create and add name field to panel)
        JPanel namePanel = new JPanel(new BorderLayout());
        nameLabel = new JLabel("Name:");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(name, BorderLayout.CENTER);
        
        // Panel for ;ast name field (create and add last name field to panel)
        JPanel lastNamePanel = new JPanel(new BorderLayout());
        lName = new JLabel("Last Name:");
        lName.setHorizontalAlignment(JLabel.CENTER);
        lastNamePanel.add(lName, BorderLayout.NORTH);
        lastNamePanel.add(lastName, BorderLayout.CENTER);
        
        // Add panels to full name panel
        fullName.add(namePanel);
        fullName.add(lastNamePanel);
        
        // Add remainign Email, Password, and confirm Passowrd components
        bottomPanel.add(fullName);
        emailLabel = new JLabel("Email:");
        bottomPanel.add(emailLabel);
        bottomPanel.add(email);
        passLabel = new JLabel("Password:");
        bottomPanel.add(passLabel);
        bottomPanel.add(password);
        cPassLabel = new JLabel("Confirm Password:");
        bottomPanel.add(cPassLabel);
        bottomPanel.add(confPassword);
        centerPanel.add(bottomPanel);

        // Create and style signup button, add to the page
        signupButton = new JButton("Sign Up");
        GUIStyle.styleButton(signupButton);
        add(signupButton, BorderLayout.SOUTH);
    }

}
