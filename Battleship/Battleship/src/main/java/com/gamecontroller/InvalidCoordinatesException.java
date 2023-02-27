package main.java.com.gamecontroller;

public class InvalidCoordinatesException extends Exception {
    private String message = "";

    public InvalidCoordinatesException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
