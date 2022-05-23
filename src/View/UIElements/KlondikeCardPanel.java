package View.UIElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class KlondikeCardPanel extends JLayeredPane {
    public KlondikeCardPanel() {
        setBackground(new Color(0, 81, 0));

        ImageIcon cardBack = new ImageIcon("src\\assets\\card_back_test.png");
        ImageIcon cardBackResized = new ImageIcon(cardBack.getImage()
                .getScaledInstance(cardBack.getIconWidth() / 5, cardBack.getIconHeight() / 5, Image.SCALE_DEFAULT));

        ArrayList<JLabel> cards = new ArrayList<>(); //list of cards for test purposes
        for (int i = 0; i < 10; i++) {
            JLabel card = new JLabel(cardBackResized);
            card.setBounds(0, (10-i)*20, cardBackResized.getIconWidth(), cardBackResized.getIconHeight());
            add(card, i);

            card.addMouseMotionListener(new MouseMotionAdapter() {
                Point anchor;

                //@Override
                //public void mousePressed(MouseEvent e) {
                    //anchor = e.getPoint();
                    //card.setBounds(e.getPoint().x, e.getPoint().y, cardBackResized.getIconWidth(), cardBackResized.getIconHeight());
//                    repaint();
                    //System.out.println(cards.indexOf(card));
                //}

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (anchor != null)
                        card.setBounds(getMousePosition().x - anchor.x, getMousePosition().y - anchor.y, cardBackResized.getIconWidth(), cardBackResized.getIconHeight());
                    anchor = e.getPoint();
                    System.out.println("aaaaaaaaa");
                }

                //@Override
                //public void mouseReleased(MouseEvent e) {
                    //card.setBounds(getMousePosition().x - anchor.x, getMousePosition().y - anchor.y, cardBackResized.getIconWidth(), cardBackResized.getIconHeight());
                //}
            });

            cards.add(card);
        }
    }
}
