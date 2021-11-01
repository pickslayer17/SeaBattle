package viewer;

import enums.GameObjectValue;
import models.*;

/*
This object only output data based on CellValue of the Cells in the GameField
 */

public class GameFieldViewer {
    public static boolean debugCondition=false;
    char[][] fieldArray;
    private final char EMPTY = ' ';
    private final char EMPTY_SHOT = '.';
    private final char DECK_HEALTHY = 'O';
    private final char DECK_INJURED = 'X';
    private final char DECK_DEAD = 'X';
    private final char AURA_HEALTHY = '-';
    private final char AURA_SHOT = '*';


    public void paintAll(GameField gameField, String playerName) {
        fieldArray = new char[gameField.getCountOfLines()][gameField.getCountOfColumns()];
        fillArray(gameField);

        //field out here
        System.out.println("--------------------");
        System.out.println("--" + playerName + "--");
        System.out.println("--------------------");
        for (int i = 0; i < fieldArray.length; i++) {
            System.out.print(i);
            for (int j = 0; j < fieldArray[i].length; j++) {
                System.out.print("[");
                System.out.print(fieldArray[i][j]);
                System.out.print("]");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    private boolean auraIsVisible(Aura aura){
        if(aura.isVisible()) {
            return true;
        }
        return false;
    }

    private void fillArray(GameField gameField) {

        Cell cell;
        GameObject gameObject;
        GameObjectValue gameObjectValue;
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                cell = gameField.getCell(i,j);
                gameObject = cell.findGameObjectUnderShots();
                gameObjectValue = gameObject.getGameObjectValue();

                fillArraySwitch(gameObject,gameObjectValue,i,j);//Huge switch

                /*
                For graphics. Layers.
                 */
//                for(GameObject gObjInCell: cell.getGameObjects()){
//                    if(gObjInCell.getGameObjectValue() == GameObjectValue.SHOT) break;
//                    fillArraySwitch(gObjInCell,gObjInCell.getGameObjectValue(),i,j);
//                }

            }
        }
    }

    public void fillArraySwitch(GameObject gameObject, GameObjectValue gameObjectValue, int i, int j){
        switch (gameObjectValue) {
            case DECK:
                Deck deck = (Deck) gameObject;
                switch (deck.getDeckValue()){
                    case HEALTHY -> fieldArray[i][j] = DECK_HEALTHY;
                    case INJURED -> fieldArray[i][j] = DECK_INJURED;
                }
                if(!deck.isVisible()){
                    fieldArray[i][j] = EMPTY;
                }
                break;
//            case SHOT:
//                fieldArray[i][j] = '*';
//                break;
            case EMPTY:
                EmptyObject empty = (EmptyObject) gameObject;
                if(empty.isShot()){
                    fieldArray[i][j] = EMPTY_SHOT;
                } else {
                    fieldArray[i][j] = EMPTY;
                }
                break;
            case AURA:
                Aura aura = (Aura) gameObject;
                if(aura.isShot()){
                    fieldArray[i][j] = AURA_SHOT;
                } else {
                    if(auraIsVisible(aura)){
                        fieldArray[i][j] = AURA_HEALTHY;
                    } else {
                        fieldArray[i][j] = EMPTY;
                    }
                }
                break;
            default:
                fieldArray[i][j] = '@';
        }

    }
}
