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

    public EmptyObject getEmptyObject(){
        return (EmptyObject) gameObjects.get(0);
    }

    /*
    this method attaches gameObject to Cell gameObject list
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

    public GameObject findGameObjectUnderShots(){
        GameObject gameObjectUnderShot = getLastGameObjectFromList();//top GameObject in gameObjectList of the Cell
        for (int i = gameObjects.size()-1; i >=0 ; i--) {
            if(gameObjects.get(i).getGameObjectValue() != GameObjectValue.SHOT){
                gameObjectUnderShot = gameObjects.get(i);
                break;
            }
        }
        return gameObjectUnderShot;
    }


    public void setVisibleAllAura() {
        for(GameObject gameObject: gameObjects){
            if(gameObject.getGameObjectValue() == GameObjectValue.AURA){
                Aura aura = (Aura) gameObject;
                if(!aura.isVisible()) {
                    aura.setVisible(true);
                }
            }
        }
    }
}
