package exceptions.password;

public class PasswordCannotBeNullException extends RuntimeException {
    public PasswordCannotBeNullException(String message) {
        super(message);
    }
}