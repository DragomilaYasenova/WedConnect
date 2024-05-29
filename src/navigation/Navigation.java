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
    private final Map<String, Register> accounts;
    private Register loggedInAccount;
    private final Login login;

    public Navigation(Scanner scanner) {
        this.scanner = scanner;
        this.accounts = new HashMap<>();
        this.login = new Login(accounts);
    }

    public void menu() {
        while (true) {
            System.out.println("""
                    Choose what you want to do:\s
                    1. Register
                    2. Login
                    3. Exit
                    """);

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ColorManager.RED + "Invalid choice. Please enter a number." + ColorManager.RESET);
                continue;
            }

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

    private void loginMenu() {
        loggedInAccount = login.loginMenu(scanner);
        if (loggedInAccount != null) {
            accountMenu();
        }
    }

    public void registerMenu() {
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        Register register = new Register(email, "", "");
        String password = register.enterValidPassword(scanner);
        register.setPassword(password);
        register.saveAccount();
        accounts.put(email, register);
    }

    public void accountMenu() {
        while (true) {
            System.out.println(ColorManager.PURPLE + "Account Menu" + ColorManager.RESET + "\n" +
                    "Choose what you want to do: " + "\n" +
                    "1. Add Client Information" + "\n" +
                    "2. Logout" + "\n");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(ColorManager.RED + "Invalid choice. Please enter a number." + ColorManager.RESET + "\n");
                continue;
            }

            switch (choice) {
                case 1:
                    addInformationToAccount();
                    break;
                case 2:
                    loggedInAccount = null;
                    System.out.println(ColorManager.GREEN + "Logged out successfully!" + ColorManager.RESET + "\n");
                    return;
                default:
                    System.out.println(ColorManager.RED + "Invalid choice. Please try again." + ColorManager.RESET + "\n");
            }
        }
    }

    public void addInformationToAccount() {
        if (loggedInAccount == null) {
            System.out.println(ColorManager.RED + "You need to log in first." + ColorManager.RESET + "\n");
            return;
        }

        System.out.println("Enter bride's first name: ");
        String brideFirstName = scanner.nextLine();
        System.out.println("Enter bride's last name: ");
        String brideLastName = scanner.nextLine();
        Person bride = new Person(brideFirstName, brideLastName);

        System.out.println("Enter groom's first name: ");
        String groomFirstName = scanner.nextLine();
        System.out.println("Enter groom's last name: ");
        String groomLastName = scanner.nextLine();
        Person groom = new Person(groomFirstName, groomLastName);

        System.out.println("Enter bride's email: ");
        String brideEmail = scanner.nextLine();
        System.out.println("Enter bride's phone: ");
        String bridePhone = scanner.nextLine();
        System.out.println("Enter bride's city: ");
        String brideCity = scanner.nextLine();
        ContactInformation brideContactInfo = new ContactInformation(bride, brideEmail, bridePhone, brideCity);

        System.out.println("Enter groom's email: ");
        String groomEmail = scanner.nextLine();
        System.out.println("Enter groom's phone: ");
        String groomPhone = scanner.nextLine();
        System.out.println("Enter groom's city: ");
        String groomCity = scanner.nextLine();
        ContactInformation groomContactInfo = new ContactInformation(groom, groomEmail, groomPhone, groomCity);

        System.out.println("Enter budget: ");
        BigDecimal budget = new BigDecimal(scanner.nextLine());

        Client client = new Client(bride, groom, brideContactInfo, groomContactInfo, budget);
        loggedInAccount.setClient(client);
        System.out.println(ColorManager.GREEN + "Client information added successfully!" + ColorManager.RESET);
        System.out.println(client);
    }
}
