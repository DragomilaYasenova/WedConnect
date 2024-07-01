package navigation;

import account.Register;
import utils.ColorManager;

import java.util.Scanner;

public class LoginNavigation {
    private final Scanner scanner;
    private final AccountService accountService;

    public LoginNavigation(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    public Register login() {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        Register loggedInAccount = accountService.login(username, password);
        if (loggedInAccount != null) {
            System.out.println(ColorManager.GREEN + "Login successful!" + ColorManager.RESET);
            return loggedInAccount;
        } else {
            System.out.println(ColorManager.RED + "Login failed. Please check your username and password." + ColorManager.RESET);
            return null;
        }
    }
}
