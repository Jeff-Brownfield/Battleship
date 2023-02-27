package main.java.com.gamestate;

public enum ShipType {

	CARRIER(5),
	BATTLESHIP(4),
	SUBMARINE(3),
	CRUISER(3),
	DESTROYER(2),
	NONE(0);

	private int shipLength;
	
	private ShipType(int shipLength) {
		this.shipLength = shipLength;
	}

	public int getShipLength() {
		return shipLength;
	}

}
