import account.FileAccountStorage;
import client.ClientFileSaver;
import client.IdGenerator;
import navigation.AccountService;
import navigation.ClientService;
import navigation.Navigation;
import utils.ColorManager;
import account.PasswordManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FileAccountStorage accountStorage = new FileAccountStorage();
        PasswordManager passwordManager = new PasswordManager();
        AccountService accountService = new AccountService(accountStorage, passwordManager);

        IdGenerator idGenerator = new IdGenerator();
        ClientFileSaver clientFileSaver = new ClientFileSaver();
        ClientService clientService = new ClientService(idGenerator, clientFileSaver);

        Navigation navigation = new Navigation(scanner, accountService, clientService);

        System.out.println(ColorManager.PURPLE + "Welcome to WedConnect!" + ColorManager.RESET);

        navigation.menu();
    }
}