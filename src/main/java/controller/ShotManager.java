package controller;

import controller.logic.AuraShotVerifier;
import controller.logic.DeckShotVerifier;
import controller.logic.EmptyShotVerifier;
import controller.logic.ShotVerifier;
import enums.GameObjectValue;
import models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
This object creates ShotVerifier object which contains shot logic
 */
public class ShotManager {
    GameField gameField;
    private List<ShotListener> shotListenerList;
    public static int shotCount = 0;

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
        Shot shot = new Shot(cell, GameObjectValue.SHOT, shotCount);
        shotCount++;
        cell.attachGameObject((GameObject) shot);
        GameObject gameObject = cell.findGameObjectUnderShots();


        boolean isShotSuccess = verifyShotLogic(gameObject);

        notifyListeners();
        System.out.println("\nField of " + gameField.getName() + " (" + shot.getLineCoordinate() + "," + shot.getColumnCoordinate() + ") BOOM!");

        return isShotSuccess;
    }

    public boolean verifyShotLogic(GameObject gameObject){
        ShotVerifier shotVerifier;
        switch (gameObject.getGameObjectValue()){
            case DECK -> shotVerifier = new DeckShotVerifier(gameObject);
            case AURA -> shotVerifier = new AuraShotVerifier(gameObject);
            case EMPTY -> shotVerifier = new EmptyShotVerifier(gameObject);
            default -> shotVerifier = new EmptyShotVerifier(gameObject);
        }
        shotVerifier.verify();
        return shotVerifier.isShotSuccess();
    }

    public boolean isAnyShotInCell(int line, int column){
        Cell cell = gameField.getCell(line, column);
        return cell.getGameObjects().stream().
                anyMatch(gameObject -> gameObject.getGameObjectValue() == GameObjectValue.SHOT);
    }

    //verify list of shots as if they were performed
    public void verifyShotsAsIfPerformed(List<Shot> shotList){
        List<Shot> orderShotList = shotList.stream().
                sorted(Comparator.comparing(shot -> shot.getShotNumber())).
                collect(Collectors.toList());
        for(Shot shot: orderShotList){
            int line = shot.getLineCoordinate();
            int column = shot.getColumnCoordinate();
            System.out.print("\n" + shot.getShotNumber() + ". Field of " + gameField.getName() + " (" + line + "," + column + ") ");
            Cell cell = gameField.getCell(line, column);
            verifyShotLogic(cell.findGameObjectUnderShots());

        }
    }

}
