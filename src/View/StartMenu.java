package View;

import View.UIElements.JPlaceholderTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel {
    private JPlaceholderTextField usernameField;

    private JButton playButton;
    private JButton scoresButton;
    private JButton creditsButton;

    private JTable topScores;
    private String[] userTableColumn = {"FIRST NAME", "SCORE"};

    public StartMenu() {
        JLabel title = new JLabel("SUSolitaire");
        title.setForeground(Color.white);
        title.setFont(new Font("", Font.BOLD, 30));

        setBackground(new Color(0, 81, 0));

        usernameField = new JPlaceholderTextField(25);
        topScores = new JTable();

        //initialize buttons
        playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(500, 100));
        scoresButton = new JButton("View All Scores");
        scoresButton.setPreferredSize(new Dimension(200, 40));
        creditsButton = new JButton("Credits");
        creditsButton.setPreferredSize(new Dimension(200, 40));

        // space between fields
        Insets fieldsInset = new Insets(0, 20, 10, 0);
        // space between buttons
        Insets buttonInset = new Insets(20,20,0,0);

        // uses Grid Bag Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = fieldsInset;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        //title
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;

        add(title, gridBagConstraints);

        //username field
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        usernameField.setPlaceholder("Username...");
        usernameField.setFont(new Font(usernameField.getFont().getName(), usernameField.getFont().getStyle(), 30));

        add(usernameField, gridBagConstraints);

        //play button
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = buttonInset;

        add(playButton, gridBagConstraints);

        //scores button
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = buttonInset;

        add(scoresButton, gridBagConstraints);

        //credits button
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = buttonInset;

        add(creditsButton, gridBagConstraints);

        //top scores
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(topScores, gridBagConstraints);

    }

    public String getUsername() {
        return usernameField.getText();
    }


    // event listeners for buttons
    public void submitUsers(ActionListener actionListener) {
            playButton.addActionListener(actionListener);
    }

    public void viewUsers(ActionListener actionListener) {
        scoresButton.addActionListener(actionListener);
    }

    public void viewCredits(ActionListener actionListener) {
        creditsButton.addActionListener(actionListener);
    }

    /*
    |           |
    | _________ |
    |___  |  ___|
    |  |  |  |  |
    |  |  |  |  |
    */

    public void topScores(Object[] objects) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) topScores.getModel();
        defaultTableModel.setColumnIdentifiers(userTableColumn);
        int i = 0;
        while (i < objects.length) {
            String row = objects[i].toString().trim();
            String[] rows = row.split(",");
            defaultTableModel.addRow(rows);
            i++;
        }
    }

    // reset fields
    public void reset(boolean bln) {
        if (bln) {
            usernameField.setText("");
        }
    }
}
