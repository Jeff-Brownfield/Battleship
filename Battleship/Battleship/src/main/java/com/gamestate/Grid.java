package main.java.com.gamestate;

import java.util.ArrayList;
import java.util.List;

import main.java.com.gamecontroller.InvalidCoordinatesException;
import main.java.com.gamestate.Cell.ShotResult;


public class Grid {

	private Cell[][] gridCells;
	private int shipsSunk = 0;
	public enum ShipDirection {
		VERTICAL, HORIZONTAL
	} 
	
	public Grid(){
		gridCells = new Cell[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				gridCells[i][j] = new Cell();
			}
		}
	}

	public int getShipsSunk() {
		return shipsSunk;
	}

	public void sinkShip() {
		shipsSunk++;
	}

	private void setShip(List<Cell> impactedCells, ShipType shipType, Ship ship) {
		for (Cell cell: impactedCells) {
			cell.setShipPresent(true);
			cell.setShipType(shipType);
			cell.setShip(ship);
		}
	}
	
	public ShotResult takeAShot(Player activePlayer, Coordinate coordinate) throws InvalidCoordinatesException {
		return gridCells[coordinate.getRow()][coordinate.getColumn()].shotsFired(activePlayer); // Y and X are swapped to match expectation that Y is vertical and X is horizontal elsewhere.
	}
	
		// (x,y) is assumed to be upper-left most cell for placement
	public boolean placeShip(Ship ship, ShipType shipType, ShipPlacement placement) {
		List<Cell> impactedCells = returnImpactedCells(shipType.getShipLength(), placement);
		boolean cellsAreInBounds = impactedCells != null;
		boolean placementAvailable = cellsAreInBounds && checkCellsNotOccupied(impactedCells);
		if (placementAvailable) {
			setShip(impactedCells, shipType, ship);
			return true;
		} else {
			return false;
		}
	}

	private boolean checkCellsNotOccupied(List<Cell> impactedCells) {
		boolean cellNotOccupied = true;
		for (Cell cell: impactedCells) {
			if (cell.isShipPresent()) {
				cellNotOccupied = false;
			}
		}
		return cellNotOccupied;
	}

	private List<Cell> returnImpactedCells(int shipLength, ShipPlacement placement) {
		int column = placement.getPlacementCell().getColumn();
		int row = placement.getPlacementCell().getRow();
		List<Cell> cells = new ArrayList<>();
		int horizontalTravel = 0;
		int verticalTravel = 0;
		switch (placement.getDirection()) {
			case HORIZONTAL: horizontalTravel = shipLength - 1; break;
			case VERTICAL: verticalTravel = shipLength - 1; break;
			}
		int rightIndex = column + horizontalTravel;
		int bottomIndex = row + verticalTravel;
		
		if (rightIndex > 9 || bottomIndex > 9) {
			return null;
		}
				
		for (int i = 0; i < shipLength; i++) {
			if (horizontalTravel > 0) {
				cells.add(gridCells[row][column+i]);
			} else {
				cells.add(gridCells[row+i][column]);
			}
		}
		
		return cells;
	}

	public ShotResult returnCellDisplayValue (int i, int j, boolean isGameOver) {
		return gridCells[i][j].getDisplayValue(isGameOver);

	}

	public boolean returnIsOccupied (int i, int j) {
		return gridCells[i][j].isShipPresent();
	}

}
