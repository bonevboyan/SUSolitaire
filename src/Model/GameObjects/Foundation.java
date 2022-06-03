package Model.GameObjects;

import Model.Enums.CardNumber;
import Model.Enums.CardSuit;
import Model.Contracts.CardStackableCollection;

import java.util.List;
import java.util.Stack;

public class Foundation extends CardStackableCollection {
    private CardSuit suit;

    public Foundation() {
        cards = new Stack<>();
    }

    public Foundation(CardSuit suit) {
        cards = new Stack<>();
        this.suit = suit;
    }

    public Foundation(List<Card> cards, CardSuit suit) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
        this.suit = suit;
    }

    @Override
    public boolean addCards(List<Card> newCards) {
        if(newCards.size() != 1) return false;

        boolean correctMove = super.addCards(newCards);
        if (correctMove && cards.size() == 1) {
            suit = cards.peek().getCardSuit();
        }
        return correctMove;
    }


    @Override
    protected boolean isMoveAllowed(Card card) {
        return (cards.isEmpty() &&
               card.getCardNumber() == CardNumber.ACE) ||
               (card.getCardSuit() == suit &&
               card.getCardNumber() == CardNumber.values()[(cards.peek().getCardNumber().ordinal() + 1)]);
    }
}
