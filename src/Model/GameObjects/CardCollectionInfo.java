package Model.GameObjects;

import Model.Enums.CardCollectionType;

public record CardCollectionInfo(int index, CardCollectionType stackType) {
    public int getIndex() {
        return index;
    }

    public CardCollectionType getStackType() {
        return stackType;
    }
}
