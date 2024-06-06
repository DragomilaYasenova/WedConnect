package exceptions.client;

public class InvalidFirstNameException extends RuntimeException {
    public InvalidFirstNameException(String message) {
        super(message);
    }
}
