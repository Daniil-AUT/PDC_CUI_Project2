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
    private JTextArea faqInfo;
    
    public FaqView() {
        createComponents();
        createEvents();
    }
    
    public void createComponents() {
        setLayout(new BorderLayout());
        backButton = new JButton("Go Back");
        GUIStyle.styleButton(backButton);
        faqInfo = new JTextArea("\n\n"+showInfo(), 60, 50);
        faqInfo.setEditable(false);
        GUIStyle.styleTextArea(faqInfo);
        
        JScrollPane scroll = new JScrollPane(faqInfo);
        scroll.setEnabled(true);
        add(backButton, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
    
    public void createEvents() {
        backButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("home button has been pressed");
                WindowManager.getManager().setFAQVisible(false);
                WindowManager.getManager().setHomeVisible(true);
            }
        });
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
            return "Sorry, FAQ is unavailable at the moment.";
        } catch (IOException e) {
            return "Apologies, An Error Occured Loading an FAQ";
        }
        return text;
    }
}
