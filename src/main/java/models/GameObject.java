package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.GameObjectValue;

/*
This class is super class for all GameObject such as Deck(of the Ship), Aura(of the Ship) and etc.
 */

public abstract class GameObject {
    private int lineCoordinate;
    private int columnCoordinate;
    @JsonIgnore
    private Cell cell;
    private GameObjectValue gameObjectValue;

    protected GameObject(){}

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

    public GameObject(Cell cell, GameObjectValue gameObjectValue){
        this.cell = cell;
        this.gameObjectValue = gameObjectValue;
        this.lineCoordinate = cell.getLine();
        this.columnCoordinate = cell.getColumn();
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
