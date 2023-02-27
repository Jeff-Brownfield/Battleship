package main.java.com.gamestate;

import main.java.com.gamecontroller.InvalidCoordinatesException;

public class Cell {
	private boolean takenFire;
	private boolean shipPresent;
	public ShipType shipType;
	private Ship ship;

	public Cell() {
		this.takenFire = false;
		this.shipPresent = false;
		this.shipType = null;
		this.ship = null;
	}

	public enum ShotResult {
		INVALID, HIT, MISS, BATTLESHIP_SUNK, CARRIER_SUNK, CRUISER_SUNK, SUBMARINE_SUNK, DESTROYER_SUNK, EMPTY, UNDAMAGED;
	}
	
	public ShotResult shotsFired(Player activePlayer) throws InvalidCoordinatesException {
		if (takenFire) {
			throw new InvalidCoordinatesException("Coordinates were invalid. A shot has already been fired at this location.");
		}
		takenFire = true;
		if (shipPresent) {
			boolean sunk = getShip().shipHit();
			if(sunk) {activePlayer.getGrid().sinkShip();}
			return sunk ? identifySunkenShip(): ShotResult.HIT;
		} else {return ShotResult.MISS;}
	}

	private ShotResult identifySunkenShip() {
		switch (shipType) {
			case CARRIER: return ShotResult.CARRIER_SUNK;
			case BATTLESHIP: return ShotResult.BATTLESHIP_SUNK;
			case CRUISER: return ShotResult.CRUISER_SUNK;
			case SUBMARINE: return ShotResult.SUBMARINE_SUNK;
			case DESTROYER: return ShotResult.DESTROYER_SUNK;
			}
		return ShotResult.INVALID;
		
	}
	
	public void setShipPresent(boolean newValue) {
		shipPresent = newValue;
	}
	
	public boolean isShipPresent() {
		return shipPresent;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}
		
	public ShotResult getDisplayValue(boolean isGameOver) {
		if (takenFire && shipPresent) {
			return ShotResult.HIT;
		} else if (shipPresent && isGameOver) {
			return ShotResult.UNDAMAGED;
		} else if (takenFire) {
			return ShotResult.MISS;
		} else {
			return ShotResult.EMPTY;
		}
	}
	
	
}
