package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Daniil
 */
public class LoginView extends JPanel implements Page {
    // back button home
    public JButton backButton;
    // Label for Login
    public JLabel loginLabel;
    // Brief explain what to do
    public JTextArea loginExplain;
    // field for email
    public JTextField emailField;
    // field password
    public JPasswordField passField;
    // button to log in
    public JButton loginButton;
    
    public ButtonGroup users;
    public JRadioButton student;
    public JRadioButton assistant;
    public JRadioButton customer;

    
    // for incorrect user input, use paint component
    public LoginView() {
        createComponents();
        createEvents();
    }
    
    @Override
    public void createComponents() {
        setLayout(new BorderLayout());

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
        GUIStyle.styleRadioButton(student);
        GUIStyle.styleRadioButton(customer);
        GUIStyle.styleRadioButton(assistant);
        inputPanel.add(radioPanel); 

        emailField = new JTextField("", 24);
        inputPanel.add(new JLabel("Email:"));  // Add label
        inputPanel.add(emailField);
        
        passField = new JPasswordField("", 24);
        inputPanel.add(new JLabel("Password:"));  // Add label
        inputPanel.add(passField);

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
        
        loginButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("loginb button has been pressed");
                if(passField.getText().equals("test") && emailField.getText().equals("test")) {
                    WindowManager.getManager().setAssistantAccountVisible(true);
                    WindowManager.getManager().setLoginVisible(false);
                }
            }
        });
    }
}
