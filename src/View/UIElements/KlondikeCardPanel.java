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
            JLabel card = new JLabel(cardBackResized);
            card.setBounds(0, (10-i)*20, cardBackResized.getIconWidth(), cardBackResized.getIconHeight());
            add(card, i);

            MouseDragListener mouseDragListener = new MouseDragListener(card);
            card.addMouseMotionListener(mouseDragListener);
            card.addMouseListener(mouseDragListener);

            cards.add(card);
        }
    }

    class MouseDragListener extends MouseAdapter implements MouseMotionListener {
        Point startPoint;
        JComponent dragObject;

        public MouseDragListener(JComponent dragObject) {
            this.dragObject = dragObject;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            startPoint = SwingUtilities.convertPoint(dragObject, e.getPoint(), dragObject.getParent());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point location = SwingUtilities.convertPoint(dragObject, e.getPoint(), dragObject.getParent());

            if (dragObject.getParent().getBounds().contains(location)) {
                Point newLocation = dragObject.getLocation();
                newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);

//                newLocation.x = Math.max(newLocation.x, 0);
//                newLocation.y = Math.max(newLocation.y, 0);
//                newLocation.x = Math.min(newLocation.x, dragObject.getParent().getWidth() - dragObject.getWidth());
//                newLocation.y = Math.min(newLocation.y, dragObject.getParent().getHeight() - dragObject.getHeight());

                dragObject.setLocation(newLocation);
                startPoint = location;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            startPoint = null;
        }
    }
}
