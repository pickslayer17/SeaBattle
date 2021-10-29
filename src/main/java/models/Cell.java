package models;

import enums.GameObjectValue;

import java.util.ArrayList;
import java.util.List;

/*
This object is main container for GameObjects
 */

public class Cell  {
    private int line;
    private int column;
    private List<GameObject> gameObjects = new ArrayList<>();
    private GameField gameField;

    public GameField getGameField() {
        return gameField;
    }

     Cell(GameField gameField, int line, int column) {
        this.gameField = gameField;
        this.line = line;
        this.column = column;
        attachGameObject( new EmptyObject(this, GameObjectValue.EMPTY));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameObject getLastGameObjectFromList(){
        return gameObjects.get(gameObjects.size()-1);
    }


    /*


     */
    public void attachGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }







}
