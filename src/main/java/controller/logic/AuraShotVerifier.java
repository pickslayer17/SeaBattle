package controller.logic;

import models.Aura;
import models.GameObject;

public class AuraShotVerifier extends ShotVerifier{

    public AuraShotVerifier(GameObject gameObject) {
        super(gameObject);
    }


    public void verify() {
        Aura aura = (Aura) getGameObject();
        aura.setShot(true);
        System.out.println("Shot to aura");
    }


}
