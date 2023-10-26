package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;

/**
 * Is the FAQ page for AUT HelpDesk application. Extends JPanel and implements
 * the Page interface. Displays components for FAQ page with information and a
 * button to navigate back.
 *
 * Styles components using GUIStyle class.
 *
 * @author Daniil
 */
public final class FaqView extends JPanel implements Page {

    protected JButton backButton;
    protected JTextArea faqInfo;

    /*
    Instatiates FAQView constructor which calls overridable
    interface method.
     */
    public FaqView() {
        createComponents();
    }

    // Compiles all GUI components into one method
    @Override
    public void createComponents() {
        // Set layout for the overall panel
        setLayout(new BorderLayout());

        // Create and style back button
        backButton = new JButton("Go Back");
        GUIStyle.styleButton(backButton);

        // Create text area for displaying FAQ information
        faqInfo = new JTextArea();
        faqInfo.setEditable(false);
        GUIStyle.styleTextArea(faqInfo);

        // Add a scroll pane for the faqInfo text area
        JScrollPane scroll = new JScrollPane(faqInfo);
        scroll.setEnabled(true);

        // Add components to the panel
        add(backButton, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}
