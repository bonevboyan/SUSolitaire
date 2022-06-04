package View.Layouts;

import Model.Common.ScoreEventListener;
import Model.Common.TimerEventListener;
import View.UIElements.KlondikeCardPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KlondikeScreen extends JPanel {
    private JButton exitButton;
    private JButton undoButton;

    private JButton resetButton;

    private JLabel scoreLabel;
    private JLabel timerLabel;

    private KlondikeCardPane cardPane;

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

//        undoButton = new JButton("Undo");
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 0;
//        add(undoButton, gridBagConstraints);


        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        add(resetButton, gridBagConstraints);

        scoreLabel = new JLabel("Score 0");
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("", Font.BOLD, 30));
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(scoreLabel, gridBagConstraints);

        timerLabel = new JLabel("0:00");
        timerLabel.setForeground(Color.white);
        timerLabel.setFont(new Font("", Font.BOLD, 30));
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        add(timerLabel, gridBagConstraints);

        resetGame();
    }

    public void exitButton(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }

    public void resetGame() {
        //resets the game and re-adds the game pane
        if (cardPane != null)
            remove(cardPane);

        cardPane = new KlondikeCardPane();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(20, 20, 50, 20);

        add(cardPane, gridBagConstraints);

        scoreLabel.setText("Score 0");
        cardPane.getField().subscribeToScoreEvent(new ScoreEventListener() {
            @Override
            public void OnEvent(int score) {
                scoreLabel.setText("Score " + score);
            }
        });


        cardPane.getField().subscribeToTimerEvent(new TimerEventListener() {
            @Override
            public void OnEvent(long seconds) {
                timerLabel.setText(seconds / 60 + ":" + String.format("%02d", seconds % 60));
            }
        });

        validate();
    }
}