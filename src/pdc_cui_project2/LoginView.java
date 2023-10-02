package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author Daniil
 */
public class LoginView extends JPanel {
    // back button home
    private JButton backButton;
    // Label for Login
    private JLabel loginLabel;
    // Brief explain what to do
    private JTextArea loginExplain;
    // field for email
    private JTextField emailField;
    // field password
    private JPasswordField passField;
    // button to log in
    private JButton loginButton;
    
    private ButtonGroup users;
    private JRadioButton student;
    private JRadioButton assistant;
    private JRadioButton customer;

    
    // for incorrect user input, use paint component
    public LoginView() {
        createComponents();
        createEvents();
    }
    
    public void createComponents() {
        setLayout(new BorderLayout());

        // Top panel with back button
        JPanel topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);
        backButton = new JButton("Home");
        GUIStyle.styleButton(backButton);
        topPanel.add(backButton);

        // Center panel for login label, instructions, radio buttons, and entry fields
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // Panel for login label
        JPanel topCenter = new JPanel();
        loginLabel = new JLabel("Log In");
        GUIStyle.styleLabel(loginLabel);
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
        users = new ButtonGroup();
        student = new JRadioButton("Student", true);
        assistant = new JRadioButton("Assistant", false);
        customer = new JRadioButton("Customer", false);
        users.add(student);
        users.add(assistant);
        users.add(customer);
        radioPanel.add(student);
        radioPanel.add(assistant);
        radioPanel.add(customer);
        inputPanel.add(radioPanel); 

        // Add email field
        emailField = new JTextField("", 24);
        inputPanel.add(new JLabel("Email:"));  // Add label
        inputPanel.add(emailField);

        // Add password field
        passField = new JPasswordField("", 24);
        inputPanel.add(new JLabel("Password:"));  // Add label
        inputPanel.add(passField);

        // Add login button
        loginButton = new JButton("Log In");
        GUIStyle.styleButton(loginButton);
        
        inputPanel.add(loginButton, BorderLayout.CENTER);

        centerPanel.add(inputPanel, BorderLayout.SOUTH);
    }

    
    public void createEvents() {
        backButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("back button has been pressed");
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setLoginVisible(false);
            }
        });
    }
}
