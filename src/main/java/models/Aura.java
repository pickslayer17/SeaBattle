package models;

import enums.GameObjectValue;

/*
This object allows covering cells around the ship
 */

public class Aura extends  GameObject{

    private Ship ship;
    private boolean isVisible = false;
    private boolean isShot = false;

    public void setShot(boolean shot) {
        isShot = shot;
    }

    public boolean isShot() {
        return isShot;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
        setVisibleAllAuraInCell();//There could be a few Aura object in one Cell. We want make them all visible because of linear visualization
    }

    public void setVisibleAllAuraInCell(){
        getCell().setVisibleAllAura();
//        for(GameObject gameObject: getCell().getGameObjects()){
//            if(gameObject.getGameObjectValue() == GameObjectValue.AURA){
//                Aura aura = (Aura) gameObject;
//                if(!aura.isVisible()) {
//                    aura.setVisible(true);
//                }
//            }
//        }

    }

    public Aura(Cell cell, Ship ship, GameObjectValue gameObjectValue) {
        super(cell, gameObjectValue);
        this.ship = ship;
    }

}
