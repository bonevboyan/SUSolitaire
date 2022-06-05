package View.Layouts;

import Model.Database.Models.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class UserDetails extends JPanel {

    // Table for user data
    private JTable userTable;
    // table column
    private String[] userTableColumn = {"FIRST NAME", "SCORE"};

    // back button
    private JButton backButton;

    public UserDetails() {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(new Color(0, 81, 0), 30));
        // toolbar for buttons
        JToolBar toolBar = new JToolBar();

        // users table
        userTable = new JTable();
        userTable.setDefaultEditor(Object.class, null);
        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(userTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        backButton = new JButton("Go Back");
        toolBar.setFloatable(false);
        add(toolBar);
        toolBar.add(backButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);

    }

    // gets users from database and loads to table
    public void getUsers(List<Score> scores) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) userTable.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(userTableColumn);

        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            defaultTableModel.addRow(new String[] {score.getUsername(), String.valueOf(score.getPoints())});
        }
    }

    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
