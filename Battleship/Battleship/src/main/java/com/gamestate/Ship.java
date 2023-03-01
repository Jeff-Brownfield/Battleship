package main.java.com.gamestate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static main.java.com.gamestate.ShipType.*;

public class Ship {

	private boolean isSunk = false;
	private int shipHealth;
	private ShipType type;

	
	public Ship(ShipType shipType) {
		this.shipHealth = shipType.getShipLength();
		this.type = shipType;
	}
	
	public boolean shipHit() {
		this.shipHealth -= 1;
		isSunk = (this.shipHealth == 0);
		return isSunk;
	}
	
	
	
	public static Map<ShipType, Ship> getShips() {
		Map<ShipType, Ship> ships = new LinkedHashMap<>();
		ships.put(CARRIER, new Ship(CARRIER));
		ships.put(BATTLESHIP, new Ship(BATTLESHIP));
		ships.put(SUBMARINE, new Ship(SUBMARINE));
		ships.put(CRUISER, new Ship(CRUISER));
		ships.put(DESTROYER, new Ship(DESTROYER));
		return ships;
	}
	
	public int getShipHealth() {
		return this.shipHealth;
	}

	public ShipType getType() {
		return type;
	}
}
