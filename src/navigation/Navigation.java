package navigation;

import account.Login;
import account.Register;
import client.Client;
import client.ContactInformation;
import client.Person;
import utils.ColorManager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Navigation {
    private final Scanner scanner;
    private final AccountService accountService;
    private final ClientService clientService;
    private Register loggedInAccount;

    public Navigation(Scanner scanner, AccountService accountService, ClientService clientService) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.clientService = clientService;
    }

    public void menu() {
        while (true) {
            System.out.println("""
                    Choose what you want to do:\s
                    1. Register
                    2. Login
                    3. Exit
                    """);

            int choice = getChoice();
            switch (choice) {
                case 1:
                    registerMenu();
                    break;
                case 2:
                    loginMenu();
                    break;
                case 3:
                    System.out.println(ColorManager.PURPLE + "Thank you for using WedConnect. Goodbye!" + ColorManager.RESET);
                    return;
                default:
                    System.out.println(ColorManager.RED + "Invalid choice. Please try again." + ColorManager.RESET);
            }
        }
    }

    private int getChoice() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ColorManager.RED + "Invalid input. Please enter a number." + ColorManager.RESET);
            }
        }
    }

    private void registerMenu() {
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        while (true) {
            System.out.println("Confirm your password: ");
            String confirmPassword = scanner.nextLine();

            if (password.equals(confirmPassword)) {
                accountService.register(email, password, confirmPassword);
                break;
            } else {
                System.out.println(ColorManager.RED + "Passwords do not match. Please try again." + ColorManager.RESET);
            }
        }
    }

    private void loginMenu() {
        loggedInAccount = accountService.login(scanner);
        if (loggedInAccount != null) {
            System.out.println(ColorManager.GREEN + "Login successful!" + ColorManager.RESET);
            accountMenu();
        } else {
            System.out.println(ColorManager.RED + "Login failed." + ColorManager.RESET);
        }
    }

    public void accountMenu() {
        while (true) {
            System.out.println("""
                    Choose what you want to do:\s
                    1. Add Client Information
                    2. View Profile
                    3. Edit Profile
                    3. Logout
                    """);

            int choice = getChoice();
            switch (choice) {
                case 1:
                    clientService.addClientMenu(scanner, loggedInAccount);
                    break;
                case 2:
                    viewProfile();
                    break;
                case 3:
                    loggedInAccount = null;
                    System.out.println(ColorManager.GREEN + "Logged out successfully!" + ColorManager.RESET + "\n");
                    return;
                default:
                    System.out.println(ColorManager.RED + "Invalid choice. Please try again." + ColorManager.RESET + "\n");
            }
        }
    }


    private void viewProfile() {
        if (loggedInAccount != null && loggedInAccount.getClient() != null) {
            System.out.println(loggedInAccount.getClient());
        } else {
            System.out.println(ColorManager.RED + "Profile not found." + ColorManager.RESET);
        }
    }
}
