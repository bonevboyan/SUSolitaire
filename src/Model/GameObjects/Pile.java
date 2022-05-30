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
        getLastCard().setOpen(true);
    }

    public boolean addCard(Card card) {
        if (isMoveAllowed(card.getCardSuit(), getLastCard().getCardSuit())) {
            upCards.add(card);
            return true;
        }
        return false;
    }

    public boolean addCards(List<Card> cards) {
        return addCard(cards.get(0));
    }

    public List<Card> removeLastCards(int count){
        if(count > upCards.size()){
            return null;
        } else {

            var cards = new ArrayList<Card>(upCards.subList(upCards.size() - count, upCards.size()));
            upCards.removeAll(cards);

            if(upCards.isEmpty()){
                var card = downCards.remove(downCards.size() - 1);
                upCards.add(card);
                card.setOpen(true);
            }

            return cards;
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

    private boolean isMoveAllowed(CardSuit firstCardSuit, CardSuit secondCardSuit) {
        return switch (firstCardSuit) {
            case CLUBS, SPADES -> secondCardSuit.equals(CardSuit.HEARTS) || secondCardSuit.equals(CardSuit.DIAMONDS);
            case HEARTS, DIAMONDS -> secondCardSuit.equals(CardSuit.CLUBS) || secondCardSuit.equals(CardSuit.SPADES);
        };
    }
}
