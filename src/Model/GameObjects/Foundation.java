package Model.GameObjects;

import Model.Enums.CardNumber;
import Model.Enums.CardSuit;

import java.util.Stack;

public class Foundation {
    private Stack<Card> cards;
    private CardSuit suit;

    public Foundation() {
        cards = new Stack<>();
    }

    public Foundation(Stack<Card> cards, CardSuit suit) {
        this.cards = cards;
        this.suit = suit;
    }

    public boolean addCard(Card card) {
        if (isMoveAllowed(card)) {
            if (cards.isEmpty())
                suit = card.getCardSuit();

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

    private Card getLastCard() {
        return cards.peek();
    }

    public boolean isMoveAllowed(Card card) {
        return (cards.isEmpty() &&
               card.getCardNumber() == CardNumber.ACE) ||
               (card.getCardSuit() == suit &&
               card.getCardNumber() == CardNumber.values()[(getLastCard().getCardNumber().ordinal() + 1)]);
    }
}
