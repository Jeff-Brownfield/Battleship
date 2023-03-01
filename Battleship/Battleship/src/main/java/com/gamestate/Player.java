package main.java.com.gamestate;

import java.util.Map;

public abstract class Player {
	private static int numberOfPlayers = 0;
	private Map<ShipType, Ship> ships;
	private Grid grid;
	private String name;

	public Player(String playerType) {
		numberOfPlayers++;
		grid = new Grid();
		name = playerType + " " + numberOfPlayers;
		this.ships = Ship.getShips();
	}

	public Grid getGrid() {
		return this.grid;
	}

	public String getName() {
		return name;
	}

	public Map<ShipType, Ship> getShips() {
		return ships;
	}
}