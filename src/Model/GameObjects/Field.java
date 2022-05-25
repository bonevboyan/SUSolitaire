package Model.GameObjects;

import java.util.ArrayList;
import java.util.Stack;

public class Field {
    private ArrayList<Pile> piles;
    private ArrayList<Stack<Card>> foundations;

    private Stack<Card> upStock;
    private Stack<Card> downStock;

    public Field() {
        piles = new ArrayList<>();
        foundations = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            foundations.add(new Stack<>());
        }
        upStock = new Stack<>();
        downStock = new Stack<>();

        Pack pack = new Pack();

        for (int i = 1; i <= 7; i++) {
            var cards = pack.getPack().subList(0, i);

            Pile pile = new Pile(cards);
            piles.add(pile);

            cards.clear();
        }

        downStock.addAll(pack.getPack());
    }
}
