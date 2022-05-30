package Model.Interfaces;

import Model.GameObjects.Card;

public interface CardStackableCollection {
    boolean isMoveAllowed(Card card);
}
