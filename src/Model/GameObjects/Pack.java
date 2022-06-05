package Model.GameObjects;

import Model.Enums.CardNumber;
import Model.Enums.CardSuit;
import Model.GameObjects.Card;

import java.util.Collections;
import java.util.LinkedList;

//a pack with all cards, shuffled
public class Pack {
    public static final int NUMBER_OF_CARDS_IN_PACK = 52;
    public static final int NUMBER_OF_SUITS = 4;
    public static final int NUMBER_OF_CARDS_IN_SUIT = 13;

    private LinkedList<Card> pack;

    public Pack() {
        this.pack = new LinkedList<>();
        this.fillPack();
        this.shufflePack();
    }

    void fillPack() {
        for (int i = 0; i < NUMBER_OF_SUITS; ++ i) {
            for (int j = 0; j < NUMBER_OF_CARDS_IN_SUIT; ++ j) {
                Card card = new Card(CardSuit.values()[i], CardNumber.values()[j]);
                this.pack.add(card);
            }
        }
    }

    void shufflePack() {
        Collections.shuffle(pack);
    }

    //getter and setter
    public LinkedList<Card> getPack() {
        return pack;
    }

    public void setPack(LinkedList<Card> cards) {
        this.pack = cards;
    }

    @Override
    public String toString() {
        return "Model.GameObjects.Pack{" +
                "pack=" + pack +
                '}';
    }
}