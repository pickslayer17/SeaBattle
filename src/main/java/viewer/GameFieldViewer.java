package viewer;

import enums.GameObjectValue;
import models.*;

/*
This object only output data based on CellValue of the Cells in the GameField
 */

public class GameFieldViewer {
    public static boolean debugCondition=false;
    char[][] fieldArray;

    public void paintAll(GameField gameField) {
        fieldArray = new char[gameField.getCountOfLines()][gameField.getCountOfColumns()];
        fillArray(gameField);

        //field out here
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

            }
        }
    }

    public void fillArraySwitch(GameObject gameObject, GameObjectValue gameObjectValue, int i, int j){
        switch (gameObjectValue) {
            case DECK:
                Deck deck = (Deck) gameObject;
                switch (deck.getDeckValue()){
                    case HEALTHY -> fieldArray[i][j] = 'O';
                    case INJURED -> fieldArray[i][j] = 'X';
                }
                break;
            case SHOT:
                fieldArray[i][j] = '*';
                break;
            case EMPTY:
                fieldArray[i][j] = ' ';
                break;
            case AURA:
                Aura aura = (Aura) gameObject;
                if(aura.isShot()){
                    fieldArray[i][j] = '*';
                } else {
                    if(auraIsVisible(aura)){
                        fieldArray[i][j] = '-';
                    } else {
                        fieldArray[i][j] = ' ';
                    }
                }
                break;
            default:
                fieldArray[i][j] = '@';
        }

    }
}
