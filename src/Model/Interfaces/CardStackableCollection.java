package Model.Interfaces;

import Model.GameObjects.Card;

public interface CardStackableCollection {
    private boolean isMoveAllowed(Card card) {
        return false;
    }
}
