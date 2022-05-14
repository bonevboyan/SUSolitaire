package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KlondikeScreen extends JPanel {
    private JButton exitButton;

    public KlondikeScreen() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setBackground(new Color(0, 81, 0));
        setLayout(new GridBagLayout());

        gridBagConstraints.fill = GridBagConstraints.NONE;
        Insets defaultInsets = new Insets(5, 5, 5, 5);

        exitButton = new JButton("Exit");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = defaultInsets;
        add(exitButton, gridBagConstraints);
    }

    public void exitButton(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }
}
