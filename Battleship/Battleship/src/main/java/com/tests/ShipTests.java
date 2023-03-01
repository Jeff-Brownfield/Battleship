package main.java.com.tests;

import main.java.com.gamestate.ShipType;
import org.junit.Test;

import main.java.com.gamestate.Ship;

public class ShipTests {

	@Test
	public void testShipHealthInitialValueSetCorrectly() {
		Ship carrier = new Ship(ShipType.CARRIER);
		assert(carrier.getShipHealth() == 5);
		Ship battleship = new Ship(ShipType.BATTLESHIP);
		assert(battleship.getShipHealth() == 4);
		Ship cruiser = new Ship(ShipType.CRUISER);
		assert(cruiser.getShipHealth() == 3);
		Ship submarine = new Ship(ShipType.SUBMARINE);
		assert(submarine.getShipHealth() == 3);
		Ship destroyer = new Ship(ShipType.DESTROYER);
		assert(destroyer.getShipHealth() == 2);
	}

	@Test
	public void testShipHealthUpdatingCorrectly() {
		Ship ship = new Ship(ShipType.DESTROYER);
		assert(ship.getShipHealth() == 2);

		ship.shipHit();
		assert(ship.getShipHealth() == 1);

		ship.shipHit();
		assert(ship.getShipHealth() == 0);
	}

	@Test
	public void testSize2fail() {

		Ship ship = new Ship(ShipType.DESTROYER);
		boolean shipSunk = ship.shipHit();

		assert(!shipSunk);
	}

	@Test
	public void testSize2LoopFailUnder() {
		boolean shipSunk = false;
		Ship ship = new Ship(ShipType.DESTROYER);
		for (int i = 0 ; i < 1 ; i++) {
			shipSunk = ship.shipHit();
		}

		assert(!shipSunk);
	}

	@Test
	public void testSize2LoopFailOver() {
		boolean shipSunk = false;
		Ship ship = new Ship(ShipType.DESTROYER);
		for (int i = 0 ; i < 3 ; i++) {
			shipSunk = ship.shipHit();
		}
		assert(!shipSunk);
	}

	@Test
	public void testSize2LoopPass() {
		boolean shipSunk = false;
		Ship ship = new Ship(ShipType.DESTROYER);
		for (int i = 0 ; i < 2 ; i++) {
			shipSunk = ship.shipHit();
		}

		assert(shipSunk);
	}
}
