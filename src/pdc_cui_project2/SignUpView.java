package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Daniil
 */

public final class SignUpView extends JPanel implements Page {
    protected JButton backButton;
    protected JLabel signupLabel;
    protected JTextField name;
    protected JLabel nameLabel;
    protected JTextField lastName;
    protected JLabel lName;
    protected JTextField email;
    protected JLabel emailLabel;
    protected JPasswordField password;
    protected JLabel passLabel;
    protected JPasswordField confPassword;
    protected JLabel cPassLabel;
    protected JButton signupButton;
    protected ButtonGroup users;
    protected JRadioButton student;
    protected JRadioButton assistant;
    protected JRadioButton customer;
    
    public SignUpView() {
        createComponents();
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
        signupLabel = new JLabel("\nSign Up\n");
        GUIStyle.styleLabel(signupLabel);
        signupLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        topCenter.add(signupLabel);
        centerPanel.add(topCenter, BorderLayout.NORTH);
        
        users = new ButtonGroup();
        student = new JRadioButton("Student", true);
        assistant = new JRadioButton("Assistant", false);
        customer = new JRadioButton("Customer", false);
        users.add(student);
        users.add(assistant);
        users.add(customer);
        GUIStyle.styleRadioButton(student);
        GUIStyle.styleRadioButton(customer);
        GUIStyle.styleRadioButton(assistant);
        // Middle panel for radio buttons
        JPanel middlePanel = new JPanel(); 
        middlePanel.add(student);
        middlePanel.add(assistant);
        middlePanel.add(customer);
        
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
        // Bottom panel for name, lastname, email, password fields, and signup button
        JPanel bottomPanel = new JPanel(new GridLayout(0, 1, 1, 0)); // Use GridLayout with two columns
        JPanel fullName = new JPanel(new GridLayout(1, 4, 1, 0));
        bottomPanel.add(middlePanel);
        // Panel for name
        JPanel namePanel = new JPanel(new BorderLayout());
        nameLabel = new JLabel("Name:");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(name, BorderLayout.CENTER);
        
        JPanel lastNamePanel = new JPanel(new BorderLayout());
        lName = new JLabel("Last Name:");
        lName.setHorizontalAlignment(JLabel.CENTER);
        lastNamePanel.add(lName, BorderLayout.NORTH);
        lastNamePanel.add(lastName, BorderLayout.CENTER);
        
        fullName.add(namePanel);
        fullName.add(lastNamePanel);
        
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
        
        // Signup button
        signupButton = new JButton("Sign Up");
        GUIStyle.styleButton(signupButton);
        add(signupButton, BorderLayout.SOUTH);
    }
    
}
