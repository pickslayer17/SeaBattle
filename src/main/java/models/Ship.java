package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.DeckValue;
import enums.ShipName;
import enums.ShipValue;

import java.util.ArrayList;
import java.util.List;

/*
This is a ship
 */

public class Ship  {

    private int lineCoordinate;
    private int columnCoordinate;
    @JsonIgnore
    private List<Deck> decks;
    private ShipName shipName;
    @JsonIgnore
    private List<Aura> shipAura = new ArrayList<>();
    private ShipValue shipValue = ShipValue.HEALTHY;
    private boolean isHorizontal;
    private boolean isVisible = true;

    //for Jackson
    private Ship(){}

    public int getLineCoordinate() {
        return lineCoordinate;
    }

    public void setLineCoordinate(int lineCoordinate) {
        this.lineCoordinate = lineCoordinate;
    }

    public int getColumnCoordinate() {
        return columnCoordinate;
    }

    public void setColumnCoordinate(int columnCoordinate) {
        this.columnCoordinate = columnCoordinate;
    }

    public ShipValue getShipValue() {
        return shipValue;
    }

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

    public void injureShip() {
        this.shipValue = ShipValue.INJURED;
    }

    public void killShip(){
        shipValue = ShipValue.DEAD;
        setAuraVisible();
    }

    public void setAuraVisible(){
        for(Aura aura:shipAura) {
            aura.setVisible(true);
        }
    }

    public void makeInvisible(){
        decks.stream().forEach(deck -> deck.setVisible(false));
        isVisible = false;
    }

    public void makeVisible(){
        decks.stream().forEach(deck -> deck.setVisible(true));
        isVisible = true;
    }
}
