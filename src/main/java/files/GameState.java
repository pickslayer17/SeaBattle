package files;

import models.GameField;

public class GameState {
    private FieldState fieldState1;
    private FieldState fieldState2;
    private GameField gameField1;
    private GameField gameField2;

    public GameField getGameField1() {
        return gameField1;
    }

    public GameField getGameField2() {
        return gameField2;
    }

    //for Jackson
    private GameState(){

    }

    public GameState(FieldState fieldState1, FieldState fieldState2) {
        this.fieldState1 = fieldState1;
        this.fieldState2 = fieldState2;
    }

    public FieldState getFieldState1() {
        return fieldState1;
    }

    public FieldState getFieldState2() {
        return fieldState2;
    }
}
