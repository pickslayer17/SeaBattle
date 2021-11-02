package models;

import enums.GameObjectValue;

//placed on the GameField to know where Shot was performed
public class Shot extends GameObject{

    private int shotNumber=0;

    public int getShotNumber() {
        return shotNumber;
    }



    private Shot(){
        super();
    }

    public Shot(Cell cell, GameObjectValue gameObjectValue, int shotNumber) {
        super(cell, gameObjectValue);
        this.shotNumber = shotNumber;
    }

    public void shot(){
        System.out.println("shot to the shot");
    }
}
