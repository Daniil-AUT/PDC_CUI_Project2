package pdc_cui_project2;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Daniil
 */
public class HelpDeskApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame window = new WindowManager();
        window.setSize(718, 540);
        window.setTitle("AUT Helpdesk");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
}
