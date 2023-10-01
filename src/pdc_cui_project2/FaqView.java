package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author Daniil
 */
public class FaqView extends JPanel {
    private JButton backButton;
    private JLabel faqInfo;
    
    public FaqView() {
        backButton = new JButton();
        faqInfo = new JLabel();
        System.out.println(showInfo());
    }
    private static String showInfo() {
        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("HelpDeskFiles\\faq.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + "\n";
            }
            reader.close();
            // Give possible exceptions errors and handle them through print statements
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, FAQ is unavailable at the moment.");
        } catch (IOException e) {
            System.out.println("Apologies, An Error Occured Loading an FAQ");
        }
        return text;
    }
}
