package exceptions.client;

public class InvalidLastNameException extends RuntimeException {
    public InvalidLastNameException(String message) {
        super(message);
    }
}
