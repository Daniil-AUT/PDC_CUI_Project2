package pdc_cui_project2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Daniil
 */
public class AssistantAccountView extends JPanel implements Page{
    public JButton backButton;
    public JTextArea userInstruction;
    public JButton assistantReply;
    public JButton assistantView;
    public JLabel greetLabel;
    
    public AssistantAccountView() {
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
           greetLabel = new JLabel("\nHello, User!\n");
           GUIStyle.styleLabel(greetLabel);
           greetLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
           topCenter.add(greetLabel);
           centerPanel.add(topCenter, BorderLayout.NORTH);

           // Panel for login instructions
           JPanel midCenter = new JPanel();
           userInstruction = new JTextArea(InstructionsHandler.assistantTicket());
           userInstruction.setEditable(false);
           userInstruction.setOpaque(false);
           midCenter.add(userInstruction);
           centerPanel.add(midCenter, BorderLayout.CENTER);

           JPanel buttonPanel = new JPanel(new GridLayout(0, 2, 1, 0));
           add(buttonPanel, BorderLayout.SOUTH);
           assistantView = new JButton("View User Tickets");
           GUIStyle.styleButton(assistantView);
           assistantReply = new JButton("Reply To User Tickets");
           GUIStyle.styleButton(assistantReply);

           buttonPanel.add(assistantView);
           buttonPanel.add(assistantReply);
    }
}
