package models;

import enums.ShipName;

import java.util.HashSet;
import java.util.Set;

public class ShipFactory {

    public Ship createNewShip(ShipName shipName){
        return new Ship(shipName);
    }

    public Set<Ship> createTestShipSet(){
        Set<Ship> shipSet = new HashSet<>();
        shipSet.add(createNewShip(ShipName.SINGLE_DECK));
        shipSet.add(createNewShip(ShipName.DOUBLE_DECK));
        return shipSet;
    }

    public Set<Ship> createHardTestShipSet(){
        Set<Ship> shipSet = new HashSet<>();
        shipSet.add(createNewShip(ShipName.SINGLE_DECK));
        shipSet.add(createNewShip(ShipName.DOUBLE_DECK));
        shipSet.add(createNewShip(ShipName.TRIPLE_DECK));
        shipSet.add(createNewShip(ShipName.TRIPLE_DECK));

        shipSet.add(createNewShip(ShipName.QUATRO_DECK));
        shipSet.add(createNewShip(ShipName.TRIPLE_DECK));
        shipSet.add(createNewShip(ShipName.TRIPLE_DECK));

        shipSet.add(createNewShip(ShipName.QUATRO_DECK));
        shipSet.add(createNewShip(ShipName.TRIPLE_DECK));
        shipSet.add(createNewShip(ShipName.TRIPLE_DECK));

        shipSet.add(createNewShip(ShipName.QUATRO_DECK));
        return shipSet;
    }


    public Set<Ship> createClassicShipSet(){
        Set<Ship> shipSet = new HashSet<>();
        shipSet.add(createNewShip(ShipName.SINGLE_DECK));
        shipSet.add(createNewShip(ShipName.SINGLE_DECK));
        shipSet.add(createNewShip(ShipName.SINGLE_DECK));
        shipSet.add(createNewShip(ShipName.SINGLE_DECK));

        shipSet.add(createNewShip(ShipName.DOUBLE_DECK));
        shipSet.add(createNewShip(ShipName.DOUBLE_DECK));
        shipSet.add(createNewShip(ShipName.DOUBLE_DECK));

        shipSet.add(createNewShip(ShipName.TRIPLE_DECK));
        shipSet.add(createNewShip(ShipName.TRIPLE_DECK));

        shipSet.add(createNewShip(ShipName.QUATRO_DECK));

        return shipSet;
    }
}
