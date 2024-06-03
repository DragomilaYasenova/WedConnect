package exceptions;

import utils.ColorManager;

public class PasswordsDoNotMatchException extends Exception {
    public PasswordsDoNotMatchException() {
        super(ColorManager.RED + "Passwords do not match." + ColorManager.RESET + "\n");
    }
}
