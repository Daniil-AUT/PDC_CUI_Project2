package pdc_cui_project2;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;

/*
 *
 * @author Daniil
 */
public final class TicketView extends JPanel implements Page {

    protected CardLayout cardLayout;
    protected JPanel cardPanel;
    protected JLabel updateLabel;
    protected JTextArea updateText;
    protected JButton updateButton;
    protected JLabel replyLabel;
    protected JButton replyButton;
    protected JTextArea replyText;
    protected JButton asViewButton;
    protected JLabel viewLabel;
    protected JTextArea viewText;
    protected JLabel createLabel;
    protected JTextArea createText;
    protected JButton createButton;
    protected JButton backButton;
    protected String currentView;
    protected JLabel idLabel;
    protected JTextField idField;
    protected JTable tickeTable;
    protected DefaultTableModel modelTable;

    public TicketView() {
        createComponents();
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

    public JPanel windowAsView() {
        JPanel asViewPanel = new JPanel(new BorderLayout());

        JLabel viewTicketslabel = new JLabel("View User Tickets", SwingConstants.CENTER);
        JPanel labelPanel = new JPanel(new BorderLayout());
        GUIStyle.styleLabel(viewTicketslabel);
        labelPanel.add(viewTicketslabel, BorderLayout.CENTER);

        // Column names
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
        // Set preferred column widths
        
        JScrollPane scrollPaneTable = new JScrollPane(tickeTable);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(labelPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPaneTable, BorderLayout.CENTER);

        asViewPanel.add(centerPanel, BorderLayout.CENTER);

        return asViewPanel;
    }

    public JPanel windowView() {
        JPanel viewPanel = new JPanel();
        viewLabel = new JLabel("Your Ticket Information");

        JPanel topPanel = new JPanel();
        GUIStyle.styleLabel(viewLabel);

        viewText = new JTextArea();
        viewText.setLineWrap(true);
        viewText.setWrapStyleWord(true);
        GUIStyle.styleTextArea(viewText);
        // Adjust the number of rows and columns based on your preference
        JScrollPane scrollPane = new JScrollPane(viewText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        topPanel.add(viewLabel, BorderLayout.CENTER);

        viewText.setEditable(false);
        viewText.setOpaque(false);

        viewPanel.setLayout(new BorderLayout());
        viewPanel.add(topPanel, BorderLayout.NORTH);
        viewPanel.add(scrollPane, BorderLayout.CENTER);

        // Add components for View window
        return viewPanel;
    }

    public JPanel windowCreate() {
        JPanel createPanel = new JPanel(new BorderLayout());
        JPanel topEntry = new JPanel();

        createLabel = new JLabel("Please Enter Ticket Description");
        GUIStyle.styleLabel(createLabel);
        topEntry.add(createLabel, BorderLayout.CENTER);

        createText = new JTextArea("");  // Use a different variable for createText
        createText.setRows(5);
        createText.setLineWrap(true);
        createText.setWrapStyleWord(true);
        createText.setBorder(new LineBorder(Color.black, 1));
        JScrollPane scrollPane = new JScrollPane(createText);
        createText.setBorder(new LineBorder(Color.BLACK));
        GUIStyle.styleTextArea(createText);

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
        topEntry.add(updateLabel, BorderLayout.CENTER);

        updateText = new JTextArea("");
        updateText.setRows(5);
        updateText.setLineWrap(true);
        updateText.setWrapStyleWord(true);
        updateText.setBorder(new LineBorder(Color.BLACK, 1));
        JScrollPane scrollPane = new JScrollPane(updateText);
        GUIStyle.styleTextArea(updateText);

        updateButton = new JButton("Update Ticket");
        GUIStyle.styleButton(updateButton);

        // Add components for Create window
        updatePanel.add(topEntry, BorderLayout.NORTH);
        updatePanel.add(scrollPane, BorderLayout.CENTER);
        updatePanel.add(updateButton, BorderLayout.SOUTH);

        return updatePanel;
    }
}
