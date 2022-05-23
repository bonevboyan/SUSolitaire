package Model.GameObjects;

import Model.Enums.CardNumber;
import Model.Enums.CardSuit;

public class Card {
    private CardSuit cardSuit;
    private CardNumber cardNumber;
    private boolean isFlipped;

    public Card() {
    }

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
        this.isFlipped = false;
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

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    @Override
    public String toString() {
        return "Model.GameObjects.Card{" +
                "cardSuit=" + cardSuit +
                ", cardNumber=" + cardNumber +
                '}';
    }
}