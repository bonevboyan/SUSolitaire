package View.UIElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KlondikeCardPanel extends JLayeredPane {
    public KlondikeCardPanel() {
        setBackground(new Color(0, 81, 0));

        ArrayList<JLabel> cards = new ArrayList<>(); //list of cards for test purposes
        for (int i = 0; i < 10; i++) {
            JCard card = new JCard(new Point(0, (10 - i) * 20), null);

            //add drag listener
            card.setMouseListeners(mouseDragListener(card));

            add(card, i);
            cards.add(card);
        }
    }

    //listener to make card draggable
    MouseAdapter mouseDragListener(JCard card) {
        return new MouseAdapter() {
            Point startPoint;

            //set initial coordinates
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = SwingUtilities.convertPoint(card, e.getPoint(), card.getParent());
                moveToFront(card);
            }

            //move card when dragged
            @Override
            public void mouseDragged(MouseEvent e) {
                Point location = SwingUtilities.convertPoint(card, e.getPoint(), card.getParent());

                if (card.getParent().getBounds().contains(location)) {
                    Point newLocation = card.getLocation();
                    newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);

//                    newLocation.x = Math.max(newLocation.x, 0);
//                    newLocation.y = Math.max(newLocation.y, 0);
//                    newLocation.x = Math.min(newLocation.x, dragObject.getParent().getWidth() - dragObject.getWidth());
//                    newLocation.y = Math.min(newLocation.y, dragObject.getParent().getHeight() - dragObject.getHeight());

                    card.setLocation(newLocation);
                    startPoint = location;
                }
            }

            //reset on release
            @Override
            public void mouseReleased(MouseEvent e) {
                startPoint = null;
            }
        };
    }
}
