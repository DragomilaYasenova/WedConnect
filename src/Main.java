import account.storage.FileAccountStorage;
import client.ClientFileSaver;
import client.IdGenerator;
import exceptions.account.AccountAlreadyExistsException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;
import navigation.AccountService;
import navigation.ClientService;
import navigation.Navigation;
import restaurant.Restaurant;
import utils.ColorManager;
import account.PasswordManager;

import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws PasswordsDoNotMatchException, AccountAlreadyExistsException, PasswordCannotBeNullException {
        List<Restaurant> restaurants = RestaurantInitializer.initializeRestaurants();
        Scanner scanner = new Scanner(System.in);

        FileAccountStorage accountStorage = new FileAccountStorage();
        PasswordManager passwordManager = new PasswordManager();
        AccountService accountService = new AccountService(accountStorage, passwordManager);

        IdGenerator idGenerator = new IdGenerator();
        ClientFileSaver clientFileSaver = new ClientFileSaver();
        ClientService clientService = new ClientService(idGenerator, clientFileSaver);

        Navigation navigation = new Navigation(scanner, accountService, clientService, restaurants);

        System.out.println(ColorManager.PURPLE + "Welcome to WedConnect!" + ColorManager.RESET);


        navigation.menu();
    }
}