package exceptions.client;

public class InvalidCityFormatException extends RuntimeException{
    public InvalidCityFormatException(String message) {
        super(message);
    }
}
