package models;

import enums.DeckValue;
import enums.GameObjectValue;
import enums.ShipValue;

/*
This object is a block of the Ship
 */

public class Deck extends GameObject{

    private Ship ship;
    private DeckValue deckValue;
    private boolean isVisible = true;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public DeckValue getDeckValue() {
        return deckValue;
    }

    public Deck(Cell cell, GameObjectValue gameObjectValue, Ship ship) {
        super(cell, gameObjectValue);
        deckValue = DeckValue.HEALTHY;
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public void setDeckValue(DeckValue deckValue) {
        this.deckValue = deckValue;

    }

}
