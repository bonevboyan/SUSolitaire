package Model.GameObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Field {
    private List<Pile> piles;
    private List<Stack<Card>> foundations;

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

    public Card drawCard() {
        if (downStock.size() != 0) {
            upStock.push(downStock.pop());
            return upStock.peek();
        }

        downStock.addAll(upStock);
        upStock.clear();
        return null;
    }

    public boolean moveCards(int count, int oldPileIndex, int newPileIndex) {
        try {
            var oldPile = this.piles.get(oldPileIndex);
            var newPile = this.piles.get(newPileIndex);

            var cards = oldPile.removeLastCards(count);

            if(cards == null) return false;

            if(newPile.addCards(cards)) return true;
            else {
                oldPile.addCards(cards);
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public boolean dropCardFromStock(int pileIndex) {
        try {
            var pile = this.piles.get(pileIndex);

            return true;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public boolean addCardToFoundation(Card card, int foundationIndex) {
        try {

            return true;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public boolean dropCardFromFoundation(int pileIndex, int foundationIndex) {
        try {

            return true;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public List<Pile> getPiles() {
        return piles;
    }

    public List<Stack<Card>> getFoundations() {
        return foundations;
    }

    public Stack<Card> getUpStock() {
        return upStock;
    }

    public Stack<Card> getDownStock() {
        return downStock;
    }
}
