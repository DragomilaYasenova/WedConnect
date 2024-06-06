package navigation;

import account.Register;
import client.CustomerPreferences;
import exceptions.InvalidNumberOfGuestsException;
import exceptions.UsernameCannotBeNullException;
import exceptions.account.AccountAlreadyExistsException;
import exceptions.client.InvalidDateFormatException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;
import restaurant.Menu.MenuOptions;
import restaurant.Restaurant;
import restaurant.RestaurantOfferAlgorithm;
import restaurant.amenity.Amenity;
import utils.ColorManager;
import utils.FileOperations;
import validators.DateValidator;

import java.util.*;

public class Navigation {
    private final Scanner scanner;
    private final AccountService accountService;
    private final ClientService clientService;
    private Register loggedInAccount;

    private final RestaurantOfferAlgorithm offerAlgorithm;
    private final Set<MenuOptions> chosenMenuPreferences;
    private final Set<Amenity> chosenAmenityPreferences;
    private static List<Restaurant> restaurants = null;
    private static String date;
    private static int numberOfGuests;
    private static String id;

    public Navigation(Scanner scanner, AccountService accountService, ClientService clientService, List<Restaurant> restaurants) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.clientService = clientService;
        this.restaurants = restaurants;
        this.offerAlgorithm = new RestaurantOfferAlgorithm();
        this.chosenMenuPreferences = new HashSet<>();
        this.chosenAmenityPreferences = new HashSet<>();
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
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        while (true) {
            System.out.println("Confirm your password: ");
            String confirmPassword = scanner.nextLine();

            if (password.equals(confirmPassword)) {
                try {
                    accountService.register(username, password, confirmPassword);
                    break;
                } catch (UsernameCannotBeNullException | PasswordCannotBeNullException | AccountAlreadyExistsException e) {
                    System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
                    return;
                } catch (PasswordsDoNotMatchException e){
                    System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
                }
            } else {
                System.out.println(ColorManager.RED + "Passwords do not match. Please try again." + ColorManager.RESET);
            }
        }
    }

    private void loginMenu() {
        loggedInAccount = accountService.login(scanner);
        if (loggedInAccount != null) {
            System.out.println(ColorManager.GREEN + "Login successful!" + ColorManager.RESET);
            id = accountService.getId();
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
                    3. Choose Preferences
                    4. Check Best Suitable Restaurant
                    5. Logout
                    """);

            int choice = getChoice();
            switch (choice) {
                case 1:
                    clientService.addClientMenu(scanner, loggedInAccount);
                    break;
                case 2:
                    if (loggedInAccount != null) {
                        viewProfile("./clients/" + loggedInAccount.getClientIdFromFile() + ".txt");
                    } else {
                        System.out.println(ColorManager.RED + "No logged in account found." + ColorManager.RESET + "\n");
                    }
                    break;
                case 3:
                    choosePreferences();
                    break;
                case 4:
                    checkBestSuitableRestaurant();
                    break;
                case 5:
                    loggedInAccount = null;
                    System.out.println(ColorManager.GREEN + "Logged out successfully!" + ColorManager.RESET + "\n");
                    return;
                default:
                    System.out.println(ColorManager.RED + "Invalid choice. Please try again." + ColorManager.RESET + "\n");
            }
        }
    }

    private void viewProfile(String fileName) {
        String fileContent = FileOperations.readFile(fileName);
        if (fileContent != null && !fileContent.isEmpty()) {
            System.out.println("Profile Information:");
            System.out.println(fileContent);
        } else {
            System.out.println(ColorManager.RED + "Profile information not found." + ColorManager.RESET);
        }
    }

    private void choosePreferences() {
        boolean exitLoop = false;

        System.out.println("""
                Choose your amenity preferences:\s
                1. Hotel
                2. Beach
                3. Countryside
                4. Pool
                5. Garden
                6. Ballroom
                7. Terrace
                8. Rooftop
                9. Banquet Hall
                0 to exit
                """);

        while (!exitLoop) {
            int amenityChoice = getChoice();
            switch (amenityChoice) {
                case 0:
                    exitLoop = true;
                    break;
                case 1:
                    chosenAmenityPreferences.add(Amenity.HOTEL);
                    break;
                case 2:
                    chosenAmenityPreferences.add(Amenity.BEACH);
                    break;
                case 3:
                    chosenAmenityPreferences.add(Amenity.COUNTRYSIDE);
                    break;
                case 4:
                    chosenAmenityPreferences.add(Amenity.POOL);
                    break;
                case 5:
                    chosenAmenityPreferences.add(Amenity.GARDEN);
                    break;
                case 6:
                    chosenAmenityPreferences.add(Amenity.BALLROOM);
                    break;
                case 7:
                    chosenAmenityPreferences.add(Amenity.TERRACE);
                    break;
                case 8:
                    chosenAmenityPreferences.add(Amenity.ROOFTOP);
                    break;
                case 9:
                    chosenAmenityPreferences.add(Amenity.BANQUET_HALL);
                    break;

                default:
                    System.out.println(ColorManager.RED + "Invalid choice. Please try again." + ColorManager.RESET);
            }
        }

        exitLoop = false;

        System.out.println("""
                Choose your menu preferences:\s
                1. Standard
                2. Vegetarian
                3. Vegan
                4. Nut FREE
                5. Dairy FREE
                0 to exit
                """);

        while (!exitLoop) {
            int menuChoice = getChoice();

            switch (menuChoice) {
                case 0:
                    exitLoop = true;
                    break;
                case 1:
                    chosenMenuPreferences.add(MenuOptions.STANDARD);
                    break;
                case 2:
                    chosenMenuPreferences.add(MenuOptions.VEGETARIAN);
                    break;
                case 3:
                    chosenMenuPreferences.add(MenuOptions.VEGAN);
                    break;
                case 4:
                    chosenMenuPreferences.add(MenuOptions.NUT_FREE);
                    break;
                case 5:
                    chosenMenuPreferences.add(MenuOptions.DAIRY_FREE);
                    break;
                default:
                    System.out.println(ColorManager.RED + "Invalid choice. Please try again." + ColorManager.RESET);
            }
        }

        while (true) {
            System.out.println("Enter your preferred date (DDMMYYYY): ");
            String inputDate = scanner.nextLine();
            try {
                DateValidator dateValidator = new DateValidator(inputDate);
                date = dateValidator.getDate();
                break;
            } catch (InvalidDateFormatException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }

        while (true) {
            System.out.println("Enter the number of guests: ");
            numberOfGuests = getChoice();
            try {
                if (numberOfGuests > 10) {
                    break;
                } else {
                    throw new InvalidNumberOfGuestsException("Number of guests must be greater than 10.");
                }
            } catch (InvalidNumberOfGuestsException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET);
            }
        }


        System.out.println("Preferences chosen successfully!");
    }


    private void checkBestSuitableRestaurant() {
        if (chosenMenuPreferences.isEmpty() || chosenAmenityPreferences.isEmpty()) {
            System.out.println(ColorManager.YELLOW + "Please choose menu and amenity preferences first." + ColorManager.RESET);
            return;
        }

        CustomerPreferences preferences = new CustomerPreferences(id, chosenMenuPreferences, chosenAmenityPreferences, date, numberOfGuests);
        List<Restaurant> suitableRestaurants = offerAlgorithm.offerRestaurants(preferences, restaurants);

        if (!suitableRestaurants.isEmpty()) {
            Restaurant bestRestaurant = suitableRestaurants.get(0);
            System.out.println(ColorManager.PURPLE + "Best suitable restaurant: " + bestRestaurant.getName() + ColorManager.RESET);

        } else {
            System.out.println(ColorManager.RED + "No suitable restaurant found based on your preferences." + ColorManager.RESET);
        }
    }

}