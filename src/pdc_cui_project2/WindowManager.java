package pdc_cui_project2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Daniil
 */
public class WindowManager extends JFrame {

    public JFrame mainFrame;
    private HomeView homeView;
    private FaqView faqView;

    public WindowManager() {
        mainFrame = new JFrame();

        homeView = new HomeView();
        faqView = new FaqView();

        add(homeView);
        
        add(faqView);
    }
    public void setHome(boolean visible) {
        homeView.setVisible(visible);
    }
    public void setFAQ(boolean visible) {
        faqView.setVisible(visible);
    }
}

