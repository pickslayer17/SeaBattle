package controller.logic;

import enums.DeckValue;
import enums.ShipValue;
import models.Deck;
import models.GameObject;
import models.Ship;
import process.GameProcess;

public class DeckShotVerifier extends ShotVerifier {

    public DeckShotVerifier(GameObject gameObject) {
        super(gameObject);
    }


    public void verify() {
        Deck deck = (Deck) getGameObject();
        DeckValue deckValue = deck.getDeckValue();
        System.out.println("shot in Deck");

        if(deckValue == DeckValue.HEALTHY){
            deck.setDeckValue(DeckValue.INJURED);
            deck.setVisible(true);
            System.out.println("INJURED");
            verifyShip();
        } else {
            System.out.println("You've already shot this Deck!");
        }
    }



    private void verifyShip(){
        boolean isAlive = false;
        Ship ship = ((Deck) getGameObject()).getShip();
        ship.injureShip();
        for(Deck deck: ship.getDecks()) {
            if(deck.getDeckValue() == DeckValue.HEALTHY ){ //if there is at least one HEALTHY -> the SHIP is not DEAD
                isAlive = true;
            }
        }
        if(!isAlive){
            ship.killShip();
            System.out.println(" & KILLED");
            verifyAllShips();
        }
    }

    private void verifyAllShips() {
        boolean areAllShipAlive = false;

        for(Ship ship : getGameObject().getCell().getGameField().getShips()){
            if(ship.getShipValue() != ShipValue.DEAD){
                areAllShipAlive = true;
            }
        }
        if(!areAllShipAlive){
            System.out.println("GAME IS FINISHED! All ships are KILLED!");
            GameProcess.setIsGameOver(true);
        }
    }
}
