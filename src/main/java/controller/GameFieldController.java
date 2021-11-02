package controller;

import models.GameField;
import models.Ship;
import models.Shot;
import process.GameProcess;
import viewer.GameFieldViewer;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
This object links GameField and GameFieldViewer. Also has function to control behaviour of the GameField
 */

public class GameFieldController {
    private GameField gameField;
    private GameFieldViewer gameFieldViewer;
    private ShipPlacer shipPlacer;
    private ShotManager shotManager;


    private ShipPlacer getShipPlacer(){
        return shipPlacer == null? shipPlacer = new ShipPlacer(gameField) : shipPlacer;
    }

    private ShotManager getShotManager(){
        return shotManager == null? shotManager = new ShotManager(gameField) : shotManager;
    }

    public GameFieldController(GameField gameField, GameFieldViewer gameFieldViewer) {
        this.gameField = gameField;
        this.gameFieldViewer = gameFieldViewer;
//        getShotManager().addListener(gameField);
    }
/*
This method returns true if Ships were put on the field

 */

    public boolean putShipsOnTheirPlaces(Set<Ship> shipSet) {
        return getShipPlacer().placeShipsOnTheirPlaces(shipSet);
    }

    public boolean putShipsInRandomEmptyPlace(Set<Ship> shipSet) {
        return getShipPlacer().placeShipsRandom(shipSet);
    }


/*
This method read all GameObjectValues from Cells on the GameField and output the result in System.out
 */
    public void updateView(String playerName) {
        gameFieldViewer.paintAll(gameField, playerName);
    }


    public boolean performShot() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type line(y):");
        int line = scanner.nextInt();
        System.out.println("Type line(x):");
        int column = scanner.nextInt();

        if(line == 666 && column == 13){
            System.exit(0);
        }
        if(line == 55 && column == 55){
            GameProcess.USER_WANTS_TO_QUIT = true;
        }

        if(!gameField.isCoordinateInsideField(line, column)){
            System.out.println("Bad coordinates!");
            return false;
        }

        return  getShotManager().shot(line, column);

    }

    public void makeShipsInvisible(Set<Ship> shipSet) {
        shipSet.stream().forEach(ship -> ship.makeInvisible());
    }


    public void verifyShotsAsIfPerformed(List<Shot> shotList) {
        getShotManager().verifyShotsAsIfPerformed(shotList);
    }
}
