package main.java.com.gamecontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;


import main.java.com.gamestate.*;
import main.java.com.view.UserInterface;

public class Battleship {
	private static Battleship game;
	private Player player1;
	private Player player2;
	private List<Player> players = new LinkedList<>();
	private boolean isGameInProgress;
	private static int gameTurn = 1;
	private static UserInterface ui = new UserInterface();

	public static void main(String[] args) {
		Player player1 = new HumanPlayer();
		Player player2 = new HumanPlayer();
		game = new Battleship(player1, player2);
		game.run();

	}

	public void run() {

		placeShips();
		while (isGameInProgress) {
			gameTurn();
			if(checkForWin()) {
				isGameInProgress = false;
			} else {
				gameTurn++;
			}
		}
		generateGameSummary();

	}

	private void generateGameSummary() {
		ui.printMessage("clear");
		String winnerName = activePlayer().getName();
		ui.printGameSummary(activePlayer().getGrid(), opposingPlayer().getGrid(), winnerName);
	}

	private void placeShips() {
		for (int i = 0; i < players.size(); i++){
			ui.printMessage(players.get(i).getName() + ", prepare to deploy your ships!");
			//TODO exchange for autoPlaceShips() when finished
			getShipPlacement(players.get(i), i);
			//
			//
			// autoPlaceShips(players.get(i), i);
			ui.clearConsole();  //System.out.println("clear");
		}
	}


	private boolean checkForWin() {
		if (player1.getGrid().getShipsSunk() == 5 || player2.getGrid().getShipsSunk() == 5) {
			return true;
		}
		return false;
	}


	public Player activePlayer() {
		return players.get(activePlayerIndex());
	}

	public Player opposingPlayer() {
		return players.get(opposingPlayerIndex());
	}
	public int activePlayerIndex() {
		return (gameTurn - 1) % players.size();
	}

	public int opposingPlayerIndex() {
		return (gameTurn % players.size());
	}



	public Battleship(Player player1, Player player2) {
		this.player1 = player1;
		players.add(player1);
		this.player2 = player2;
		players.add(player2);
		this.isGameInProgress = true;
	}


	private void gameTurn() {
		boolean isResultInvalid = true;
		while (isResultInvalid) {
			ui.printBoardStatus(opposingPlayer().getGrid(), opposingPlayerIndex());
			ui.printMessage(activePlayer().getName() + ", take aim!");
			ui.printMessage("So far there are "	+ activePlayer().getGrid().getShipsSunk() + " ships sunk.");
			isResultInvalid = attemptTakeAShot();
		}
	}

	private boolean attemptTakeAShot() {
		Coordinate coordinate;
		Cell.ShotResult result;
		boolean isResultInvalid = true;
		try {
			coordinate = new Coordinate(ui.promptForCoordinates("Enter coordinates for shot: "));

			// Attempt to apply shot
			result = opposingPlayer().getGrid().takeAShot(activePlayer(), coordinate);
			ui.clearConsole(); //ui.printMessage("clear");

			// Display updated opponent board state and shot result
			afterShotDisplayUpdate(coordinate, result);
			ui.clearConsole(); //ui.printMessage("clear");
			isResultInvalid = false;
		} catch (InvalidCoordinatesException e) {
			ui.printMessage(e.getMessage());
		} catch (Exception ex) {
			ui.printMessage("Column must be entered as a number from 1 to 10.");
		}
		return isResultInvalid;
	}

	private void afterShotDisplayUpdate(Coordinate coordinate, Cell.ShotResult result) {
		ui.clearConsole();  //ui.printMessage("clear");
		ui.printBoardStatus(opposingPlayer().getGrid(), opposingPlayerIndex());
		ui.printMessageWithPause(activePlayer().getName() + " shoots! Result: " + result + " at: "
				+ coordinate.getDisplayRow() +  coordinate.getDisplayColumn() + ". So far there are "
				+ activePlayer().getGrid().getShipsSunk() + " ships sunk.");
	}


	private void getShipPlacement(Player player, int i) {
		for(Map.Entry<ShipType, Ship> ship : player.getShips().entrySet()){
			boolean shipPlacementIsLegal = false;
			while(!shipPlacementIsLegal) {
				ui.printBoardSetupStatus(player.getGrid(), i);
				try {
					ShipPlacement placement = ui.promptForShipPlacement(ship.getKey());
					shipPlacementIsLegal = player.getGrid().placeShip(ship.getValue(),
							ship.getKey(), placement);
				} catch (InvalidCoordinatesException e) {
					ui.printMessage(e.getMessage());
				} catch (Exception ex) {
					System.out.println("Column must be entered as a number from 1 to 10.");
				}
			}
			ui.clearConsole();  //System.out.println("clear");
		}
	}


	private void autoPlaceShips(Player player, int offset) {
		//TODO remove when finished
		List<ShipPlacement> placements = new ArrayList<ShipPlacement>();
		placements.add(new ShipPlacement('A', 1 + offset, Grid.ShipDirection.HORIZONTAL));
		placements.add(new ShipPlacement('B', 1 + offset, Grid.ShipDirection.HORIZONTAL));
		placements.add(new ShipPlacement('C', 1 + offset, Grid.ShipDirection.HORIZONTAL));
		placements.add(new ShipPlacement('D', 1 + offset, Grid.ShipDirection.HORIZONTAL));
		placements.add(new ShipPlacement('E', 1 + offset, Grid.ShipDirection.HORIZONTAL));

		List<Ship> ships = new ArrayList<>();
		ships.add(new Ship(ShipType.CARRIER));
		ships.add(new Ship(ShipType.BATTLESHIP));
		ships.add(new Ship(ShipType.SUBMARINE));
		ships.add(new Ship(ShipType.CRUISER));
		ships.add(new Ship(ShipType.DESTROYER));

		List<ShipType> shipType = new ArrayList<>();
		shipType.add(ShipType.CARRIER);
		shipType.add(ShipType.BATTLESHIP);
		shipType.add(ShipType.SUBMARINE);
		shipType.add(ShipType.CRUISER);
		shipType.add(ShipType.DESTROYER);

		for(int i = 0; i < 5; i++){
			boolean shipPlacementIsLegal = false;
			while(!shipPlacementIsLegal) {
				ShipPlacement placement = placements.get(i);
				shipPlacementIsLegal = player.getGrid().placeShip(
						ships.get(i), shipType.get(i), placement);
			}
			ui.clearConsole();  //System.out.println("clear");
		}
	}
}
