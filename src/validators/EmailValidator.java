package validators;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex == -1 || atIndex == 0 || atIndex == email.length() - 1) {
            return false;
        }

        int dotIndex = email.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex < atIndex || dotIndex == email.length() - 1) {
            return false;
        }

        for (char c : email.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '@' && c != '.' && c != '_' && c != '+' && c != '-') {
                return false;
            }
        }

        return true;
    }
}
