package Model.Interfaces;

import Model.GameObjects.Card;

import java.util.Stack;

public abstract class CardStackableCollection {
    protected Stack<Card> cards;

    public boolean addCard(Card card) {
        if (isMoveAllowed(card)) {
            cards.push(card);
            return true;
        }
        return false;
    }

    public boolean removeLastCard() {
        if (!cards.isEmpty()) {
            cards.pop();
            return true;
        }
        return false;
    }

    protected abstract boolean isMoveAllowed(Card card);
}
