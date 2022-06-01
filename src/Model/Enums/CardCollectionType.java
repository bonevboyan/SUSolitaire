package Model.Enums;

public enum CardCollectionType {
    PILE(0),
    FOUNDATION(7),
    STOCK(9);

    public int index;

    CardCollectionType(int index){
        this.index = index;
    }
}
