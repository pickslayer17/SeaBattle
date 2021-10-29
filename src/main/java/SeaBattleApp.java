import controller.GameFieldController;
import exceptions.DumnException;
import models.GameField;
import models.Ship;
import models.ShipFactory;
import viewer.GameFieldViewer;

import java.util.Set;

public class SeaBattleApp {

    static StringBuilder LOG = new StringBuilder("");

    public static void main(String[] args) throws DumnException {
        GameField gameField = new GameField(10,10);
        GameFieldViewer gameFieldViewer = new GameFieldViewer();
        GameFieldController gameFieldController = new GameFieldController(gameField, gameFieldViewer);

        ShipFactory shipFactory = new ShipFactory();
        Set<Ship> shipSet = shipFactory.createClassicShipSet();
        System.out.println(gameFieldController.putShipsInRandomEmptyPlace(shipSet));

        gameFieldController.updateView();
        while(true){
            gameFieldController.userInput();
            gameFieldController.updateView();
        }




//        dataHandler.saveData()
//        dataHandler.loadHistory();
//        dataHandler.loadLastGame()
//        gameFieldController.showHi
//        story();

    }
}
