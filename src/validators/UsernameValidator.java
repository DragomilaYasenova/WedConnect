package validators;

public class UsernameValidator {
    public static boolean nullUsername(String username) {
        return username.isEmpty();
    }
}
