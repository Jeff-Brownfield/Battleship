package main.java.com.gamestate;

import java.util.Map;

public class ComputerPlayer extends Player{
    @Override
    public void getShipPlacement(Grid grid, Map<ShipType, Ship> ships) {

    }

    @Override
    public Coordinate getShotCoordinates() {
        int column = (int)(Math.random() * 10);
        int row = (int)(Math.random() * 10);

        Coordinate shot = new Coordinate(row, column);
        return shot;
    }
}
