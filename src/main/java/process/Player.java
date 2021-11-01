package process;

import controller.GameFieldController;
import enums.GameObjectValue;
import exceptions.DumnException;
import models.*;
import viewer.GameFieldViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Player {
    private String name;
    private GameField gameField;
    private GameFieldController gameFieldController;
    private GameFieldViewer gameFieldViewer;
    private Player enemyPlayer;

    public String getName() {
        return name;
    }

    public Player(String name) {
        this.name = name;
    }

    public void initializePlayer(boolean isEnemy, int lineCount, int columnCount) throws DumnException {
        gameField = new GameField(lineCount,columnCount);
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

    public EmptyObject[][] getEmptyObjects() {
        int lines = gameField.getCountOfLines();
        int columns = gameField.getCountOfColumns();
        EmptyObject[][] emptyObjects = new EmptyObject[lines][columns];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j <  columns; j++) {
                emptyObjects[i][j] = gameField.getCell(i, j).getEmptyObject();
            }
        }

        return emptyObjects;
    }

    public Set<Ship> getShips() {
        return gameField.getShips();
    }

    public List<Shot> getShots() {
        int lines = gameField.getCountOfLines();
        int columns = gameField.getCountOfColumns();
        List<Shot> shotList = new ArrayList<>();
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                List<Shot> shotListInCell = gameField.getCell(i, j).getGameObjects().stream()
                        .filter(gameObject -> gameObject.getGameObjectValue() == GameObjectValue.SHOT)
                        .map(gameObject -> (Shot) gameObject)
                        .collect(Collectors.toList());
                shotList.addAll(shotListInCell);
            }
        }
        return shotList;
    }
}
