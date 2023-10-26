package pdc_cui_project2;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

/**
 * GUIStyle is used as utility to style Swing components
 * by making their design consistent throughout each page.
 * 
 * @author Daniil
 */
public class GUIStyle {
    
    // Apply a consistent style to a JButton.
    public static void styleButton(JButton button) {
        
        // Use a white text colour and blue background.
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        
        // Set appropriate font and size
        button.setFont(new Font("Consolas", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 30));
        button.setFocusPainted(false);
    }
    
    // Apply a consistent style to a JLabel.
    public static void styleLabel(JLabel label) {
        
        // Set appropriate font and size
        label.setFont(new Font("Consolas", Font.BOLD, 24));
        
        /*
        For text label to display text on new line, use the '<br>'
        instead of '\n'
        */
        label.setText("<html>" + label.getText().replace("\n", "<br>") + "</html>");
    }
    
    // Apply a consistent style to a JTextArea.
    public static void styleTextArea(JTextArea text) {
        
        // Set appropriate font and size
        text.setFont(new Font("Consolas", Font.CENTER_BASELINE, 13));
        
        // Create border and place limit on word count for each line.
        text.setBorder(new LineBorder(Color.BLACK, 1));
        text.setLineWrap(true);
        text.setRows(5);
        text.setWrapStyleWord(true);
    }
    
    // Apply a consistent style to a JTextField.
    public static void styleTextField(JTextField text) {
        
        // Set appropriate font and size
        text.setFont(new Font("Consolas", Font.BOLD, 14));
    }
    
    // Apply a consistent style to a JRadioButton
    public static void styleRadioButton(JRadioButton radioButton) {

        // Set background color to a light gray for a neutral look
        radioButton.setOpaque(true);
        
        // Set appropriate font and size
        radioButton.setFont(new Font("Consolas", Font.BOLD, 15));
        
        // --CHAT-GPT assisted method--
        // Remove focus painting for a cleaner appearance
        radioButton.setFocusPainted(false);
    }
}
