package main.java.com.gamestate;

import javax.swing.*;
import java.util.Map;

public class HumanPlayer extends Player{





    @Override
    public void getShipPlacement(Grid grid, Map<ShipType, Ship> ships) {
    }


    private int getCoordinate (String axis, ShipType shipEntry) {
        return 0;
    }


    private Grid.ShipDirection getOrientation (ShipType shipEntry) {
        return null;
    }

    @Override
    public Coordinate getShotCoordinates() {
        return null;
    }


}
