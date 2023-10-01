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
        // TODO code application logic here
        HomeView hw = new HomeView();
        JFrame frame = new JFrame();
        frame.add(hw);
        
        frame.setSize(718, 540);
        frame.setTitle("AUT Helpdesk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
