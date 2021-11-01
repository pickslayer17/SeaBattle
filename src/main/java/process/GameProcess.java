package process;

import exceptions.DumnException;

import java.util.Scanner;

public class GameProcess {

    private Player player1;
    private Player player2;
    private static boolean isGameOver = false;
    private static GameProcess instance;

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

    }

    public void addPlayers(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public void initGame() throws DumnException {
        player1.initializePlayer(false);
        player1.addEnemy(player2);
        player2.initializePlayer(true);
        player2.addEnemy(player1);
    }

    public void play() throws DumnException {
        initGame();
        boolean isPlayer1Turn = true;
        while (!isGameOver()){
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

}
