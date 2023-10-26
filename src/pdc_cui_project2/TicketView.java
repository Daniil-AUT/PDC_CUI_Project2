package pdc_cui_project2;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * TicketView displays the content for every ticket operation within a page.
 * Extends JPanel and implements the Page interface for consistent styling.
 *
 * @author Daniil
 */
public final class TicketView extends JPanel implements Page {

    // CardLayout for managing different panels
    protected CardLayout cardLayout;
    protected JPanel cardPanel;

    // Labels for different windows
    protected JLabel updateLabel;
    protected JLabel replyLabel;
    protected JLabel viewLabel;
    protected JLabel createLabel;
    protected JLabel idLabel;

    // TextArea for user input and displaying information
    protected JTextArea updateText;
    protected JTextArea replyText;
    protected JTextArea viewText;
    protected JTextArea createText;

    // Buttons for user to interact with
    protected JButton updateButton;
    protected JButton replyButton;
    protected JButton asViewButton;
    protected JButton createButton;
    protected JButton backButton;

    // Current view trigger
    protected String currentView;

    protected JTextField idField;
    
    // --CHAT-GPT assisted method--
    // Table for displaying information for user tickets
    protected JTable tickeTable;
    protected DefaultTableModel modelTable;

    /**
     * Constructor initializes page through overridable method from page
     * interface.
     */
    public TicketView() {
        createComponents();
    }

    /**
     * Override createComponents method from the Page interface. To create
     * layout and add components to the Ticket page.
     */
    @Override
    public void createComponents() {

        // Set layout for the main panel
        setLayout(new BorderLayout());

        // Initialise CardLayout to switching between different ticket opertations
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // Add different panels for each ticket operation
        cardPanel.add("Reply", windowReply());
        cardPanel.add("AsView", windowAsView());
        cardPanel.add("View", windowView());
        cardPanel.add("Create", windowCreate());
        cardPanel.add("Update", windowUpdate());
        backButton = new JButton("Back");
        
        // Button for navigating back to the account page
        GUIStyle.styleButton(backButton);
        add(backButton, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }
    
    // Show reply page for assistants
    public void showAsReplyWindow() {
        cardLayout.show(cardPanel, "Reply");
        currentView = "Reply";
    }

    // Show view page for assistants
    public void showAsViewWindow() {
        cardLayout.show(cardPanel, "AsView");
        currentView = "AsView";
    }
    
    // Show view page for customers & students
    public void showViewWindow() {
        cardLayout.show(cardPanel, "View");
        currentView = "View";
    }
    
    // Show create page for customers & students
    public void showCreateWindow() {
        cardLayout.show(cardPanel, "Create");
        currentView = "Create";
    }
    
    // Show update page for customers & students
    public void showUpdateWindow() {
        cardLayout.show(cardPanel, "Update");
        currentView = "Update";
    }
    
    // Creates a reply window
    public JPanel windowReply() {
        
        // Create panel for Reply page.
        JPanel asViewPanel = new JPanel(new BorderLayout());

        // Top Panel for ID Label and Field
        JPanel topPanel = new JPanel();
        idLabel = new JLabel("Enter User ID ");
        GUIStyle.styleLabel(idLabel);
        idLabel.setFont(new Font("Consolas", Font.BOLD, 13));
        idField = new JTextField("", 20);
        GUIStyle.styleTextField(idField);
        idField.setBorder(new LineBorder(Color.BLACK, 1));
        topPanel.add(idLabel);
        topPanel.add(idField);
        asViewPanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel for Reply Label and Text Area
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel topCenterPanel = new JPanel(new FlowLayout());
        replyLabel = new JLabel("Reply To User Ticket");
        GUIStyle.styleLabel(replyLabel);
        topCenterPanel.add(replyLabel);
        centerPanel.add(topCenterPanel, BorderLayout.NORTH);

        replyText = new JTextArea("");
        replyText.setRows(5);
        replyText.setLineWrap(true);
        replyText.setWrapStyleWord(true);
        GUIStyle.styleTextArea(replyText);
        replyText.setBorder(new LineBorder(Color.BLACK, 1));
        replyText.setFont(new Font("Consolas", Font.CENTER_BASELINE, 15));
        JScrollPane scrollPane = new JScrollPane(replyText);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        asViewPanel.add(centerPanel, BorderLayout.CENTER);

        // Reply Button at the bottom
        replyButton = new JButton("Reply");
        GUIStyle.styleButton(replyButton);
        asViewPanel.add(replyButton, BorderLayout.PAGE_END);

        return asViewPanel;
    }
    
    // --CHAT-GPT assisted method--
    // Create assistant view page
    public JPanel windowAsView() {
        
        // Create panel for the assistant view
        JPanel asViewPanel = new JPanel(new BorderLayout());
        
        // Create the label for page title
        JLabel viewTicketslabel = new JLabel("View User Tickets", SwingConstants.CENTER);
        JPanel labelPanel = new JPanel(new BorderLayout());
        GUIStyle.styleLabel(viewTicketslabel);
        labelPanel.add(viewTicketslabel, BorderLayout.CENTER);

        // Column names for id and description
        String[] columnNames = {"ID", "Description"};
        
        // Create a DefaultTableModel with no data
        modelTable = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // Create JTable with the DefaultTableModel
        tickeTable = new JTable(modelTable);
        tickeTable.setRowHeight(60);
        
        // Create scroll pane for the ticket table
        JScrollPane scrollPaneTable = new JScrollPane(tickeTable);
        
        // Add components to the center panel, and add to main panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(labelPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPaneTable, BorderLayout.CENTER);

        asViewPanel.add(centerPanel, BorderLayout.CENTER);

        return asViewPanel;
    }
    
    // Create a view page
    public JPanel windowView() {
        
        // Create a view panel
        JPanel viewPanel = new JPanel();
        viewLabel = new JLabel("Your Ticket Information");

        JPanel topPanel = new JPanel();
        GUIStyle.styleLabel(viewLabel);
        
        // Create and style text area
        viewText = new JTextArea();
        viewText.setLineWrap(true);
        viewText.setWrapStyleWord(true);
        GUIStyle.styleTextArea(viewText);
        
        // Add scroll pane to text area for text to be displayed properly
        JScrollPane scrollPane = new JScrollPane(viewText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        topPanel.add(viewLabel, BorderLayout.CENTER);
        
        // Disable editing text, remove background colour
        viewText.setEditable(false);
        viewText.setOpaque(false);
        
        // Add components to the view panel
        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(topPanel, BorderLayout.NORTH);
        viewPanel.add(scrollPane, BorderLayout.CENTER);

        return viewPanel;
    }
    
    // Create "Create" page for customers & students
    public JPanel windowCreate() {
        
        // Create "Create" panel
        JPanel createPanel = new JPanel(new BorderLayout());
        JPanel topEntry = new JPanel();
        
        // Create a label to set the title for page
        createLabel = new JLabel("Please Enter Ticket Description");
        GUIStyle.styleLabel(createLabel);
        topEntry.add(createLabel, BorderLayout.CENTER);
        
        // Create and style text area and set the word limit per line
        createText = new JTextArea(""); 
        createText.setRows(5);
        createText.setLineWrap(true);
        createText.setWrapStyleWord(true);
        createText.setBorder(new LineBorder(Color.black, 1));
        JScrollPane scrollPane = new JScrollPane(createText);
        createText.setBorder(new LineBorder(Color.BLACK));
        GUIStyle.styleTextArea(createText);
        
        // Create a button for user to interact with
        createButton = new JButton("Create Ticket");
        GUIStyle.styleButton(createButton);

        // Add components for Create window
        createPanel.add(topEntry, BorderLayout.NORTH);
        createPanel.add(scrollPane, BorderLayout.CENTER);
        createPanel.add(createButton, BorderLayout.SOUTH);
        return createPanel;
    }
    
    // Create update page
    public JPanel windowUpdate() {
        
        // Create update panel
        JPanel updatePanel = new JPanel(new BorderLayout());
        JPanel topEntry = new JPanel();

        // Create label for page title
        updateLabel = new JLabel("Please Edit Your Ticket Details");
        GUIStyle.styleLabel(updateLabel);
        topEntry.add(updateLabel, BorderLayout.CENTER);
        
        // Create and style update text area
        updateText = new JTextArea("");
        updateText.setRows(5);
        updateText.setLineWrap(true);
        updateText.setWrapStyleWord(true);
        updateText.setBorder(new LineBorder(Color.BLACK, 1));
        JScrollPane scrollPane = new JScrollPane(updateText);
        GUIStyle.styleTextArea(updateText);
        
        // Create update button for user to interact with.
        updateButton = new JButton("Update Ticket");
        GUIStyle.styleButton(updateButton);

        // Add components for Create window
        updatePanel.add(topEntry, BorderLayout.NORTH);
        updatePanel.add(scrollPane, BorderLayout.CENTER);
        updatePanel.add(updateButton, BorderLayout.SOUTH);

        return updatePanel;
    }
}
