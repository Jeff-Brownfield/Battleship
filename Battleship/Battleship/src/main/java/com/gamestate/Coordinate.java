package main.java.com.gamestate;

public class Coordinate {

	private int row;
	private int column;
	
	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public Coordinate(String coordinate) {
		this.row = coordinate.charAt(0) - 65; //converts from capital letter to number (A -> 1)
		this.column = Integer.parseInt(coordinate.substring(1)) - 1; //converts from column display # to array index
	}

	public int getRow() {
		return row;
	}

	public char getDisplayRow() {
		return (char)(row + 65);
	}
	
	public int getColumn() {
		return column;
	}

	public String getDisplayColumn() {
		return String.valueOf(column + 1);
	}
}
