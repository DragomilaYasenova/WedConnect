package account;

public class PasswordManager {
    public boolean passwordsMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}