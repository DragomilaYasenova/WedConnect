package validators;

public class PasswordValidator {
    public static boolean passwordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean nullPassword(String password) {
        return password.isEmpty();
    }
}