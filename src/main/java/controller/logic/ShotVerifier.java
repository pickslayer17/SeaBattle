package controller.logic;

import enums.GameObjectValue;
import enums.ShipValue;
import models.GameField;
import models.GameObject;


//allows creating classes for verify shot logic for GameObject
public abstract class ShotVerifier {

    private GameObject gameObject;

    public GameObject getGameObject() {
        return gameObject;
    }

    public ShotVerifier(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract void verify();

    public  boolean isShotSuccess(){
        return gameObject.getGameObjectValue() == GameObjectValue.DECK;
    }
}
