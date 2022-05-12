package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreditsScreen extends JPanel {
    private JButton backButton;
    private JLabel title;
    private JLabel creditsText;

    public CreditsScreen() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setBackground(new Color(0, 81, 0));
        setLayout(new GridBagLayout());

        gridBagConstraints.fill = GridBagConstraints.NONE;
        Insets defaultInsets = new Insets(0, 0, 10, 0);

        title = new JLabel("Credits");
        title.setFont(new Font("", Font.ITALIC, 40));
        title.setForeground(Color.white);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(0, 0, 30, 0);
        add(title, gridBagConstraints);

        creditsText = new JLabel("<html>Did you ever hear the tragedy of Darth Plagueis The Wise? I thought not. " +
                "It's not a story the Jedi would tell you. It's a Sith legend. Darth Plagueis was a Dark Lord of the Sith," +
                " so powerful and so wise he could use the Force to influence the midichlorians to create life… He had " +
                "such a knowledge of the dark side that he could even keep the ones he cared about from dying. The dark" +
                " side of the Force is a pathway to many abilities some consider to be unnatural. He became so powerful… " +
                "the only thing he was afraid of was losing his power, which eventually, of course, he did. Unfortunately," +
                " he taught his apprentice everything he knew, then his apprentice killed him in his sleep. Ironic. " +
                "He could save others from death, but not himself.</html>");
        creditsText.setFont(new Font("", Font.ITALIC, 20));
        creditsText.setForeground(Color.white);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 900;
        add(creditsText, gridBagConstraints);

        backButton = new JButton("Go Back");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 0;
        add(backButton, gridBagConstraints);
    }

    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}