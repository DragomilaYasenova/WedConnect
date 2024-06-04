package validators;

public class StringValidator {
    public static boolean isValidString(String input) {
        return input.matches("[a-zA-Z]+");
    }
}