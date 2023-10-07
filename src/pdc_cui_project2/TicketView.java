package pdc_cui_project2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/*
 *
 * @author Daniil
 */
public class TicketView extends JPanel implements Page {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JLabel updateLabel;
    private JTextArea updateText;
    private JButton updateButton;
    private JLabel replyLabel;
    private JButton replyButton;
    private JTextArea replyText;
    private JButton asViewButton;
    private JLabel viewLabel;
    private JTextArea viewText;
    private JLabel createLabel;
    private JTextArea createText;
    private JButton createButton;
    private JButton backButton;
    private String currentView;

    public TicketView() {
        createComponents();
        createEvents();
    }

    @Override
    public void createComponents() {
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add("Reply", windowReply());
        cardPanel.add("AsView", windowAsView());
        cardPanel.add("View", windowView());
        cardPanel.add("Create", windowCreate());
        cardPanel.add("Update", windowUpdate());
        backButton = new JButton("Back");
        
        
        GUIStyle.styleButton(backButton);
        add(backButton, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    public void showAsReplyWindow() {
        cardLayout.show(cardPanel, "Reply");
        currentView = "Reply";
    }

    public void showAsViewWindow() {
        cardLayout.show(cardPanel, "AsView");
        currentView = "AsView";
    }
    public void showViewWindow() {
        cardLayout.show(cardPanel, "View");
        currentView = "View";
    }
    public void showCreateWindow() {
        cardLayout.show(cardPanel, "Create");
        currentView = "Create";
    }
    public void showUpdateWindow() {
        cardLayout.show(cardPanel, "Update");
        currentView = "Update";
    }
    
    
    public JPanel windowReply() {
        JPanel asViewPanel = new JPanel(new BorderLayout());
        JPanel topEntry = new JPanel();
        
        replyLabel = new JLabel("Enter You Reply Below");
        GUIStyle.styleLabel(replyLabel);
        replyLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 26));
        topEntry.add(replyLabel, BorderLayout.CENTER);
        
        replyText = new JTextArea("");
        replyText.setRows(5);
        replyText.setLineWrap(true);
        replyText.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(replyText);
        replyText.setBorder(new LineBorder(Color.BLACK)); 
        GUIStyle.styleTextArea(replyText);
        replyText.setFont(new Font("Consolas", Font.CENTER_BASELINE, 15));
        
        replyButton = new JButton("Reply");
        GUIStyle.styleButton(replyButton);
        
        // Add components for AsView window
        asViewPanel.add(topEntry, BorderLayout.NORTH);
        asViewPanel.add(scrollPane, BorderLayout.CENTER);  
        asViewPanel.add(replyButton, BorderLayout.SOUTH);
        return asViewPanel;
    }
    
    public JPanel windowAsView() {
        JPanel asViewPanel = new JPanel();
        
        asViewButton = new JButton("View All Tickets");
        asViewPanel.add(asViewButton, BorderLayout.NORTH);
        
        // Add components for AsView window
        return asViewPanel;
    }

    public JPanel windowView() {
        JPanel viewPanel = new JPanel();
        viewLabel = new JLabel("Your Ticket Information");
        GUIStyle.styleLabel(viewLabel);
        viewLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 24));
        viewText = new JTextArea("");
        viewText.setEditable(false);
        viewText.setOpaque(false);
        viewPanel.add(viewLabel, BorderLayout.NORTH);
        viewPanel.add(viewText, BorderLayout.CENTER);
        // Add components for View window
        return viewPanel;
    }

    public JPanel windowCreate() {
        JPanel createPanel = new JPanel(new BorderLayout());
        JPanel topEntry = new JPanel();

        createLabel = new JLabel("Please Enter Ticket Description");
        GUIStyle.styleLabel(createLabel);
        createLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 26));
        topEntry.add(createLabel, BorderLayout.CENTER);

        createText = new JTextArea("");  // Use a different variable for createText
        createText.setRows(5);
        createText.setLineWrap(true);
        createText.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(createText);  
        createText.setBorder(new LineBorder(Color.BLACK));
        GUIStyle.styleTextArea(createText);
        createText.setFont(new Font("Consolas", Font.CENTER_BASELINE, 15));

        createButton = new JButton("Create Ticket");
        GUIStyle.styleButton(createButton);

        // Add components for Create window
        createPanel.add(topEntry, BorderLayout.NORTH);
        createPanel.add(scrollPane, BorderLayout.CENTER);
        createPanel.add(createButton, BorderLayout.SOUTH);
        return createPanel;
    }


    public JPanel windowUpdate() {
        JPanel updatePanel = new JPanel(new BorderLayout());
        JPanel topEntry = new JPanel();

        updateLabel = new JLabel("Please Edit Your Ticket Details");
        GUIStyle.styleLabel(updateLabel);
        updateLabel.setFont(new Font("Consolas", Font.CENTER_BASELINE, 26));
        topEntry.add(updateLabel, BorderLayout.CENTER);

        updateText = new JTextArea("");  
        updateText.setRows(5);
        updateText.setLineWrap(true);
        updateText.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(updateText);  
        updateText.setBorder(new LineBorder(Color.BLACK));
        GUIStyle.styleTextArea(updateText);
        updateText.setFont(new Font("Consolas", Font.CENTER_BASELINE, 15));

        updateButton = new JButton("Update Ticket");
        GUIStyle.styleButton(updateButton);

        // Add components for Create window
        updatePanel.add(topEntry, BorderLayout.NORTH);
        updatePanel.add(scrollPane, BorderLayout.CENTER);
        updatePanel.add(updateButton, BorderLayout.SOUTH);
        
        return updatePanel;
    }

    @Override
    public void createEvents() {
        backButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                WindowManager.getManager().setTicketVisible(false, "");
                if(currentView.equals("Update") || currentView.equals("View") 
                        || currentView.equals("Create")) {
                    WindowManager.getManager().setUserAccountVisible(true);
                }
                else {
                    WindowManager.getManager().setAssistantAccountVisible(true);
                }
                WindowManager.getManager().setTicketVisible(false, "");
            }
        });
        
        createButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("create button has been pressed");
                WindowManager.getManager().setUserAccountVisible(true);
                WindowManager.getManager().setTicketVisible(false, "");
            }
        });
        updateButton.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("update button has been pressed");
                WindowManager.getManager().setUserAccountVisible(true);
                WindowManager.getManager().setTicketVisible(false, "");
            }
        });
    }
}
