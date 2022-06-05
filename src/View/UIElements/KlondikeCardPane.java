package View.UIElements;

import Model.Enums.CardCollectionType;
import Model.GameObjects.Card;
import Model.GameObjects.CardCollectionInfo;
import Model.GameObjects.Field;
import View.Sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

//extension of JLayeredPane to show overlapping cards
public class KlondikeCardPane extends JLayeredPane {
    ArrayList<PilePanel> pilePanels;
    ArrayList<FoundationPanel> foundationPanels;
    DownStockPanel downStockPanel;
    UpStockPanel upStockPanel;

    //list of the lists of the piles with cards
    ArrayList<ArrayList<JCard>> pileCards;
    Stack<JCard> downStock;
    Stack<JCard> upStock;
    Field field;
    Sound player;

    public KlondikeCardPane() {
        setBackground(new Color(0, 81, 0));

        //panels for coordination of cards in piles
        pilePanels = new ArrayList<>();
        foundationPanels = new ArrayList<>();

        pileCards = new ArrayList<>();
        downStock = new Stack<>();
        upStock = new Stack<>();

        //initialize game
        field = new Field();

        //add pile panels
        for (int i = 0; i < 7; i++) {
            PilePanel pilePanel = new PilePanel(new Point(i * 180, 250));

            pilePanels.add(pilePanel);
            add(pilePanel);

            var pile = field.getPiles().get(i);

            pileCards.add(new ArrayList<>());
            int counter = 0;
            for (Card card : pile.getCards()) {
                JCard jCard = new JCard(new Point(pilePanel.getX(), pilePanel.getY() + counter * 25), card);
                add(jCard);
                //set card to its appropriate location
                moveCard(jCard, jCard.getLocation());
                counter++;

                jCard.setMouseListeners(mouseDragListener(jCard));
            }
        }

        //add foundation panels
        for (int i = 0; i < 4; i++) {
            FoundationPanel foundationPanel = new FoundationPanel(new Point(540 + i * 180, 20));

            foundationPanels.add(foundationPanel);
            add(foundationPanel);
        }

        downStockPanel = new DownStockPanel(new Point(0, 20));
        downStockPanel.addMouseListener(restockListener());
        add(downStockPanel);

        var downStock = field.getDownStock();

        for (Card cardValue : downStock) {
            JCard card = new JCard(new Point(downStockPanel.getX() + 10, downStockPanel.getY() + 10), cardValue);
            card.setLocation(downStockPanel.getX() + 10, downStockPanel.getY() + 10);
            this.downStock.push(card);

            add(card);
            moveToFront(card);
            card.setMouseListeners(openCardListener(card));
        }

        upStockPanel = new UpStockPanel(new Point(180, 20));
        add(upStockPanel);

        //player to play sounds
        player = new Sound();
    }

    MouseAdapter openCardListener(JCard card) {
        return new MouseAdapter() {
            //opens card
            @Override
            public void mousePressed(MouseEvent e) {
                if (field.openCardFromStock()) {
                    player.playCardClick();

                    moveCard(card, new Point(190, 30));
                    upStock.push(downStock.pop());
                }
            }

            //resets card's listeners
            @Override
            public void mouseReleased(MouseEvent e) {
                for (MouseListener mouseListener : card.getMouseListeners()) {
                    card.removeMouseListener(mouseListener);
                }
                for (MouseMotionListener mouseListener : card.getMouseMotionListeners()) {
                    card.removeMouseMotionListener(mouseListener);
                }
                card.setMouseListeners(mouseDragListener(card));
            }
        };
    }

    //listener for click to restock the down stock
    MouseAdapter restockListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (downStock.isEmpty()) {
                    player.playCardClick();

                    //true restock in game's logic
                    field.restock();
                    while (!upStock.isEmpty()) {
                        downStock.push(upStock.pop());
                        moveToFront(downStock.peek());
                    }

                    //reset card listeners and position
                    downStock.forEach(x -> {
                        x.setLocation(new Point(10, 30));

                        for (MouseListener mouseListener : x.getMouseListeners()) {
                            x.removeMouseListener(mouseListener);
                        }
                        for (MouseMotionListener mouseListener : x.getMouseMotionListeners()) {
                            x.removeMouseMotionListener(mouseListener);
                        }
                        x.setMouseListeners(openCardListener(x));
                    });
                }
            }
        };
    }

    //listener to make card draggable
    MouseAdapter mouseDragListener(JCard originalCard) {
        return new MouseAdapter() {
            Point originalPosition;
            Point currentPoint;
            //list of all the cards being dragged
            List<JCard> cardsSet;

            @Override
            public void mousePressed(MouseEvent e) {
                //move legal only if card is open
                if (!originalCard.isOpen()) return;

                //sound
                player.playCardMove();

                //set initial coordinates
                originalPosition = originalCard.getLocation();
                currentPoint = SwingUtilities.convertPoint(originalCard, e.getPoint(), originalCard.getParent());

                CardCollectionInfo stackInfo = getStackInfo(getPanel(currentPoint));

                //set cards set of cards below the card clicked
                assert stackInfo != null;
                if (stackInfo.getStackType() == CardCollectionType.PILE) {
                    ArrayList<JCard> listOfDraggedCard = pileCards.get(stackInfo.getIndex());
                    cardsSet = listOfDraggedCard.subList(listOfDraggedCard.indexOf(originalCard), listOfDraggedCard.size());
                } else {
                    cardsSet = new ArrayList<>();
                    cardsSet.add(originalCard);
                }

                for (JCard card : cardsSet) moveToFront(card);
            }

            //move all cards from the set when dragged
            @Override
            public void mouseDragged(MouseEvent e) {
                //move legal only if card is open
                if (!originalCard.isOpen()) return;

                Point location = SwingUtilities.convertPoint(originalCard, e.getPoint(), originalCard.getParent());

                if (originalCard.getParent().getBounds().contains(location)) {
                    for (JCard card : cardsSet) {
                        Point newLocation = card.getLocation();
                        newLocation.translate(location.x - currentPoint.x, location.y - currentPoint.y);

                        card.setLocation(newLocation);
                    }
                    currentPoint = location;
                }
            }

            //set cards to their new position
            @Override
            public void mouseReleased(MouseEvent e) {
                //move legal only if card is open
                if (!originalCard.isOpen()) return;

                //get information for start and end position
                var startLocation = getPanel(originalPosition);
                var endLocation = getPanel(currentPoint);

                var startLocationInfo = getStackInfo(startLocation);
                var endLocationInfo = getStackInfo(endLocation);

                //checks if move is appropriate
                if (startLocation == endLocation ||
                        startLocationInfo == null ||
                        endLocationInfo == null ||
                        endLocation instanceof UpStockPanel) {
                    //resets cards to the original position
                    for (JCard card : cardsSet) {
                        card.setLocation(originalPosition);
                        originalPosition.translate(0, 20);
                    }
                    return;
                }

                //checks if move a single from stock follows the rules
                if (startLocationInfo.getStackType() == CardCollectionType.STOCK) {
                    if (field.moveCardFromStock(endLocationInfo)) {
                        player.playCardMove();

                        moveCard(originalCard, currentPoint);
                        removeCardFromPastPile(originalPosition);
                        upStock.pop();
                    } else {
                        //resets card to the original position
                        originalCard.setLocation(originalPosition);
                    }
                    return;
                }

                //checks if other possible move follows the rules
                if (field.moveCards(cardsSet.size(), startLocationInfo, endLocationInfo)) {
                    player.playCardMove();

                    for (JCard card : cardsSet) {
                        moveCard(card, currentPoint);
                        removeCardFromPastPile(originalPosition);
                    }
                    pileCards.get(Objects.requireNonNull(getStackInfo(startLocation)).getIndex()).removeAll(cardsSet);
                } else {
                    for (JCard card : cardsSet) {
                        //resets cards to the original position
                        card.setLocation(originalPosition);
                        originalPosition.translate(0, 20);
                    }
                }
            }
        };
    }

    //finds the panel by given coordinates
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

        if (dropLocation instanceof PilePanel dropPile) {
            card.setLocation(dropPile.getX() + 10, dropPile.getY() + dropPile.getCardAmount() * 25);
            dropPile.incrementCardAmount(1);
            pileCards.get(Objects.requireNonNull(getStackInfo(dropPile)).getIndex()).add(card);
        } else {
            //moves card to foundation
            card.setLocation(dropLocation.getX() + 10, dropLocation.getY() + 10);
        }

        moveToFront(card);
        return true;
    }

    //updates pile's card amount
    private void removeCardFromPastPile(Point pastLocation) {
        var location = getPanel(pastLocation);

        if (location instanceof PilePanel pilePanel) {
            pilePanel.incrementCardAmount(-1);
        }
    }

    //gets info for the card collection by given panel
    private CardCollectionInfo getStackInfo(JPanel panel) {
        try {
            if (panel instanceof PilePanel pilePanel) {
                return new CardCollectionInfo(this.pilePanels.indexOf(pilePanel), CardCollectionType.PILE);
            } else if (panel instanceof FoundationPanel foundationPanel) {
                return new CardCollectionInfo(this.foundationPanels.indexOf(foundationPanel), CardCollectionType.FOUNDATION);
            } else if (panel instanceof UpStockPanel) {
                return new CardCollectionInfo(0, CardCollectionType.STOCK);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public Field getField() {
        return field;
    }
}

/*
Ideas:
-responsive pile coordination + responsive card coordination inside pile
 */
