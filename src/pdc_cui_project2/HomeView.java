package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Daniil
 */
public class HomeView extends JPanel {
    private JButton loginButton;
    private JPanel levelButtons;
    private JPanel levelLabels;
    private JButton signupButton;
    private JButton faqButton;
    private JLabel greetLabel;
    private JLabel instructionLabel;
    
    @Override
    public void paintComponent( Graphics g ){
        Image image = new ImageIcon("swag.png").getImage();
        g.drawImage(image, 100,100,null);
    }
    public HomeView() {
        setLayout(new BorderLayout());
        
        
        levelLabels = new JPanel();
        levelLabels.setLayout(new BoxLayout(levelLabels, BoxLayout.Y_AXIS));
        
        greetLabel = new JLabel("Welcome to the AUT HelpDesk!");
        GUIStyle.styleLabel(greetLabel);
        // Chat GPT helped
        instructionLabel = new JLabel(InstructionsHandler.userGreeting());
        GUIStyle.styleLabel(instructionLabel);
        
        levelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        loginButton = new JButton("Log In");
        GUIStyle.styleButton(loginButton);
        loginButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("login button has been pressed");
            }
        });
        
        signupButton = new JButton("Sign Up");
        GUIStyle.styleButton(signupButton);
        signupButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("signup button has been pressed");
            }
        });
        
        faqButton = new JButton("View FAQ");
        GUIStyle.styleButton(faqButton);
        faqButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("faq button has been pressed");
                
            }
        });
        
        levelLabels.add(greetLabel);
        levelLabels.add(instructionLabel);
        add(levelLabels, BorderLayout.CENTER);

        // Add some space around the label panel using EmptyBorder
        levelLabels.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        levelButtons.add(loginButton);
        levelButtons.add(signupButton);
        add(levelButtons, BorderLayout.SOUTH);
        
        add(faqButton, BorderLayout.NORTH);
        
    }
}
