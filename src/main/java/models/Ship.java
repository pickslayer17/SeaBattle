package models;

import enums.DeckValue;
import enums.ShipName;
import enums.ShipValue;

import java.util.ArrayList;
import java.util.List;

/*
This is a ship
 */

public class Ship  {

    private List<Deck> decks;
    private ShipName shipName;
    private List<Aura> shipAura = new ArrayList<>();
    private ShipValue shipValue;
    private boolean isHorizontal;

    public ShipName getShipName() {
        return shipName;
    }

    public Ship(ShipName shipName) {
        this.shipName = shipName;
        this.isHorizontal = isHorizontal;
        decks = new ArrayList<>(shipName.getDeckCount());

    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public List<Aura> getShipAura() {
        return shipAura;
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public void setShipValue(ShipValue shipValue) {
        this.shipValue = shipValue;
    }

    public void checkShip(){
        boolean isAlive = false;
        for(Deck deck: decks) {
            if(deck.getDeckValue() == DeckValue.HEALTHY){ //if there is at least one HEALTHY -> the SHIP is not DEAD
                isAlive = true;
            }
        }
        if(!isAlive){
            killShip();
            System.out.println(" & KILLED");
        } else {
            System.out.println();
        }
    }

    private void killShip(){
        shipValue = ShipValue.DEAD;
        setAuraVisible();
    }

    public void setAuraVisible(){
        for(Aura aura:shipAura) {
            aura.setVisible(true);
        }
    }
}
