package Model.GameObjects;

import Model.Enums.CardNumber;
import Model.Enums.CardSuit;

public class Card {
    private CardSuit cardSuit;
    private CardNumber cardNumber;
    private boolean isOpen;

    public Card() {
    }

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
        this.isOpen = false;
    }

    //getters and setters
    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.cardSuit = cardSuit;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "Model.GameObjects.Card{" +
                "cardSuit=" + cardSuit +
                ", cardNumber=" + cardNumber +
                '}';
    }
}