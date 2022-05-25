package View.UIElements;

import Model.GameObjects.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class JCard extends JLabel {
    private Card card;
    private Point coordinates;
    private ImageIcon cardBack;
    private ImageIcon cardBackResized;

    public JCard(Card card, Point coordinates) {
        this.card = card;
        this.coordinates = coordinates;

        //resize icon to fit panel
        cardBack = new ImageIcon("src\\assets\\card_back_test.png");
        cardBackResized = new ImageIcon(cardBack.getImage()
                .getScaledInstance(cardBack.getIconWidth() / 5, cardBack.getIconHeight() / 5, Image.SCALE_DEFAULT));

        setIcon(cardBackResized);
        setBounds(coordinates.x, coordinates.y, cardBackResized.getIconWidth(), cardBackResized.getIconHeight());

        //add drag listeners
        MouseDragListener mouseDragListener = new MouseDragListener(this);
        addMouseMotionListener(mouseDragListener);
        addMouseListener(mouseDragListener);
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
        setLocation(coordinates);
    }
}

//listener to make cards draggable
class MouseDragListener extends MouseAdapter implements MouseMotionListener {
    Point startPoint;
    JCard card;

    public MouseDragListener(JCard card) {
        this.card = card;
    }

    //set initial coordinates
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = SwingUtilities.convertPoint(card, e.getPoint(), card.getParent());
    }

    //move card when dragged
    @Override
    public void mouseDragged(MouseEvent e) {
        Point location = SwingUtilities.convertPoint(card, e.getPoint(), card.getParent());

        if (card.getParent().getBounds().contains(location)) {
            Point newLocation = card.getLocation();
            newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);

//                newLocation.x = Math.max(newLocation.x, 0);
//                newLocation.y = Math.max(newLocation.y, 0);
//                newLocation.x = Math.min(newLocation.x, dragObject.getParent().getWidth() - dragObject.getWidth());
//                newLocation.y = Math.min(newLocation.y, dragObject.getParent().getHeight() - dragObject.getHeight());

            card.setCoordinates(newLocation);
            startPoint = location;
        }
    }

    //reset on release
    @Override
    public void mouseReleased(MouseEvent e) {
        startPoint = null;
    }
}
