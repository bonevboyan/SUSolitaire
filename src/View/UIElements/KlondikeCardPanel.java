package View.UIElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KlondikeCardPanel extends JLayeredPane {
    public KlondikeCardPanel() {
        setBackground(new Color(0, 81, 0));

        ImageIcon cardBack = new ImageIcon("src\\assets\\card_back_test.png");
        ImageIcon cardBackResized = new ImageIcon(cardBack.getImage()
                .getScaledInstance(cardBack.getIconWidth() / 5, cardBack.getIconHeight() / 5, Image.SCALE_DEFAULT));

        ArrayList<JLabel> cards = new ArrayList<>(); //list of cards for test purposes
        for (int i = 0; i < 10; i++) {
            JCard card = new JCard(null, new Point(0, (10-i)*20));
            add(card, i);
            cards.add(card);
        }
    }
}
