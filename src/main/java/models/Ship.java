package models;

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

    public ShipName getShipName() {
        return shipName;
    }

    public Ship(ShipName shipName) {
        this.shipName = shipName;
        decks = new ArrayList<>(shipName.getDeckCount());

    }


    public List<Aura> getShipAura() {
        return shipAura;
    }

    public List<Deck> getDecks() {
        return decks;
    }






}
