package pdc_cui_project2;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Daniil
 */
public class GUIStyle {

    public static void styleButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLUE);
        button.setFont(new Font("Consolas", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(150, 30));
        button.setFocusPainted(false);
    }
    
    public static void styleLabel(JLabel label) { 
        label.setFont(new Font("Consolas", Font.BOLD, 12));
        label.setText("<html>" + label.getText().replace("\n", "<br>") + "</html>");
    }
    
    public static void styleTextArea(JTextArea text) {
        text.setFont(new Font("Consolas", Font.PLAIN, 12));
        
    }
    
    public static void styleRadioButton(JRadioButton radioButton) {
        // Set text color to black for better contrast
        radioButton.setForeground(Color.BLACK);

        // Set background color to a light gray for a neutral look
        radioButton.setOpaque(true);

        // Use the default font with a slightly larger size for clarity
        Font defaultFont = UIManager.getFont("RadioButton.font");
        radioButton.setFont(defaultFont.deriveFont(defaultFont.getSize() + 2f));

        // Remove focus painting for a cleaner appearance
        radioButton.setFocusPainted(false);
    }
}
