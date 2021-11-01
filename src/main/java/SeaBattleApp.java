import exceptions.DumnException;
import process.GameProcess;
import process.Player;

public class SeaBattleApp {

    static StringBuilder LOG = new StringBuilder("");

    public static void main(String[] args) throws DumnException {
//        GameField gameField = new GameField(10,10);
//        GameFieldViewer gameFieldViewer = new GameFieldViewer();
//        GameFieldController gameFieldController = new GameFieldController(gameField, gameFieldViewer);
//
//        ShipFactory shipFactory = new ShipFactory();
//        Set<Ship> shipSet = shipFactory.createClassicShipSet();
//        System.out.println(gameFieldController.putShipsInRandomEmptyPlace(shipSet));
//
//        gameFieldController.updateView();
//        while(true){
//            gameFieldController.userInput();
//            gameFieldController.updateView();
//        }


        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        GameProcess gameProcess = GameProcess.getInstance();
        gameProcess.addPlayers(player1, player2);
//        gameProcess.initGame();

        gameProcess.setLineCount(5);
        gameProcess.setColumnCount(5);
        gameProcess.start();



//        DataHandler dataHandler = new DataHandler();
//        dataHandler.setParser(new JsonParser());
//        dataHandler.saveData();

//        dataHandler.loadHistory();
//        dataHandler.loadLastGame()
//        gameFieldController.showHi
//        story();

    }


}
