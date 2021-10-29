package controller;

import java.io.*;
import java.util.Scanner;

public class DataHandler {

    private final String  FILE_PATH = "src/main/java/files/SeaBattleLog.json";
    private File file = new File(FILE_PATH);
    private Parser parser;

    public DataHandler() {

    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void writeToFile(){
        String data = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(parser.parseTo(data));

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,true))) {//append
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException exception){
            exception.printStackTrace();
        }

    }

    public String readFromFile(){
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
        return parser.parseFrom(stringBuilder.toString());
    }

    public void saveData(){
        writeToFile();
    }
}
