package exceptions.client;

public class InvalidEmailAddressException extends RuntimeException {
    public InvalidEmailAddressException(String message) {
        super(message);
    }
}
