package files;

import models.GameField;
import models.Ship;
import models.Shot;
import process.Player;

import java.io.*;
import java.util.*;

public class DataHandler {

    private final String  FILE_PATH = "src/main/java/files/SeaBattleLog.json";
    private File file = new File(FILE_PATH);
    private Parser parser;

    Deque<GameState> gameStateHistory = new ArrayDeque<>();


    public DataHandler() {

    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void writeToFile(String data) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        try{
            stringBuilder.append(data);
        } catch (NullPointerException exception) {
            System.err.println("First set parser!");
            exception.printStackTrace();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException exception){
            exception.printStackTrace();
        }

    }

    public Deque<GameState> readFromFile() throws IOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        while(scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine());
        }
        return parser.deSerialize(stringBuilder.toString().trim());
    }



    public Deque<GameState> loadGameState() throws IOException {
        Deque<GameState> gameStateDeque;
        gameStateDeque = readFromFile();
        return gameStateDeque;
    }


    public void saveGameState(Player player1, Player player2) throws IOException {
        Set<Ship> shipSet1 = player1.getShips();
        List<Shot> shotList1 = player1.getShots();
        GameField gameField1 = player1.getGameField();
        FieldState fieldState1 = new FieldState(player1.getName(), gameField1, shipSet1, shotList1);

        Set<Ship> shipSet2 = player2.getShips();
        List<Shot> shotList2 = player2.getShots();
        GameField gameField2 = player2.getGameField();
        FieldState fieldState2 = new FieldState(player2.getName(), gameField2, shipSet2, shotList2);

        GameState gameState = new GameState(fieldState1, fieldState2);
        gameStateHistory.push(gameState);

        writeToFile(parser.serialize(gameStateHistory));
        System.out.println("Saving successfully!");
    }
}
