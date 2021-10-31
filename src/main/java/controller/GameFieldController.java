package controller;

import models.GameField;
import models.Ship;
import viewer.GameFieldViewer;

import java.util.Scanner;
import java.util.Set;

/*
This object controls behaviour of the model
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
        getShotManager().addListener(gameField);
    }
/*
This method returns true if Ships were put on the field

 */


    public boolean putShipsInRandomEmptyPlace(Set<Ship> shipSet) {
        return getShipPlacer().placeShipsRandom(shipSet);
    }


/*
This method read all GameObjectValues from Cells on the GameField and output the result in System.out
 */
    public void updateView(String playerName) {
        gameFieldViewer.paintAll(gameField, playerName);
    }


    public void userInput() {
        performShot();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("1. Shot\n2. Something else");
//        try {
//            switch (scanner.nextLine().charAt(0)) {
//                case '1':
//                    System.out.println("1");
//                    performShot();
//                    break;
//                case '2':
//                    System.out.println("2");
//                    break;
//                default:
//            }
//        } catch (StringIndexOutOfBoundsException exception){
//            exception.printStackTrace();
//        }
    }

    public boolean performShot() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type line(y):");
        int line = scanner.nextInt();
        System.out.println("Type line(x):");
        int column = scanner.nextInt();
        GameFieldViewer.debugCondition = true;
        return shotManager.shot(line, column);

    }
}
