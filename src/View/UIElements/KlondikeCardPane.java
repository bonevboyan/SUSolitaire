package View.UIElements;

import Model.GameObjects.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KlondikeCardPane extends JLayeredPane {
    ArrayList<PilePanel> pilePanels;

    public KlondikeCardPane() {
        setBackground(new Color(0, 81, 0));

        //panels for coordination of cards in piles
        pilePanels = new ArrayList<>();

        Field field = new Field();
        
        for (int i = 0; i < 7; i++) {
            PilePanel pilePanel = new PilePanel(new Point(i * 180, 200));
            //pilePanel.setCardAmount(i + 1);

            pilePanels.add(pilePanel);
            add(pilePanel);

            var pile = field.getPiles().get(i);

            int counter = 0;
            for (int j = 0; j < pile.getDownCards().size(); j++) {
                JCard card = new JCard(new Point(pilePanel.getX(), pilePanel.getY() + counter * 20), pile.getDownCards().get(j));
                add(card);
                moveCard(card, card.getLocation());
                counter++;

                card.setMouseListeners(mouseDragListener(card));
            }

            for (int j = 0; j < pile.getUpCards().size(); j++) {
                JCard card = new JCard(new Point(pilePanel.getX(), pilePanel.getY() + counter * 20), pile.getUpCards().get(j));
                add(card);
                moveCard(card, card.getLocation());
                counter++;

                card.setMouseListeners(mouseDragListener(card));
            }
        }

//        ArrayList<JLabel> cards = new ArrayList<>(); //list of cards for test purposes
//        for (int i = 0; i < 10; i++) {
//            JCard card = new JCard(new Point(0, 200 + i * 20), null);
//            add(card);
//
//            //add card to pile
//            moveCard(card, card.getLocation());
//
//            //add drag listener
//            card.setMouseListeners(mouseDragListener(card));
//
//            cards.add(card);
//        }
    }

    //listener to make card draggable
    MouseAdapter mouseDragListener(JCard card) {
        return new MouseAdapter() {
            Point originalPosition;
            Point startPoint;

            //set initial coordinates
            @Override
            public void mousePressed(MouseEvent e) {
                if(!card.getCard().isFlipped()) return;

                originalPosition = card.getLocation();
                startPoint = SwingUtilities.convertPoint(card, e.getPoint(), card.getParent());
                moveToFront(card);
            }

            //move card when dragged
            @Override
            public void mouseDragged(MouseEvent e) {
                if(!card.getCard().isFlipped()) return;

                Point location = SwingUtilities.convertPoint(card, e.getPoint(), card.getParent());

                if (card.getParent().getBounds().contains(location)) {
                    Point newLocation = card.getLocation();
                    newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);

                    //panel boundaries for the card
//                    newLocation.x = Math.max(newLocation.x, 0);
//                    newLocation.y = Math.max(newLocation.y, 0);
//                    newLocation.x = Math.min(newLocation.x, dragObject.getParent().getWidth() - dragObject.getWidth());
//                    newLocation.y = Math.min(newLocation.y, dragObject.getParent().getHeight() - dragObject.getHeight());

                    card.setLocation(newLocation);
                    startPoint = location;
                    System.out.println("az kogato");
                }
            }

            //reset on release
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!card.getCard().isFlipped()) return;

                int startPileIndex = indexOfPile(originalPosition);
                int newPileIndex = indexOfPile(startPoint);

                if(startPileIndex == newPileIndex) {
                    moveCardToOldPile(card, startPileIndex);
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
    private int indexOfPile(Point location) {
        for (PilePanel pilePanel : pilePanels) {
            if (pilePanel.getBounds().contains(location)) {
                return pilePanels.indexOf(pilePanel);
            }
        }

        return -1;
    }

    //moves card to the respective pile, returns boolean wheth  er the move was successful
    private boolean moveCard(JCard card, Point location) {
        int pileIndex = indexOfPile(location);

        if (pileIndex < 0)
            return false;

        PilePanel pilePanel = pilePanels.get(pileIndex);
        pilePanel.incrementCardAmount(1);
        card.setLocation(pilePanel.getX() + 10, pilePanel.getY() + pilePanel.getCardAmount() * 20);
        moveToFront(card);
        return true;
    }

    private void moveCardToOldPile(JCard card, int pileIndex){
        PilePanel pilePanel = pilePanels.get(pileIndex);
        card.setLocation(pilePanel.getX() + 10, pilePanel.getY() + pilePanel.getCardAmount() * 20);
        moveToFront(card);
    }

    private void removeCardFromPastPile(Point pastLocation) {
        int pileIndex = indexOfPile(pastLocation);

        if (pileIndex >= 0) {
            PilePanel pilePanel = pilePanels.get(pileIndex);
            pilePanel.incrementCardAmount(-1);
        }
    }
}

/*
Ideas:
-responsive pile coordination + responsive card coordination inside pile
-dynamic pile height
 */
