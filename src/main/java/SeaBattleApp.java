import exceptions.DumnException;
import process.GameProcess;
import process.Player;

import java.io.IOException;

public class SeaBattleApp {

    static StringBuilder LOG = new StringBuilder("");

    public static void main(String[] args) throws IOException, DumnException {

        GameProcess gameProcess = GameProcess.getInstance();
        gameProcess.setLineCount(10);
        gameProcess.setColumnCount(10);
        gameProcess.setPlayersVisibility(true, true);

        gameProcess.start();

    }


}
