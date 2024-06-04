package validators;

public class PhoneNumberValidator {
    public static boolean isValidPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return false;
        }

        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
