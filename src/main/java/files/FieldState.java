package files;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import models.GameField;
import models.Ship;
import models.Shot;

import java.util.List;
import java.util.Set;

//keeps data about current state of GameField such as its size, Ships location and Shot performed
public class FieldState {


    private String playerName;
    private GameField gameField;
    private Set<Ship> shipSet;
    private List<Shot> shotList;

    public GameField getGameField() {
        return gameField;
    }

    public String getPlayerName() {
        return playerName;
    }

    //for Jackson
    private FieldState(){

    }

    public FieldState(String playerName, GameField gameField, Set<Ship> shipSet, List<Shot> shotList) {
        this.playerName = playerName;
        this.gameField = gameField;
        this.shipSet = shipSet;
        this.shotList = shotList;
    }

    public Set<Ship> getShipSet() {
        return shipSet;
    }

    public List<Shot> getShotList() {
        return shotList;
    }
}
