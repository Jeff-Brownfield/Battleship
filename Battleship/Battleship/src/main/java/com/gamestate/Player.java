package main.java.com.gamestate;

import java.util.Map;
import java.util.Scanner;

public abstract class Player {
	private static int numberOfPlayers = 0;
	private Map<ShipType, Ship> ships;
	private Grid grid;
	private String name;

	
	public Player() {
		numberOfPlayers++;
		grid = new Grid();
		name = "Player ".concat(String.valueOf(numberOfPlayers));
		this.ships = Ship.getShips();
//		getShipPlacement(grid, ships);
	}

	public abstract void getShipPlacement(Grid grid, Map<ShipType, Ship> ships);
	
	public Grid getGrid() {
		return this.grid;
	}

	public String getName() {
		return name;
	}

	public Map<ShipType, Ship> getShips() {
		return ships;
	}

	public abstract Coordinate getShotCoordinates();


}