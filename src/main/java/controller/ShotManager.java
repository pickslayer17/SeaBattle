package controller;

import enums.DeckValue;
import enums.GameObjectValue;
import enums.ShipValue;
import models.*;

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


    //Create a Shot object and attaches it to Cell, then find gameObject under Shots and shot it
    public void shot(int line, int column) {
        Cell cell = gameField.getCell(line, column);
        Shot shot = new Shot(cell, GameObjectValue.SHOT);
        cell.attachGameObject((GameObject) shot);

        cell.findGameObjectUnderShots().shot();


        notifyListeners();
    }





}
