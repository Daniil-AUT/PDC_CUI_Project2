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
public class FaqView extends JPanel implements Page {
    public JButton backButton;
    public JTextArea faqInfo;
    
    public FaqView() {
        createComponents();
    }
    
    @Override
    public void createComponents() {
        setLayout(new BorderLayout());
        backButton = new JButton("Go Back");
        GUIStyle.styleButton(backButton);
        faqInfo = new JTextArea();
        faqInfo.setEditable(false);
        GUIStyle.styleTextArea(faqInfo);
        
        JScrollPane scroll = new JScrollPane(faqInfo);
        scroll.setEnabled(true);
        add(backButton, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}
