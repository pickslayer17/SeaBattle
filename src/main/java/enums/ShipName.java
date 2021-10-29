package enums;



public enum ShipName {
    SINGLE_DECK(1),
    DOUBLE_DECK(2),
    TRIPLE_DECK(3),
    QUATRO_DECK(4);

    private int deckCount;

    public int getDeckCount() {
        return deckCount;
    }

    ShipName(int deckCount) {
        this.deckCount = deckCount;
    }
}
