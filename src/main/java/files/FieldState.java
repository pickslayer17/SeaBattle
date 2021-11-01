package files;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import models.Ship;
import models.Shot;

import java.util.List;
import java.util.Set;

public class FieldState {


    private String playerName;
    private Set<Ship> shipSet;
    private List<Shot> shotList;

    //for Jackson
    private FieldState(){

    }

    public FieldState(String playerName, Set<Ship> shipSet, List<Shot> shotList) {
        this.playerName = playerName;
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
