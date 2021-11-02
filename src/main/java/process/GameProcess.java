package process;

import exceptions.DumnException;
import files.DataHandler;
import files.FieldState;
import files.GameState;
import files.JsonParser;

import java.io.IOException;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

/*
This is the main game object. Which keep players, main game properties and also DataHanlder
 */
public class GameProcess {

    public static boolean USER_WANTS_TO_QUIT = false;
    //default values
    private boolean isPlayer1Visible = true;
    private boolean isPlayer2Visible = true;
    private int lineCount=10;
    private int columnCount=10;

    private Player player1;
    private Player player2;
    private static boolean isGameOver = false;
    private static GameProcess instance;

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

    public void play()  {
        player1.updateViewer();
        player2.updateViewer();
        boolean isPlayer1Turn = true;
        int i = 0;
        while (!isGameOver() && !USER_WANTS_TO_QUIT){

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
        System.out.println("1. Play new Game 2. Show last history action 3. Show all history 4. Exit");
        try {
            switch (scanner.nextLine().charAt(0)) {
                case '1':
                    initGame("PlaYYer1", "PloYYer2", lineCount, columnCount);
                    play();

                    GameProcess.USER_WANTS_TO_QUIT = false;
                    break;
                case '2':
                    showGameState(getLastGameState());
                    break;
                case '3':
                    showHistory();
                    break;
                case '4':
                    System.exit(0);
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

    public void showHistory() throws IOException ,DumnException{
        Deque<GameState> gameStates = dataHandler.loadGameState();
        Iterator<GameState> iterator = gameStates.descendingIterator();
        while(iterator.hasNext()){
            showGameState(iterator.next());
        }



    }

    public GameState getLastGameState() throws IOException {
        return dataHandler.loadGameState().peek();
    }



    public void showGameState(GameState gameState) throws IOException, DumnException {
        System.out.println("Game state# " + gameState.getNumber());
        FieldState fieldState1 = gameState.getFieldState1();
        FieldState fieldState2 = gameState.getFieldState2();


        initGame(fieldState1.getPlayerName(),fieldState2.getPlayerName(), fieldState1.getGameField().getCountOfLines(), fieldState1.getGameField().getCountOfColumns());


        player1.initializePlayer( lineCount, columnCount);
//        player1.addEnemy(player2); // in case we want to continue game after loading
        player1.addConcreteShipSet(isPlayer1Visible, fieldState1.getShipSet());
        player1.putShotsOnField(fieldState1.getShotList());

        player2.initializePlayer(lineCount, columnCount);
        player2.addConcreteShipSet(isPlayer2Visible, fieldState2.getShipSet());
//        player2.addEnemy(player1);
        player2.putShotsOnField(fieldState2.getShotList());


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
