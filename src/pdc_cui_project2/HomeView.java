package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Daniil
 */
public final class HomeView extends JPanel {
    private JButton loginButton;
    private JButton signupButton;
    private JButton faqButton;
    private JLabel greetLabel;
    private JTextArea instructionText;
    
    
    public HomeView() {
        createComponents();
        createEvents();
    }
    
    private void createComponents() {
        setLayout(new BorderLayout());
        
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

        
        JPanel bottomPanel = new JPanel();
        loginButton = new JButton("Log In");
        GUIStyle.styleButton(loginButton);
        signupButton = new JButton("Sign Up");
        GUIStyle.styleButton(signupButton);
        bottomPanel.add(loginButton);
        bottomPanel.add(signupButton);
        add(bottomPanel, BorderLayout.SOUTH);
        
    }
    
    private void createEvents() {
       loginButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("login button has been pressed");
                WindowManager.getManager().setHomeVisible(false);
                WindowManager.getManager().setLoginVisible(true);
            }
        }); 
       
       signupButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("signup button has been pressed");
                WindowManager.getManager().setSignUpVisible(true);
                WindowManager.getManager().setHomeVisible(false);
                
            }
        });
        
        
        faqButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("faq button has been pressed");
                WindowManager.getManager().setHomeVisible(false);
                WindowManager.getManager().setFAQVisible(true);
            }
        });
        
    }

}
