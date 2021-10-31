package controller.logic;

import models.GameObject;

public abstract class ShotVerifier {

    private GameObject gameObject;

    public GameObject getGameObject() {
        return gameObject;
    }

    public ShotVerifier(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract void verify();

    public abstract boolean isShotSuccess();
}
