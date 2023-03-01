package main.java.com.tests;

import main.java.com.gamestate.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GridTests {
    Player player;
    Grid grid;

    @Before
    public void setup() {
         player = new HumanPlayer();
         grid = player.getGrid();
    }

    @Test
    public void testGetShipsSunk() {
        Assert.assertEquals(0, grid.getShipsSunk());
    }

    @Test
    public void testSinkShip() {
        grid.sinkShip();
        Assert.assertEquals(1, grid.getShipsSunk());
    }

    @Test
    public void testPlaceShipInvalid() {
        Ship carrier = new Ship(ShipType.CARRIER);
        ShipPlacement placement = new ShipPlacement('H', 8, Grid.ShipDirection.VERTICAL);

        boolean isPlacementValid = grid.placeShip(carrier, placement);
        Assert.assertFalse(isPlacementValid);

    }
}
