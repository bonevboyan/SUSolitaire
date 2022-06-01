package Model.Interfaces;

import Model.GameObjects.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public abstract class CardStackableCollection {
    protected Stack<Card> cards;

    public boolean addCards(List<Card> newCards) {
        if (isMoveAllowed(newCards.get(0))) {
            this.cards.addAll(newCards);
            return true;
        }
        return false;
    }

    public List<Card> removeLastCards(int count) {
        if (count > cards.size()) {
            return null;
        }

        List<Card> removedCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            removedCards.add(cards.pop());
        }
        Collections.reverse(removedCards);

        return removedCards;
    }

    protected abstract boolean isMoveAllowed(Card card);

    public Stack<Card> getCards() {
        return cards;
    }
}
