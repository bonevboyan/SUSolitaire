package Model.GameObjects;

import Model.Enums.CardNumber;
import Model.Enums.CardSuit;
import Model.Contracts.CardStackableCollection;

import java.util.List;
import java.util.Stack;

public class Pile extends CardStackableCollection {

    public Pile(List<Card> cards) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
        this.cards.peek().setOpen(true);
    }

    //returns if the move was successful
    public boolean addCards(List<Card> cards) {
        if (!isMoveAllowed(cards.get(0))) return false;

        this.cards.addAll(cards);
        return true;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    @Override
    protected boolean isMoveAllowed(Card card) {
        if (cards.isEmpty()) {
            return card.getCardNumber() == CardNumber.KING;
        }

        CardSuit cardSuit = card.getCardSuit();
        CardSuit lastSuit = cards.peek().getCardSuit();
        return switch (cardSuit) {
            case CLUBS, SPADES -> (lastSuit.equals(CardSuit.HEARTS) || lastSuit.equals(CardSuit.DIAMONDS))
                    && card.getCardNumber() == CardNumber.values()[(cards.peek().getCardNumber().ordinal() - 1)];
            case HEARTS, DIAMONDS -> (lastSuit.equals(CardSuit.CLUBS) || lastSuit.equals(CardSuit.SPADES))
                    && card.getCardNumber() == CardNumber.values()[(cards.peek().getCardNumber().ordinal() - 1)];
        };
    }
}
