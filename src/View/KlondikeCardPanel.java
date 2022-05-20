package View;

import javax.swing.*;
import java.awt.*;

public class KlondikeCardPanel extends JLayeredPane {
    public KlondikeCardPanel() {
        setBackground(new Color(0, 81, 0));

        ImageIcon cardBack = new ImageIcon("src\\assets\\card_back_test.png");
        ImageIcon cardBackResized = new ImageIcon(cardBack.getImage()
                .getScaledInstance(cardBack.getIconWidth() / 5, cardBack.getIconHeight() / 5, Image.SCALE_DEFAULT));

        for (int i = 0; i < 10; i++) {
            JLabel card = new JLabel(cardBackResized);
            card.setBounds(0, (10-i)*20, cardBackResized.getIconWidth(), cardBackResized.getIconHeight());
            add(card, i);
        }
    }
}
