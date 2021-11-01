package process;

import controller.GameFieldController;
import exceptions.DumnException;
import models.GameField;
import models.Ship;
import models.ShipFactory;
import viewer.GameFieldViewer;

import java.util.Set;

public class Player {
    private String name;
    private GameField gameField;
    private GameFieldController gameFieldController;
    private GameFieldViewer gameFieldViewer;
    private Player enemyPlayer;

    public Player(String name) {
        this.name = name;
    }

    public void initializePlayer(boolean isEnemy) throws DumnException {
        gameField = new GameField(5,5);
        gameFieldViewer = new GameFieldViewer();
        gameFieldController = new GameFieldController(gameField, gameFieldViewer);
        ShipFactory shipFactory = new ShipFactory();

//        Set<Ship> shipSet = shipFactory.createClassicShipSet();
        Set<Ship> shipSet = shipFactory.createTestShipSet();
//        Set<Ship> shipSet = shipFactory.createHardTestShipSet();


        gameFieldController.putShipsInRandomEmptyPlace(shipSet);
        if(isEnemy){
            gameFieldController.makeShipsInvisible(shipSet);
        }
        gameFieldController.updateView(name);
    }


    public boolean turn(){
            System.out.println(name + " turn");
            return enemyPlayer.gameFieldController.performShot();
    }



    public void addEnemy(Player enemyPlayer) {
        this.enemyPlayer = enemyPlayer;
    }

    public void updateViewer() {
        gameFieldController.updateView(name);
    }
}
