package Model.GameObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Field {
    private List<Pile> piles;
    private List<Foundation> foundations;

    private Stack<Card> upStock;
    private Stack<Card> downStock;

    public Field() {
        piles = new ArrayList<>();
        foundations = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            foundations.add(new Foundation());
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

    public Card drawCard() {
        if (downStock.size() != 0) {
            upStock.push(downStock.pop());
            return upStock.peek();
        }

        downStock.addAll(upStock);
        upStock.clear();
        return null;
    }

    public List<Pile> getPiles() {
        return piles;
    }

    public List<Foundation> getFoundations() {
        return foundations;
    }

    public Stack<Card> getUpStock() {
        return upStock;
    }

    public Stack<Card> getDownStock() {
        return downStock;
    }
}
