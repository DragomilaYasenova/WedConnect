import account.storage.FileAccountStorage;
import client.ClientFileSaver;
import client.IdGenerator;
import navigation.*;
import restaurant.Restaurant;
import utils.ColorManager;
import validators.PasswordValidator;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Restaurant> restaurants = RestaurantInitializer.initializeRestaurants();
        Scanner scanner = new Scanner(System.in);

        FileAccountStorage accountStorage = new FileAccountStorage();
        PasswordValidator passwordValidator = new PasswordValidator();
        AccountService accountService = new AccountService(accountStorage, passwordValidator);

        IdGenerator idGenerator = new IdGenerator();
        ClientFileSaver clientFileSaver = new ClientFileSaver();
        ClientService clientService = new ClientService(idGenerator, clientFileSaver);

        Navigation navigation = new Navigation(scanner, accountService, clientService, restaurants);

        System.out.println(ColorManager.PURPLE + "Welcome to WedConnect!" + ColorManager.RESET);


        navigation.menu();
    }
}