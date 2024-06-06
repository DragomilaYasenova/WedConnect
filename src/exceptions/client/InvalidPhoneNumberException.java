package exceptions.client;

public class InvalidPhoneNumberException extends RuntimeException{
    public InvalidPhoneNumberException(String message) {
        super(message);
    }

}
