package View.Layouts;

import Model.Database.Models.Score;
import View.UIElements.JPlaceholderTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

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
        title.setFont(new Font("Serif", Font.BOLD, 120));

        JLabel topScoresLabel = new JLabel("Top Scores:");
        topScoresLabel.setForeground(Color.white);
        topScoresLabel.setFont(new Font("", Font.BOLD, 30));

        setBackground(new Color(0, 81, 0));

        usernameField = new JPlaceholderTextField(25);
        topScores = new JTable();
        topScores.setDefaultEditor(Object.class, null);

        //initialize buttons
        playButton = new JButton("Play");
        playButton.setFont(new Font("", Font.BOLD, 50));
        playButton.setPreferredSize(new Dimension(500, 100));
        scoresButton = new JButton("Show Leaderboard");
        scoresButton.setPreferredSize(new Dimension(200, 40));
        scoresButton.setFont(new Font("", Font.BOLD, 15));
        creditsButton = new JButton("Credits");
        creditsButton.setPreferredSize(new Dimension(200, 40));
        creditsButton.setFont(new Font("", Font.BOLD, 15));

        // space between fields
        Insets fieldsInset = new Insets(0, 20, 10, 0);
        // space between buttons
        Insets buttonInset = new Insets(20, 20, 0, 0);

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

        //top scores label
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;

        add(topScoresLabel, gridBagConstraints);

        //username field
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        usernameField.setPlaceholder("Username...");
        usernameField.setFont(new Font(usernameField.getFont().getName(), usernameField.getFont().getStyle(), 30));

        //autofill username from past games
        try (Scanner scanner = new Scanner(new File("src/assets/username.txt"))) {
            usernameField.setText(scanner.nextLine());
        } catch (FileNotFoundException ignored) {}

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                try (Scanner scanner = new Scanner(new File("src/assets/username.txt"))) {
                    usernameField.setText(scanner.nextLine());
                } catch (Exception ignored) {
                }
            }
        });


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

    public void topScores(List<Score> scores) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) topScores.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(userTableColumn);

        for (Score score : scores) {
            defaultTableModel.addRow(new String[]{score.getUsername(), String.valueOf(score.getPoints())});
        }
    }
}
