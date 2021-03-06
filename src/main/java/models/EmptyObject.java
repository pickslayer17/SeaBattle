package models;

import enums.GameObjectValue;

//a plug object for Cells are not empty
public class EmptyObject extends GameObject{
    private boolean isShot = false;
    public EmptyObject(Cell cell, GameObjectValue gameObjectValue) {
        super(cell, gameObjectValue);
    }

    public boolean isShot() {
        return isShot;
    }

    public void setShot(boolean shot) {
        isShot = shot;
    }
}
