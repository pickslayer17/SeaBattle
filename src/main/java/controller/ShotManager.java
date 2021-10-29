package controller;

import enums.GameObjectValue;
import models.ShotListener;
import models.Cell;
import models.GameField;
import models.GameObject;
import models.Shot;

import java.util.ArrayList;
import java.util.List;

/*
This object does all dirty work with shot
 */
public class ShotManager {
    GameField gameField;
    private List<ShotListener> shotListenerList;

    public ShotManager(GameField gameField) {
        this.gameField = gameField;
        shotListenerList = new ArrayList<>();
    }

    public void addListener(ShotListener shotListener){
        shotListenerList.add(shotListener);
    }

    public void notifyListeners(){
        shotListenerList.stream().forEach(shotListener -> shotListener.shotUpdate());
    }


    //Create a Shot object and attaches it to Cell, then
    public void shot(int line, int column) {
        Cell cell = gameField.getCell(line, column);
        Shot shot = new Shot(cell, GameObjectValue.SHOT);
        cell.attachGameObject((GameObject) shot);

        GameObject gameObjectUnderShot = findGameObjectUnderShot(cell);
        shotGameObject(gameObjectUnderShot);//shot logic


        notifyListeners();
    }

    //
    private GameObject findGameObjectUnderShot(Cell cell){
        List<GameObject> gameObjectList = cell.getGameObjects();
        gameObjectList.stream().forEach(x -> System.out.println(x));

        GameObject gameObjectUnderShot = gameObjectList.get(gameObjectList.size()-1);//top GameObject in gameObjectList of the Cell
        for (int i = gameObjectList.size()-1; i >=0 ; i--) {
            if(gameObjectList.get(i).getGameObjectValue() != GameObjectValue.SHOT){
                gameObjectUnderShot = gameObjectList.get(i);
                System.out.println("Position of non-shot object is " + i);
                break;
            }
        }
        return gameObjectUnderShot;
    }

    private void shotGameObject(GameObject gameObject){
        switch (gameObject.getGameObjectValue()){
            case AURA:
                System.out.println("shot in Aura");
            break;
            case DECK:
                System.out.println("shot in Deck");
                break;
            case EMPTY:
                System.out.println("missed!");
                break;

            default:
                System.out.println("????");
        }

    }


}
