package process;

import exceptions.DumnException;

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

    public void initGame(Player player1, Player player2) throws DumnException {
        this.player1 = player1;
        this.player2 = player2;
        player1.initializePlayer();
        player1.addEnemy(player2);
        player2.initializePlayer();
        player2.addEnemy(player1);
    }

    public void play(){
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

    public static boolean isGameOver() {
        return isGameOver;
    }
}
