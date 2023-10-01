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
}
