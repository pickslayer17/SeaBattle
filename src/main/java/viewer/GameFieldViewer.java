package viewer;

import enums.GameObjectValue;
import models.Cell;
import models.Deck;
import models.GameField;
import models.GameObject;

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

    private boolean auraCondition(GameObject gameObject){
        return true;
    }

    private void fillArray(GameField gameField) {

        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                Cell cell = gameField.getCell(i,j);
                GameObject gameObject = cell.findGameObjectUnderShots();
                GameObjectValue gameObjectValue = gameObject.getGameObjectValue();
//                GameObjectValue gameObjectValue = gameObject.getGameObjectValue();
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
                            if(auraCondition(gameObject)){
                                fieldArray[i][j] = '-';
                            } else {
                                fieldArray[i][j] = ' ';
                            }
                            break;
                        default:
                            fieldArray[i][j] = '@';
                    }
            }
        }
    }
}
