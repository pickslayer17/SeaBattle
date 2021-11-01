package process;

import exceptions.DumnException;
import files.DataHandler;
import files.JsonParser;

import java.io.IOException;
import java.util.Scanner;

public class GameProcess {

    private Player player1;
    private Player player2;
    private static boolean isGameOver = false;
    private static GameProcess instance;
    private int lineCount = 10;
    private int columnCount = 10;
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

    public void initGame(boolean player1Invisible, boolean player2Invisible) throws DumnException {
        player1.initializePlayer(player1Invisible, lineCount, columnCount);
        player1.addEnemy(player2);
        player2.initializePlayer(player2Invisible, lineCount, columnCount);
        player2.addEnemy(player1);
    }

    public void play() throws DumnException {
        initGame(false, false);
        boolean isPlayer1Turn = true;
        int i = 0;
        while (!isGameOver()){

//            saveGameState();

            if(isPlayer1Turn){
                isPlayer1Turn = player1.turn();
            } else{
                isPlayer1Turn = !player2.turn();

            }
            player1.updateViewer();
            player2.updateViewer();


        }
    }

    public void userInput() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Play Game 2. Something else");
        try {
            switch (scanner.nextLine().charAt(0)) {
                case '1':
                    System.out.println("1");
                    play();
                    break;
                case '2':
                    System.out.println("2");
                    showGameState();
                    break;
                default:
            }
        } catch (StringIndexOutOfBoundsException | DumnException exception){
            exception.printStackTrace();
        }
    }

    public void start() {
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


    public void showGameState()  {
        try{
        dataHandler.loadGameState().peek().getFieldState1().getShipSet().stream()
                .forEach(ship -> System.out.println(ship.getShipName()));}
         catch (IOException exception){
            exception.printStackTrace();
         }
    }


    public void saveGameState(){
        try {
            dataHandler.saveGameState(player1, player2);
        } catch (IOException exception) {
            System.err.println("Some IOException");
            exception.printStackTrace();
        }
    }
}
