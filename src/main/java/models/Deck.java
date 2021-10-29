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

    private void setDeckValue(DeckValue deckValue) {
        this.deckValue = deckValue;

    }
    public void shot(){
        System.out.println("shot in Deck");
        if(deckValue == DeckValue.HEALTHY){
            deckValue =DeckValue.INJURED;
            System.out.print("INJURED");
            notifyShip();
        } else {
            System.out.println("You've already shot here!");
        }
    }

    private void notifyShip(){
        ship.checkShip();
//        boolean isAlive = false;
//        for(Deck deck: ship.getDecks()) {
//            if(deck.getDeckValue() == DeckValue.HEALTHY){ //if there is at least one HEALTHY -> the SHIP is not DEAD
//                isAlive = true;
//            }
//        }
//        if(!isAlive){
//            ship.setShipValue(ShipValue.DEAD);
//            System.out.println(" & KILLED");
//        } else {
//            System.out.println();
//        }

    }
}
