package Model;

import Model.Enums.CardNumber;
import Model.Enums.CardSuit;

public class Card {
    private CardSuit cardSuit;
    private CardNumber cardNumber;

    public Card() {
    }

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
    }

    //getters and setters
    public CardSuit getCardSymbol() {
        return cardSuit;
    }

    public void setCardSymbol(CardSuit cardSuit) {
        this.cardSuit = cardSuit;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Model.Card{" +
                "cardSuit=" + cardSuit +
                ", cardNumber=" + cardNumber +
                '}';
    }
}