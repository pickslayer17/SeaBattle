package controller.logic;

import models.EmptyObject;
import models.GameObject;

public class EmptyShotVerifier extends ShotVerifier{

    public EmptyShotVerifier(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void verify() {
        EmptyObject empty = (EmptyObject) getGameObject();
        empty.setShot(true);
        System.out.println("Not Deck");
    }


}
