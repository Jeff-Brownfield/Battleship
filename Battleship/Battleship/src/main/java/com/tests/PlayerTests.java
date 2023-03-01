package main.java.com.tests;

import main.java.com.gamestate.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class PlayerTests {
    private Player humanPlayer;
    private Player computerPlayer;

    @Before
    public void setup() {
        humanPlayer = new HumanPlayer();
        computerPlayer = new ComputerPlayer();
    }

    @Test
    public void testGetGrid() {
        Assert.assertNotNull(humanPlayer.getGrid());
        Assert.assertNotNull(computerPlayer.getGrid());
    }

    @Test
    public void testGetName() {
        String firstPlayerName = humanPlayer.getName();
        Assert.assertEquals("Player 1", firstPlayerName);
        String secondPlayerName = computerPlayer.getName();
        Assert.assertEquals("Computer 2", secondPlayerName);
        String anotherPlayerName = new HumanPlayer().getName();
        Assert.assertEquals("Player 3", anotherPlayerName);
    }

    @Test
    public void testGetShips() {
        Map<ShipType, Ship> ships = humanPlayer.getShips();
        Assert.assertEquals(5, ships.size());
        Assert.assertTrue(ships.containsKey(ShipType.CARRIER));
    }

}
