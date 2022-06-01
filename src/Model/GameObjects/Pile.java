package Model.GameObjects;

import Model.Enums.CardSuit;
import Model.Interfaces.CardStackableCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Pile extends CardStackableCollection {

    public Pile(List<Card> cards) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
        this.cards.peek().setOpen(true);
    }

    public boolean addCards(List<Card> cards) {
        if (!isMoveAllowed(cards.get(0))) return false;

        this.cards.addAll(cards);
        return true;
    }

    public List<Card> removeLastCards(int count) {
        if (count > cards.size()) {
            return null;
        }

        ArrayList<Card> removedCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            removedCards.add(cards.pop());
        }
        Collections.reverse(removedCards);

        if (!cards.isEmpty()) {
            cards.peek().setOpen(true);
        }

        return cards;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    @Override
    protected boolean isMoveAllowed(Card card) {
        CardSuit cardSuit = card.getCardSuit();
        CardSuit lastSuit = cards.peek().getCardSuit();
        return switch (cardSuit) {
            case CLUBS, SPADES -> lastSuit.equals(CardSuit.HEARTS) || lastSuit.equals(CardSuit.DIAMONDS);
            case HEARTS, DIAMONDS -> lastSuit.equals(CardSuit.CLUBS) || lastSuit.equals(CardSuit.SPADES);
        };
    }
}
