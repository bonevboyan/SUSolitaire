package View.Layouts;

import View.UIElements.KlondikeCardPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KlondikeScreen extends JPanel {
    private JButton exitButton;
    private JButton undoButton;

    private JButton minusButton;
    private JButton plusButton;

    private JLabel score;

    public KlondikeScreen() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setBackground(new Color(0, 81, 0));
        setLayout(new GridBagLayout());

        gridBagConstraints.fill = GridBagConstraints.NONE;
        Insets defaultInsets = new Insets(5, 20, 5, 20);

        ImageIcon exitIcon = new ImageIcon("src/assets/X.png");
        ImageIcon exitIcon_selected = new ImageIcon("src/assets/X_selected.png");

        exitButton = new JButton(exitIcon);
        exitButton.setBorderPainted(false);
        exitButton.setBorder(null);
        exitButton.setRolloverIcon(exitIcon_selected);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = defaultInsets;
        add(exitButton, gridBagConstraints);

        undoButton = new JButton("Undo");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(undoButton, gridBagConstraints);

        minusButton = new JButton("-");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(minusButton, gridBagConstraints);

        score = new JLabel("0");
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        add(score, gridBagConstraints);

        plusButton = new JButton("+");
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        add(plusButton, gridBagConstraints);

        KlondikeCardPane cardPanel = new KlondikeCardPane();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(20, 20, 50, 20);
        add(cardPanel, gridBagConstraints);
    }

    public void exitButton(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }

    public void minusButton(ActionListener actionListener) {
        minusButton.addActionListener(actionListener);
    }

    public void plusButton(ActionListener actionListener) {
        plusButton.addActionListener(actionListener);
    }

    public void setScore(int score) {
        this.score.setText(String.valueOf(score));
        this.revalidate();
        this.repaint();
    }
}