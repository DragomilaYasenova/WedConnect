package exceptions;

import utils.ColorManager;

public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException(String email) {
        super(ColorManager.RED + "An account with email " + email + " is already registered." + ColorManager.RESET + "\n");
    }
}
