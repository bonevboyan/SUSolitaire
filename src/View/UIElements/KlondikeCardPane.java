package View.UIElements;

import Model.GameObjects.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KlondikeCardPane extends JLayeredPane {
    ArrayList<PilePanel> pilePanels;
    ArrayList<FoundationPanel> foundationPanels;
    DownStockPanel downStockPanel;
    UpStockPanel upStockPanel;

    Field field;

    public KlondikeCardPane() {
        setBackground(new Color(0, 81, 0));

        //panels for coordination of cards in piles
        pilePanels = new ArrayList<>();
        foundationPanels = new ArrayList<>();

        field = new Field();

        for (int i = 0; i < 7; i++) {
            PilePanel pilePanel = new PilePanel(new Point(i * 180, 250));

            pilePanels.add(pilePanel);
            add(pilePanel);

            var pile = field.getPiles().get(i);

            int counter = 0;
            for (int j = 0; j < pile.getDownCards().size(); j++) {
                JCard card = new JCard(new Point(pilePanel.getX(), pilePanel.getY() + counter * 20), pile.getDownCards().get(j), pilePanel);
                add(card);
                moveCard(card, card.getLocation());
                counter++;

                card.setMouseListeners(mouseDragListener(card));
            }

            for (int j = 0; j < pile.getUpCards().size(); j++) {
                JCard card = new JCard(new Point(pilePanel.getX(), pilePanel.getY() + counter * 20), pile.getUpCards().get(j), pilePanel);
                add(card);
                moveCard(card, card.getLocation());
                counter++;

                card.setMouseListeners(mouseDragListener(card));
            }
        }

        for (int i = 0; i < 4; i++) {
            FoundationPanel foundationPanel = new FoundationPanel(new Point(540 + i * 180, 20));

            foundationPanels.add(foundationPanel);
            add(foundationPanel);
        }

        downStockPanel = new DownStockPanel(new Point(0, 20));
        add(downStockPanel);

        var downStock = field.getDownStock();

        for (int i = 0; i < downStock.size(); i++) {
            JCard card = new JCard(new Point(downStockPanel.getX() + 10, downStockPanel.getY() + 10), downStock.get(i), downStockPanel);
            card.setLocation(downStockPanel.getX() + 10, downStockPanel.getY() + 10);
            //downStockPanel.add(card);
            add(card);
            moveToFront(card);
            card.setMouseListeners(openCardListenerListener(card));
        }

        upStockPanel = new UpStockPanel(new Point(180, 20));
        add(upStockPanel);

    }

    MouseAdapter openCardListenerListener(JCard card) {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                card.flipCard();
//                downStockPanel.remove(card);
//                upStockPanel.add(card);
                moveCard(card, new Point(190, 30));

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                card.removeMouseListener(this);
                card.setMouseListeners(mouseDragListener(card));
            }
        };
    }

    //listener to make card draggable
    MouseAdapter mouseDragListener(JCard card) {
        return new MouseAdapter() {
            Point originalPosition;
            Point startPoint;

            //set initial coordinates
            @Override
            public void mousePressed(MouseEvent e) {
                if (!card.isFlipped()) {
                    return;
                }

                originalPosition = card.getLocation();
                startPoint = SwingUtilities.convertPoint(card, e.getPoint(), card.getParent());
                moveToFront(card);
            }

            //move card when dragged
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!card.isFlipped()) return;

                Point location = SwingUtilities.convertPoint(card, e.getPoint(), card.getParent());

                if (card.getParent().getBounds().contains(location)) {
                    Point newLocation = card.getLocation();
                    newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);

                    card.setLocation(newLocation);
                    startPoint = location;
                }
            }

            //reset on release
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!card.isFlipped()) {
                    return;
                }

                var startLocation = getPanel(originalPosition);
                var endLocation = getPanel(startPoint);

                if (startLocation == endLocation) {
                    card.setLocation(originalPosition);
                    return;
                }

                if (!moveCard(card, startPoint))
                    card.setLocation(originalPosition);
                else
                    removeCardFromPastPile(originalPosition);

                startPoint = null;
            }
        };
    }

    //finds the index of the pile int the list by given coordinates of the pile
    private JPanel getPanel(Point location) {
        for (PilePanel pilePanel : pilePanels) {
            if (pilePanel.getBounds().contains(location)) {
                return pilePanel;
            }
        }

        for (FoundationPanel foundationPanel : foundationPanels) {
            if (foundationPanel.getBounds().contains(location)) {
                return foundationPanel;
            }
        }

        if (upStockPanel.getBounds().contains(location)) {
            return upStockPanel;
        }

        return null;
    }

    //moves card to the respective pile, returns boolean whether the move was successful
    private boolean moveCard(JCard card, Point location) {
        var dropLocation = getPanel(location);

        if (dropLocation == null)
            return false;

        //PilePanel pilePanel = pilePanels.get(pileIndex);
        if (dropLocation instanceof PilePanel) {
            ((PilePanel) dropLocation).incrementCardAmount(1);
            card.setLocation(dropLocation.getX() + 10, dropLocation.getY() + ((PilePanel) dropLocation).getCardAmount() * 20);
        } else {
            card.setLocation(dropLocation.getX() + 10, dropLocation.getY() + 10);
        }

        moveToFront(card);
        return true;
    }

    private void removeCardFromPastPile(Point pastLocation) {
        var dropLocation = getPanel(pastLocation);

        if (dropLocation != null) {
            if (dropLocation instanceof PilePanel) {
                ((PilePanel) dropLocation).incrementCardAmount(-1);
            }
        }
    }
}

/*
Ideas:
-responsive pile coordination + responsive card coordination inside pile
-dynamic pile height
 */
