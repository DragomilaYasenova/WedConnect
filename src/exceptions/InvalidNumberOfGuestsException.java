package exceptions;

public class InvalidNumberOfGuestsException extends RuntimeException {
    public InvalidNumberOfGuestsException(String message) {
        super(message);
    }
}
