package controller;

import enums.GameObjectValue;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/*
This object provides methods allow place Ship and its Aura on the GameField
 */

public class ShipPlacer {

    private final int RANDOM_ATTEMPTS_COUNT = 200;
    GameField gameField;
    public ShipPlacer(GameField gameField) {
        this.gameField = gameField;
    }



    public boolean placeShipRandom(Ship ship, int randomTimes){
        Random random = new Random();
        int line;
        int column;
        boolean isHorizontal;
            for (int i = 0; i < randomTimes; i++) {
                line = random.nextInt(0, gameField.getCountOfLines());
                column = random.nextInt(0, gameField.getCountOfColumns());
                isHorizontal = random.nextBoolean();
                if(putShipToField(ship, line, column, isHorizontal))
                    break;
            }
        return true;

    }

    public boolean placeShipsRandom(Set<Ship> shipSet){

        for(Ship ship: shipSet){

            placeShipRandom(ship, RANDOM_ATTEMPTS_COUNT);
        }
        return true;
    }
/*
This method put the Ship on the GameField checking if there is available space for it
Returns true if Ship was put and false if not
 */
    public boolean putShipToField(Ship ship, int line, int column, boolean isHorizontal)  {
        if(gameField.ifShipOnTheField(ship)){
            return false;
        }

        List<Cell> cellsForShip;
        cellsForShip = getCellsForShip(ship, line, column, isHorizontal);
        if (cellsForShip ==null){
            return false;
        }
        if(isAvailableSpaceForShip( ship, cellsForShip, isHorizontal)){

            gameField.addShip(ship);
            initializeDecks(ship, cellsForShip);
            initializeShipAura(ship, cellsForShip, isHorizontal);

            return true;
        }
        return false;
    }

/*
This method initialize Decks objects in the List of the Ship
Initially Ship doesn't have any Decks
 */
    private void initializeDecks(Ship ship, List<Cell> cellsForShip) {
        for(Cell cell : cellsForShip){
            Deck deck = new Deck(cell, GameObjectValue.DECK, ship);
            ship.getDecks().add(deck);
            cell.attachGameObject((GameObject) deck);

        }
    }

/*
This method create a list of Aura around the Ship
 */

    private void initializeShipAura(Ship ship, List<Cell> cellsForShip, boolean isHorizontal) {
        int lineBegin = cellsForShip.get(0).getLine()-1;
        int columnBegin = cellsForShip.get(0).getColumn()-1;
        int lineEnd = cellsForShip.get(cellsForShip.size()-1).getLine()+1;
        int columnEnd = cellsForShip.get(cellsForShip.size()-1).getColumn()+1;
        for (int i = lineBegin; i <= lineEnd; i++) {
            for (int j = columnBegin; j <= columnEnd; j++) {
                if(gameField.isCoordinateInsideField(i,j) && isCellEmptyForAura(i,j)) {
                    Aura aura = new Aura(gameField.getCell(i,j), ship, GameObjectValue.AURA);
                    ship.getShipAura().add(aura);
                    gameField.getCell(i,j).attachGameObject((GameObject) aura);

                }
            }
        }

    }
/*
This method returns true if CellValue is EMPTY or AURA
 */
    private boolean isCellEmptyForAura(int line, int column) {
        return  gameField.getCell(line, column).getLastGameObjectFromList().getGameObjectValue() == GameObjectValue.EMPTY ||
                gameField.getCell(line, column).getLastGameObjectFromList().getGameObjectValue() == GameObjectValue.AURA;
    }

/*
This method returns true if CellValue is EMPTY only
 */
    private boolean isCellEmptyForShip(int line, int column) {
        return gameField.getCell(line, column).getLastGameObjectFromList().getGameObjectValue() == GameObjectValue.EMPTY;
    }

/*
This method create a little list of Cells equivalent to Decks of the Ship by coordinates
returns NULL in case coordinates are out of bounce or CellValue is not EMPTY
 */
    private List<Cell> getCellsForShip(Ship ship, int line, int column, boolean isHorizontal)  {
        List<Cell> cellsForShip = new ArrayList<>();
        int size = ship.getShipName().getDeckCount();
        int x;
        int y;
        for (int i = 0; i < size; i++) {

            if(isHorizontal){
                x = line;
                y = column + i;

            } else {
                x = line + i;
                y = column;

            }
            if(!(gameField.isCoordinateInsideField(x,y) && isCellEmptyForShip(x,y)) ){
                return null;
            }
            cellsForShip.add(gameField.getCell(x, y));
        }
        return cellsForShip;

    }

/*
This method returns true if:
1.coordinates for Ship is not out of bounce in GameField 2d Array
&
2. the Cell doesn't contain any object or aura
 */
    private boolean isAvailableSpaceForShip( Ship ship, List<Cell> cellsForShip, boolean isHorizontal) {
        boolean result = true;
        int lineBegin = cellsForShip.get(0).getLine();
        int columnBegin = cellsForShip.get(0).getColumn();
        int lineEnd = cellsForShip.get(cellsForShip.size()-1).getLine();
        int columnEnd = cellsForShip.get(cellsForShip.size()-1).getColumn();

        for (int i = lineBegin; i <= lineEnd; i++) {
            for (int j = columnBegin; j <= columnEnd; j++) {
                if(!gameField.isCoordinateInsideField(i,j) && !isCellEmptyForShip(i,j)) {
                    return false;
                }
            }
        }
        return result;
    }


}
