package Model.GameObjects;

import Model.Enums.CardSuit;

import java.util.ArrayList;
import java.util.List;

public class Pile {
    //unrevealed cards
    private List<Card> downCards;
    //revealed cards
    private List<Card> upCards;

    public Pile(List<Card> cards) {
        this.downCards = new ArrayList<>();
        downCards.addAll(cards.subList(0, cards.size() - 1));

        this.upCards = new ArrayList<>();
        this.upCards.add(cards.get(cards.size() - 1));
        getLastCard().setOpen(true);
    }

    public boolean addCard(Card card) {
        if (isMoveAllowed(card)) {
            upCards.add(card);
            return true;
        }
        return false;
    }

    public boolean addCards(ArrayList<Card> cards) {
        if (isMoveAllowed(cards.get(0))) {
            upCards.addAll(cards);
            return true;
        }
        return false;
    }

    public boolean removeLastCards(int count){
        if (count > upCards.size()) {
            return false;
        } else {
            upCards.subList(upCards.size() - count, upCards.size()).clear();
            return true;
        }
    }

    public List<Card> getDownCards() {
        return downCards;
    }

    public List<Card> getUpCards() {
        return upCards;
    }

    private Card getLastCard() {
        return upCards.get(upCards.size() - 1);
    }

    private boolean isMoveAllowed(Card card) {
        CardSuit cardSuit = card.getCardSuit();
        CardSuit lastSuit = getLastCard().getCardSuit();
        return switch (cardSuit) {
            case CLUBS, SPADES -> lastSuit.equals(CardSuit.HEARTS) || lastSuit.equals(CardSuit.DIAMONDS);
            case HEARTS, DIAMONDS -> lastSuit.equals(CardSuit.CLUBS) || lastSuit.equals(CardSuit.SPADES);
        };
    }
}
