package controller;

import controller.logic.AuraShotVerifier;
import controller.logic.DeckShotVerifier;
import controller.logic.EmptyShotVerifier;
import controller.logic.ShotVerifier;
import enums.GameObjectValue;
import models.*;

import java.util.ArrayList;
import java.util.List;

/*
This object creates ShotVerifier object which contains logic of shot
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
    public boolean shot(int line, int column) {
        Cell cell = gameField.getCell(line, column);
        Shot shot = new Shot(cell, GameObjectValue.SHOT);
        cell.attachGameObject((GameObject) shot);
        GameObject gameObject = cell.findGameObjectUnderShots();

        ShotVerifier shotVerifier;
        switch (gameObject.getGameObjectValue()){
            case DECK -> shotVerifier = new DeckShotVerifier(gameObject);
            case AURA -> shotVerifier = new AuraShotVerifier(gameObject);
            case EMPTY -> shotVerifier = new EmptyShotVerifier(gameObject);
            default -> shotVerifier = new EmptyShotVerifier(gameObject);
        }
        shotVerifier.verify();

        notifyListeners();
        System.out.println("BOOM!");

        return shotVerifier.isShotSuccess();
    }





}
