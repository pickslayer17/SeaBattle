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

        GameObject gameObjectUnderShot = cell.findGameObjectUnderShots();
        shotGameObject(gameObjectUnderShot);//shot logic

        notifyListeners();
    }

    //


    private void shotGameObject(GameObject gameObject){

        switch (gameObject.getGameObjectValue()){
            case AURA:
                Aura aura = (Aura) gameObject;
                System.out.println("shot in Aura");
            break;
            case DECK:
                Deck deck = (Deck) gameObject;
                shotDeck(deck);
                System.out.println("shot in Deck");
                break;
            case EMPTY:
                EmptyObject empty = (EmptyObject) gameObject;
                System.out.println("missed!");
                break;

            default:
                System.out.println("????");
        }

    }

    private void shotDeck(Deck deck) {
        Ship ship = deck.getShip();
        if(deck.getDeckValue() == DeckValue.HEALTHY){
            deck.setDeckValue(DeckValue.INJURED);
            System.out.print("INJURED");
            checkShip(ship);
        } else {
            System.out.println("You've already shot here!");
        }
    }

    private void checkShip(Ship ship) {
        ship.setShipValue(ShipValue.INJURED);
        boolean isAlive = false;
        for(Deck deck: ship.getDecks()) {
            if(deck.getDeckValue() == DeckValue.HEALTHY){ //if there is at least one HEALTHY -> the SHIP is not DEAD
                isAlive = true;
            }
        }
        if(!isAlive){
            ship.setShipValue(ShipValue.DEAD);
            System.out.println(" & KILLED");
        } else {
            System.out.println();
        }

    }


}
