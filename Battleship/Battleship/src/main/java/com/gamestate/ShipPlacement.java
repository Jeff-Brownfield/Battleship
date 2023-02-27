package main.java.com.gamestate;

public class ShipPlacement {
    private Coordinate placementCell;
    private Grid.ShipDirection direction;

    public ShipPlacement(char row, int column, Grid.ShipDirection direction) {
        row -= 65; //converts from capital letter to number (A -> 1)
        column -= 1; //converts from column display # to array index
        this.placementCell = new Coordinate(row, column);
        this.direction = direction;
    }

    public Coordinate getPlacementCell() {
        return placementCell;
    }

    public Grid.ShipDirection getDirection() {
        return direction;
    }
}
