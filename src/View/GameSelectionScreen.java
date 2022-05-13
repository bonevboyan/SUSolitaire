package View;

import View.UIElements.GameSelectionButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameSelectionScreen extends JPanel {

    private ArrayList<GameSelectionButton> gamesButtonsList;
    private JPanel gamesButtonsPanel;
    private JScrollPane gamesScrollPane;

    private JButton backButton;



    public GameSelectionScreen() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setBackground(new Color(0, 81, 0));
        setLayout(new GridBagLayout());

        gamesButtonsList = new ArrayList<GameSelectionButton>();
        gamesButtonsList.add(new GameSelectionButton("Pyramids", Color.yellow));
        gamesButtonsList.add(new GameSelectionButton("Pyramids", Color.yellow));
        gamesButtonsList.add(new GameSelectionButton("Pyramids", Color.yellow));
        gamesButtonsList.add(new GameSelectionButton("Pyramids", Color.yellow));

        gamesButtonsPanel = new JPanel();
        gamesButtonsPanel.setLayout(new GridBagLayout());
        gamesButtonsPanel.setBackground(new Color(0, 81, 0));
        gamesButtonsPanel.setPreferredSize(new Dimension((GameSelectionButton.WIDTH + 50) * gamesButtonsList.size(), GameSelectionButton.HEIGHT + 100));
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < gamesButtonsList.size(); i++) {
            gridBagConstraints.gridx = i;
            gamesButtonsPanel.add(gamesButtonsList.get(i), gridBagConstraints);
        }

        Insets defaultInsets = new Insets(5, 5, 5, 5);

        backButton = new JButton("Go Back");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = defaultInsets;
        add(backButton, gridBagConstraints);

        gamesScrollPane = new JScrollPane(gamesButtonsPanel);
        gamesScrollPane.setBorder(BorderFactory.createEmptyBorder());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = defaultInsets;
        //gamesScrollPane.setPreferredSize(new Dimension(1000, gamesButtonsPanel.getHeight()));
        add(gamesScrollPane, gridBagConstraints);



    }

    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

}
