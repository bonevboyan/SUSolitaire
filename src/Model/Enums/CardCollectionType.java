package Model.Enums;

public enum CardCollectionType {
    PILE(0),
    FOUNDATION(7),
    STOCK(9);

    private int index;

    CardCollectionType(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
