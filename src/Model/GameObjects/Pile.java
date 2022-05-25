package Model.GameObjects;

import Model.Enums.CardSuit;

import java.util.ArrayList;
import java.util.List;

public class Pile {
    private List<Card> downCards;
    private List<Card> upCards;

    public Pile(List<Card> cards) {
        this.downCards = new ArrayList<>();
        downCards.addAll(cards.subList(0, cards.size() - 1));

        this.upCards = new ArrayList<>();
        this.upCards.add(cards.get(cards.size() - 1));
        getLastCard().setFlipped(true);
    }

    public boolean addCard(Card card) {
        if (isMoveAllowed(card.getCardSymbol(), getLastCard().getCardSymbol())) {
            upCards.add(card);
            return true;
        }
        return false;
    }

    public boolean addCards(ArrayList<Card> cards) {
        if (isMoveAllowed(cards.get(0).getCardSymbol(), getLastCard().getCardSymbol())) {
            upCards.addAll(cards);
            return true;
        }
        return false;
    }

    public boolean removeLastCards(int count){
        if(count > upCards.size()){
            return false;
        } else {
            upCards.subList(upCards.size() - count, upCards.size()).clear();
            return true;
        }
    }

    private Card getLastCard() {
        return upCards.get(upCards.size() - 1);
    }

    private boolean isMoveAllowed(CardSuit firstCardSuit, CardSuit secondCardSuit) {
        return switch (firstCardSuit) {
            case CLUBS, SPADES -> secondCardSuit.equals(CardSuit.HEARTS) || secondCardSuit.equals(CardSuit.DIAMONDS);
            case HEARTS, DIAMONDS -> secondCardSuit.equals(CardSuit.CLUBS) || secondCardSuit.equals(CardSuit.SPADES);
        };
    }
}
