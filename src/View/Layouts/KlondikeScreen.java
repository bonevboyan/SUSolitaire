package View.Layouts;

import Model.Common.EndGameListener;
import Model.Common.ScoreEventListener;
import Model.Common.TimerEventListener;
import View.Sounds.Sound;
import View.UIElements.KlondikeCardPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class KlondikeScreen extends JPanel {
    private JButton exitButton;
    private JButton undoButton;
    private JButton resetButton;
    private JButton saveButton;

    private JLabel scoreLabel;
    private JLabel timerLabel;

    private KlondikeCardPane cardPane;

    private Sound player;

    private Timer timer;

    public KlondikeScreen() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setBackground(new Color(0, 81, 0));
        setLayout(new GridBagLayout());

        //initialize sound player to play sounds
        player = new Sound();

        //defaults
        gridBagConstraints.fill = GridBagConstraints.NONE;
        Insets defaultInsets = new Insets(5, 20, 5, 20);

        //add button to exit to the game selection screen
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

//        unfinished undo button:
//        undoButton = new JButton("Undo");
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 0;
//        add(undoButton, gridBagConstraints);

        //add button to restart the game
        ImageIcon resetIcon = new ImageIcon("src/assets/reset.png");
        ImageIcon resetIcon_selected  = new ImageIcon("src/assets/reset_selected.png");

        resetButton = new JButton("Reset");
        resetButton = new JButton(resetIcon);
        resetButton.setBorderPainted(false);
        resetButton.setBorder(null);
        resetButton.setRolloverIcon(resetIcon_selected);

        resetButton.addActionListener(e -> {player.playButtonClick(); resetGame();});
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(resetButton, gridBagConstraints);

        //add label to keep track of score
        scoreLabel = new JLabel("Score 0");
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("", Font.BOLD, 30));
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        add(scoreLabel, gridBagConstraints);

        //add label to show passed time
        timerLabel = new JLabel("0:00");
        timerLabel.setForeground(Color.white);
        timerLabel.setFont(new Font("", Font.BOLD, 30));
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        add(timerLabel, gridBagConstraints);

        //reset the game when the screen is showed
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (timerLabel.getText().equals("0:00")) //makes possible to exit and return to the current game
                    resetGame();
            }
        });

        saveButton = new JButton();
    }

    //add event listener to exit button
    public void exitButton(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }

    public void resetGame() {
        //resets the game and re-adds the game pane
        if (cardPane != null) {
            remove(cardPane);
            cardPane.getField().destroyTimers();
        }

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

        //subscribe to all events
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

        cardPane.getField().subscribeToEndGameEvent(new EndGameListener() {
            //event when the game is finished
            @Override
            public void OnEvent() {
                player.playVictory();
                cardPane.getField().destroyTimers();

                Object[] options = {"Exit", "Restart"};
                String endMessage = "You win! \n" +
                        "Final score: " + scoreLabel.getText() + '\n' +
                        "Time: " + timerLabel.getText();

                //show victory popup with given time to finish the card move
                timer = new Timer(100, e -> {
                    int option = JOptionPane.showOptionDialog(cardPane,
                            endMessage,
                            "Game Won!",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,     //do not use a custom Icon
                            options,  //the titles of buttons
                            options[0]); //default button title

                    switch (option) {
                        case 0, JOptionPane.CLOSED_OPTION -> {
                            saveButton.doClick();
                            exitButton.doClick();
                        }
                        case 1 -> {
                            saveButton.doClick();
                            resetButton.doClick();
                        }
                    }
                });

                timer.start();
                timer.setRepeats(false);
            }
        });

        validate();
    }

    public int getScore() {
        return Integer.parseInt(this.scoreLabel.getText().split(" ")[1]);
    }

    //invisible save button for its action listener for the end of the game
    public void saveResult(ActionListener actionListener) {
        saveButton.addActionListener(actionListener);
    }
}