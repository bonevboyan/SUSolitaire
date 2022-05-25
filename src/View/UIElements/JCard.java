package View.UIElements;

import Model.GameObjects.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class JCard extends JLabel {
    private Card card;
    private ImageIcon cardBack;
    private ImageIcon cardBackResized;

    public JCard(Point coordinates, Card card) {
        this.card = card;

        //resize icon to fit panel
        cardBack = new ImageIcon("src\\assets\\card_back_test.png");
        cardBackResized = new ImageIcon(cardBack.getImage()
                .getScaledInstance(cardBack.getIconWidth() / 5, cardBack.getIconHeight() / 5, Image.SCALE_DEFAULT));
        setIcon(cardBackResized);

        setBounds(coordinates.x, coordinates.y, cardBackResized.getIconWidth(), cardBackResized.getIconHeight());
    }

    public void setMouseListeners(MouseAdapter listener) {
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
}