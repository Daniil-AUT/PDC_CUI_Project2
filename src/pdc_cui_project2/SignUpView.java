package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Daniil
 */

public class SignUpView extends JPanel {
    private JButton backButton;
    private JLabel signupLabel;
    private JTextArea signInstruction;
    private JTextField name;
    private JTextField lastName;
    private JTextField email;
    private JPasswordField password;
    private JPasswordField confPassword;
    private JButton signupButton;
    private ButtonGroup users;
    private JRadioButton student;
    private JRadioButton assistant;
    private JRadioButton customer;
    
    public SignUpView() {
        createComponents();
        createEvents();
    }
    
    private void createComponents() { 
        setLayout(new BorderLayout());
        backButton = new JButton("Home");
        GUIStyle.styleButton(backButton);
        signupLabel = new JLabel("Sign Up");
        GUIStyle.styleLabel(signupLabel);
        signInstruction = new JTextArea();
        GUIStyle.styleTextArea(signInstruction);
        
        add(backButton, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // Panel for login label
        JPanel topCenter = new JPanel();
        GUIStyle.styleLabel(signupLabel);
        signupLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        topCenter.add(signupLabel);
        centerPanel.add(topCenter, BorderLayout.NORTH);

        // Panel for login instructions
        JPanel midCenter = new JPanel();
        signInstruction = new JTextArea(InstructionsHandler.userLogIn());
        signInstruction.setEditable(false);
        signInstruction.setOpaque(false);
        midCenter.add(signInstruction);
        centerPanel.add(midCenter, BorderLayout.CENTER);

        // Middle panel for radio buttons
        
        users = new ButtonGroup();
        student = new JRadioButton("Student", true);
        assistant = new JRadioButton("Assistant", false);
        customer = new JRadioButton("Customer", false);
        users.add(student);
        users.add(assistant);
        users.add(customer);
        
        JPanel middlePanel = new JPanel();
        middlePanel.add(student);
        middlePanel.add(assistant);
        middlePanel.add(customer);
        centerPanel.add(middlePanel, BorderLayout.SOUTH);

        
        name = new JTextField();
        lastName = new JTextField();
        email = new JTextField();
        
        password = new JPasswordField();
        confPassword = new JPasswordField();
        // Bottom panel for name, lastname, email, password fields, and signup button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(5, 2));
        bottomPanel.add(new JLabel("Name:"));
        bottomPanel.add(name);
        bottomPanel.add(new JLabel("Last Name:"));
        bottomPanel.add(lastName);
        bottomPanel.add(new JLabel("Email:"));
        bottomPanel.add(email);
        bottomPanel.add(new JLabel("Password:"));
        bottomPanel.add(password);
        bottomPanel.add(new JLabel("Confirm Password:"));
        bottomPanel.add(confPassword);
        add(bottomPanel, BorderLayout.SOUTH);

        // Signup button
        signupButton = new JButton("Sign Up");
        GUIStyle.styleButton(signupButton);
        add(signupButton, BorderLayout.SOUTH);
    }
    
    private void createEvents() { 
        
    }
    
}
