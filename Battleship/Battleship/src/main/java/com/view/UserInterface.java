package main.java.com.view;

import main.java.com.gamecontroller.InvalidCoordinatesException;
import main.java.com.gamestate.*;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserInterface {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final char[] ROW_LETTER = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void printBoard(String[][] displayGrid, String whosBoard) {
        String headerFormat = "%2s |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s |";
        String format = "%2s |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |  %s  |";
        System.out.println(String.format(headerFormat, "\nP" + whosBoard, "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        for (int i = 0; i < 10; i++) {
            System.out.println(" -- ".repeat(16));
            System.out.println(String.format(format, " " + (ROW_LETTER[i]), displayGrid[i][0], displayGrid[i][1],
                    displayGrid[i][2], displayGrid[i][3], displayGrid[i][4], displayGrid[i][5],
                    displayGrid[i][6], displayGrid[i][7], displayGrid[i][8], displayGrid[i][9]));
        }
        System.out.println("\n");
    }

    public void printBoardSetupStatus(Grid grid, int activePlayerIndex) {

        String[][] displayGrid = new String[10][10];
        String whosBoard = String.valueOf(activePlayerIndex + 1);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boolean shipPresent = grid.returnIsOccupied(i, j);
                if (shipPresent) {
                    displayGrid[i][j] = ANSI_RED + "@" + ANSI_RESET; //ANSI_RED + '\u2299' + ANSI_RESET;
                } else {
                    displayGrid[i][j] = " ";
                }
            }
        }

        printBoard(displayGrid, whosBoard);

    }

    public void printBoardStatus(Grid grid, int opposingPlayerIndex) {
        String[][] displayGrid = new String[10][10];
        String whosBoard = String.valueOf(opposingPlayerIndex + 1);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell.ShotResult result = grid.returnCellDisplayValue(i, j, false);
                String str = "";
                switch (result) {
                    case EMPTY: displayGrid[i][j] = " ";
                    break;
                    case HIT: displayGrid[i][j] = ANSI_RED + "@" + ANSI_RESET; //ANSI_RED + '\u2600' + ANSI_RESET;
                    break;
                    case MISS: displayGrid[i][j] = ANSI_BLUE + "@" + ANSI_RESET; //ANSI_BLUE + '\u26AB' + ANSI_RESET;
                    break;
                }
            }
        }

        printBoard(displayGrid, whosBoard);
    }

    public String promptForCoordinates(String message) throws Exception{
        System.out.print(message);
        String input = scanner.nextLine().toUpperCase(); // Get what the user types as ALL CAPS.
        int column = Integer.parseInt(input.substring(1));
        if (input.length() < 2 || input.length() > 3) {
            throw new InvalidCoordinatesException(input + " is too long.");
        } else if (input.charAt(0) < 'A' || input.charAt(0) > 'J') {
            throw new InvalidCoordinatesException(input + " doesn't start with a valid row. Acceptable values are letters A through J.");
        } else if (column < 1 || column > 10) {
            throw new InvalidCoordinatesException(input + " doesn't contain a valid column. Acceptable values are numbers 1 through 10.");
        } else {
            return input;
        }
    }

    public Grid.ShipDirection promptForDirection(String message) throws Exception{
        System.out.print(message);
        String input = scanner.nextLine().toUpperCase();
        if (input.equals("H")) {
            return Grid.ShipDirection.HORIZONTAL;
        } else if (input.equals("V")) {
            return Grid.ShipDirection.VERTICAL;
        } else {
            throw new InvalidCoordinatesException(input + " isn't a valid input for direction.");
        }
    }

    public void clearConsole() {
        System.out.println("clear");
        for(int i = 0; i < 1000; i++) {
            System.out.println("");
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessageWithPause(String message) {
        System.out.println(message);
        System.out.print("Hit enter to continue.");
        scanner.nextLine();
    }

    public ShipPlacement promptForShipPlacement(ShipType shipType) throws Exception {
        String message = "Enter coordinates to place your " + shipType.name() + ": ";
        String coordinates = promptForCoordinates(message);
        char row = coordinates.charAt(0);
        int column = Integer.parseInt(coordinates.substring(1));

        message = "Enter 'H' for HORIZONTAL or 'V' for VERTICAL placement :";
        Grid.ShipDirection direction = promptForDirection(message);

        return new ShipPlacement(row, column, direction);
    }

    public void printGameSummary(Grid winnerGrid, Grid loserGrid, String winnerName) {
        printMessage(winnerName + " WINS!");
        String winningPlayerNumber = winnerName.substring(winnerName.length()-1);
        String losingPlayerNumber = winningPlayerNumber.equalsIgnoreCase("1") ? "2" : "1";
        String[][] displayGrid = displayGridMaker(winnerGrid, true);
        printBoard(displayGrid, winningPlayerNumber);
        displayGrid = displayGridMaker(loserGrid, true);
        printBoard(displayGrid, losingPlayerNumber);

    }

    private String[][] displayGridMaker(Grid grid, boolean isGameOver) {
        String[][] displayGrid = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell.ShotResult result = grid.returnCellDisplayValue(i, j, isGameOver);
                String str = "";
                if(result == Cell.ShotResult.UNDAMAGED) {
                    displayGrid[i][j] = ANSI_YELLOW + "@" + ANSI_RESET; //ANSI_YELLOW + '\u2600' + ANSI_RESET;
                } else if (result == Cell.ShotResult.EMPTY) {
                    displayGrid[i][j] = " ";
                } else if (result == Cell.ShotResult.MISS) {
                    displayGrid[i][j] = ANSI_BLUE + "@" + ANSI_RESET; //ANSI_BLUE + '\u26AB' + ANSI_RESET;
                } else {
                    displayGrid[i][j] = ANSI_RED + "@" + ANSI_RESET; //b6ANSI_RED + '\u2600' + ANSI_RESET;
                }
            }
        }
        return displayGrid;
    }
}
