package models;

import enums.GameObjectValue;

public class EmptyObject extends GameObject{
    public EmptyObject(Cell cell, GameObjectValue gameObjectValue) {
        super(cell, gameObjectValue);
    }

    @Override
    public void shot() {
        System.out.println("shot to empty object");
    }
}
