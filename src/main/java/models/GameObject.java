package models;

import enums.GameObjectValue;

/*
This class is super class for all GameObject such as Deck(of the Ship), Aura(of the Ship) ans etc.
 */

public abstract class GameObject {
    private Cell cell;
    private GameObjectValue gameObjectValue;

    public GameObject(Cell cell, GameObjectValue gameObjectValue){
        this.cell = cell;
        this.gameObjectValue = gameObjectValue;

    }


    private void setGameObjectValue(GameObjectValue gameObjectValue) {
        this.gameObjectValue = gameObjectValue;

    }

    public Cell getCell() {
        return cell;
    }

    public GameObjectValue getGameObjectValue() {

        return gameObjectValue;
    }
}
