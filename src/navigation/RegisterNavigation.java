package navigation;

import account.Register;
import exceptions.UsernameCannotBeNullException;
import exceptions.account.AccountAlreadyExistsException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;
import utils.ColorManager;

import java.util.Scanner;

public class RegisterNavigation {
    private final Scanner scanner;
    private final AccountService accountService;

    public RegisterNavigation(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    public void register() {
        String username;
        String password;
        String confirmPassword;

        while (true) {
            System.out.println("Enter your username: ");
            username = scanner.nextLine();

            try {
                if (username.trim().isEmpty()) {
                    throw new UsernameCannotBeNullException("Username cannot be null.");
                }
                if (accountService.accountExists(username.trim())) {
                    throw new AccountAlreadyExistsException("An account with username " + username + " is already registered.");
                }
                break;
            } catch (UsernameCannotBeNullException | AccountAlreadyExistsException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            System.out.println("Enter your password: ");
            password = scanner.nextLine();
            System.out.println("Confirm your password: ");
            confirmPassword = scanner.nextLine();

            try {
                if (password.trim().isEmpty()) {
                    throw new PasswordCannotBeNullException("Password cannot be null.");
                }
                if (!password.equals(confirmPassword)) {
                    throw new PasswordsDoNotMatchException("Passwords do not match.");
                }
                accountService.register(username, password, confirmPassword);
                System.out.println(ColorManager.GREEN + "Account successfully registered." + ColorManager.RESET + "\n");
                break;
            } catch (PasswordCannotBeNullException | PasswordsDoNotMatchException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }
    }
}
