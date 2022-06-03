package Model.GameObjects;

import Model.Interfaces.CardStackableCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Field {
    private List<CardStackableCollection> cardStacks;

    private Stack<Card> upStock;
    private Stack<Card> downStock;

    public Field() {
        cardStacks = new ArrayList<>();
        upStock = new Stack<>();
        downStock = new Stack<>();

        Pack pack = new Pack();

        for (int i = 1; i <= 7; i++) {
            var cards = pack.getPack().subList(0, i);

            Pile pile = new Pile(cards);
            cardStacks.add(pile);

            cards.clear();
        }

        downStock.addAll(pack.getPack());

        for (int i = 0; i < 4; i++) {
            cardStacks.add(new Foundation());
        }
    }

    public void restock() {
        try {
            //while (!upStock.isEmpty()) downStock.push(upStock.pop());
            downStock.addAll(upStock);
            downStock.forEach(x -> x.setOpen(false));
            //Collections.reverse(downStock);
            upStock = new Stack<>();

        } catch (Exception ignored) {}
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

    public boolean moveCardFromStock(CardCollectionInfo stackInfo) {
        try {
            var stack = this.cardStacks.get(stackInfo.getIndex() + stackInfo.getStackType().index);

            var card = upStock.pop();

            var cards = new ArrayList<Card>();
            cards.add(card);

            if (stack.addCards(cards)) return true;
            else {
                upStock.add(card);
                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    public boolean moveCards(int count, CardCollectionInfo oldStackInfo, CardCollectionInfo newStackInfo) {
        try {
            var oldStack = this.cardStacks.get(oldStackInfo.getIndex() + oldStackInfo.getStackType().index);
            var newStack = this.cardStacks.get(newStackInfo.getIndex() + newStackInfo.getStackType().index);

            var cards = oldStack.removeLastCards(count);
            if (cards == null) return false;

            if (newStack.addCards(cards)) {
                if (!oldStack.getCards().isEmpty()) {
                    oldStack.getCards().peek().setOpen(true);
                }
                return true;
            } else {
                oldStack.getCards().addAll(cards);
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public List<Pile> getPiles() {
        return cardStacks.subList(0, 7).stream().map(x -> (Pile) x).toList();
    }

    public Stack<Card> getDownStock() {
        return downStock;
    }
}
