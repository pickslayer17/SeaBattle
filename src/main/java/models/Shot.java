package models;

import enums.GameObjectValue;

public class Shot extends GameObject{
    public Shot(Cell cell, GameObjectValue gameObjectValue) {
        super(cell, gameObjectValue);
    }

    public void shot(){
        System.out.println("shot to the shot");
    }
}
