package process;

import exceptions.DumnException;
import files.DataHandler;
import files.FieldState;
import files.GameState;
import files.JsonParser;

import java.io.IOException;
import java.util.Scanner;

public class GameProcess {

    private Player player1;
    private Player player2;
    private boolean isPlayer1Visible = true;
    private boolean isPlayer2Visible = true;
    private static boolean isGameOver = false;
    private static GameProcess instance;
    private int lineCount;
    private int columnCount;
    private DataHandler dataHandler = new DataHandler();


    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public static void setIsGameOver(boolean isGameOver) {
        GameProcess.isGameOver = isGameOver;
    }


    public static GameProcess getInstance(){
        if(instance == null) {
            instance = new GameProcess();
        }
        return instance;
    }


    private GameProcess() {
        dataHandler.setParser(new JsonParser());
    }

    public void addPlayers(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public void initGame(String player1Name, String player2Name, int lineCount, int columnCount) throws DumnException {
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        addPlayers(player1, player2);
        setLineCount(lineCount);
        setColumnCount(columnCount);
        initPlayers();

    }

    public void initPlayers() throws DumnException {
        player1.initializePlayer(lineCount, columnCount);
        player1.addRandomShipSet(isPlayer1Visible);
        player1.addEnemy(player2);
        player2.initializePlayer(lineCount, columnCount);
        player2.addRandomShipSet(isPlayer2Visible);
        player2.addEnemy(player1);
    }

    public void play() throws DumnException {
        player1.updateViewer();
        player2.updateViewer();
        boolean isPlayer1Turn = true;
        int i = 0;
        while (!isGameOver()){

            saveGameState();

            if(isPlayer1Turn){
                isPlayer1Turn = player1.turn();
            } else{
                isPlayer1Turn = !player2.turn();

            }
            player1.updateViewer();
            player2.updateViewer();


        }

    }

    public void userInput() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Play new Game 2. Show last history action 3. Show all history");
        try {
            switch (scanner.nextLine().charAt(0)) {
                case '1':
                    initGame("PlaYYer1", "PloYYer2", lineCount, columnCount);
                    play();
                    saveGameState();
                    break;
                case '2':
                    showGameState();
                    break;
                case '3':
                    showHistory();
                default:
            }
        } catch (StringIndexOutOfBoundsException | DumnException exception){
            exception.printStackTrace();
        }
    }

    public void start() throws IOException {
        while(true){
            userInput();
            if(isGameOver())
            break;
        }
        System.out.println("start func is ended");
    }


    public static boolean isGameOver() {
        return isGameOver;
    }

    public void showHistory(){

    }



    public void showGameState() throws IOException, DumnException {
        GameState gameState = dataHandler.loadGameState().peek();
        FieldState fieldState1 = gameState.getFieldState1();
        FieldState fieldState2 = gameState.getFieldState2();


        initGame(fieldState1.getPlayerName(),fieldState2.getPlayerName(), fieldState1.getGameField().getCountOfLines(), fieldState1.getGameField().getCountOfColumns());


        player1.initializePlayer( lineCount, columnCount);
        player1.addEnemy(player2);
        player1.addConcreteShipSet(isPlayer1Visible, fieldState1.getShipSet());
        player1.putShotsOnField(fieldState1.getShotList());
        player1.verifyShots(fieldState1.getShotList());

        player2.initializePlayer(lineCount, columnCount);
        player2.addConcreteShipSet(isPlayer2Visible, fieldState2.getShipSet());
        player2.addEnemy(player1);
        player2.putShotsOnField(fieldState2.getShotList());
        player2.verifyShots(fieldState2.getShotList());

        player1.updateViewer();
        player2.updateViewer();

    }


    public void saveGameState()  {
        try{
            dataHandler.saveGameState(player1, player2);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void setPlayersVisibility(boolean isPlayer1Visible, boolean isPlayer2Visible) {
        this.isPlayer1Visible = isPlayer1Visible;
        this.isPlayer2Visible = isPlayer2Visible;
    }
}
