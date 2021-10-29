package models;

import enums.GameObjectValue;

/*
This object allows to cover cells around the ship
 */

public class Aura extends  GameObject{
    public Aura(Cell cell, GameObjectValue gameObjectValue) {
        super(cell, gameObjectValue);
    }
}
