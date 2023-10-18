package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Daniil
 */
public final class FaqView extends JPanel implements Page {
    protected JButton backButton;
    protected JTextArea faqInfo;
    
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
