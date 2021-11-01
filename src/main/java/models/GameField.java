package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import exceptions.DumnException;

import java.util.HashSet;
import java.util.Set;

/*
This object stores 2d array of cells and Set of Ships
 */

public class GameField  {
    @JsonIgnore
    private Cell[][] cells;

    @JsonIgnore
    private Set<Ship> ships = new HashSet<>();

    //for Jackson
    private GameField(){}

    public int getCountOfLines() {
        return cells.length;
    }

    public int getCountOfColumns() {
        return cells[0].length;
    }

    public GameField(int lines, int columns) throws DumnException {
        if (lines <= 0 || columns <= 0) {
            throw new DumnException("Did you think well before?");
        }
        cells = new Cell[lines][columns];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(this, i, j);
            }
        }

    }

    public Cell getCell(int line, int column) {

        return cells[line][column];
    }




    public boolean isCoordinateInsideField(int x, int y){
        if(x >= 0 && x < getCountOfColumns() && y >= 0 && y < getCountOfLines()){
            return true;
        }
        return false;
    }


    public Set<Ship> getShips() {
        return ships;
    }

    public boolean ifShipOnTheField(Ship ship){
        return ships.contains(ship);
    }

    public void addShip(Ship ship){
        ships.add(ship);
    }


}
