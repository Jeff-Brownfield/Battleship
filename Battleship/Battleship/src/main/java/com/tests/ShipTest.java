package main.java.com.tests;

import static org.junit.Assert.*;

import main.java.com.gamestate.ShipType;
import org.junit.Test;

import main.java.com.gamestate.Ship;

public class ShipTest {
	@Test
	public void testSize2fail() {

		Ship battleship = new Ship(ShipType.DESTROYER);
		boolean shipSunk = battleship.shipHit();

		assert(!shipSunk);
	}

//	@Test
//	public void testSize1Pass() {
//		Ship battleship = new Ship(ShipType.CARRIER);
//		boolean shipSunk = battleship.shipHit();
//
//		assert(shipSunk);
//	}

	@Test
	public void testSize2LoopFailUnder() {
		boolean shipSunk = false;
		Ship battleship = new Ship(ShipType.DESTROYER);
		for (int i = 0 ; i < 1 ; i++) {
			shipSunk = battleship.shipHit();
		}

		assert(!shipSunk);
	}

	@Test
	public void testSize2LoopFailOver() {
		boolean shipSunk = false;
		Ship battleship = new Ship(ShipType.DESTROYER);
		for (int i = 0 ; i < 3 ; i++) {
			shipSunk = battleship.shipHit();
		}

		assert(!shipSunk);
	}

	@Test
	public void testSize2LoopPass() {
		boolean shipSunk = false;
		Ship battleship = new Ship(ShipType.DESTROYER);
		for (int i = 0 ; i < 2 ; i++) {
			shipSunk = battleship.shipHit();
		}

		assert(shipSunk);
	}

}
