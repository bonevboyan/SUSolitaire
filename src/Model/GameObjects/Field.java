package Model.GameObjects;

import Model.Enums.CardSuit;

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

        for (int i = 0; i < 4; i++) {
            foundations.add(new Foundation());
        }
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

    public boolean openCardFromStock() {
        try {
            var card = downStock.pop();
            card.setOpen(true);
            upStock.push(card);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean moveCards(int count, int oldPileIndex, int newPileIndex) {
        try {
            var oldPile = this.piles.get(oldPileIndex);
            var newPile = this.piles.get(newPileIndex);

            var cards = oldPile.removeLastCards(count);

            if (cards == null) return false;

            if (newPile.addCards(cards)) {
                return true;
            }

            oldPile.addCards(cards);
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean dropCardFromStock(int pileIndex) {
        try {
            var pile = this.piles.get(pileIndex);
            var card = upStock.pop();

            if (pile.addCard(card)) return true;

            upStock.push(card);
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean addCardFromPileToFoundation(int pileIndex, int foundationIndex) {
        try {
            var pile = piles.get(pileIndex);

            var foundation = piles.get(foundationIndex);

            var cards = pile.removeLastCards(1);
            foundation.addCard(cards.get(0));

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean addCardFromStockToFoundation(int foundationIndex) {
        try {
            var foundation = piles.get(foundationIndex);

            var card = upStock.pop();
            foundation.addCard(card);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean dropCardFromFoundation(int pileIndex, int foundationIndex) {
        try {
            var pile = piles.get(pileIndex);

            var foundation = piles.get(foundationIndex);

            var cards = foundation.removeLastCards(1);
            pile.addCards(cards);

            return true;
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
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
