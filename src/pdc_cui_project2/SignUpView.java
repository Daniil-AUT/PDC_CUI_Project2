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
    private JTextArea signUpInstruction;
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
        
        // Middle panel for radio buttons
        JPanel middlePanel = new JPanel(); 
        middlePanel.add(student);
        middlePanel.add(assistant);
        middlePanel.add(customer);
        
        name = new JTextField();
        lastName = new JTextField();
        email = new JTextField();
        password = new JPasswordField();
        confPassword = new JPasswordField();
        
        // Bottom panel for name, lastname, email, password fields, and signup button
        JPanel bottomPanel = new JPanel(new GridLayout(0, 1, 1, 0)); // Use GridLayout with two columns
        JPanel fullName = new JPanel(new GridLayout(1, 4, 1, 0));
        bottomPanel.add(middlePanel);
        // Panel for name
        JPanel namePanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        namePanel.add(nameLabel, BorderLayout.NORTH);
        namePanel.add(name, BorderLayout.CENTER);
        
        JPanel lastNamePanel = new JPanel(new BorderLayout());
        JLabel lName = new JLabel("Last Name:");
        lName.setHorizontalAlignment(JLabel.CENTER);
        lastNamePanel.add(lName, BorderLayout.NORTH);
        lastNamePanel.add(lastName, BorderLayout.CENTER);
        
        fullName.add(namePanel);
        fullName.add(lastNamePanel);
        
        bottomPanel.add(fullName);
        bottomPanel.add(new JLabel("Email:"));
        bottomPanel.add(email);
        bottomPanel.add(new JLabel("Password:"));
        bottomPanel.add(password);
        bottomPanel.add(new JLabel("Confirm Password:"));
        bottomPanel.add(confPassword);
        centerPanel.add(bottomPanel);
        
        // Signup button
        signupButton = new JButton("Sign Up");
        GUIStyle.styleButton(signupButton);
        add(signupButton, BorderLayout.SOUTH);
    }
    
    private void createEvents() { 
        backButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("home button has been pressed");
                WindowManager.getManager().setHomeVisible(true);
                WindowManager.getManager().setSignUpVisible(false);
            }
        });
        signupButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("signup button has been pressed");
                
            }
        });
    }
    
}
