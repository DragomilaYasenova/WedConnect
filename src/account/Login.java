package account;

import account.storage.AccountStorage;
import utils.FileOperations;

import java.util.Map;
import java.util.Scanner;

public class Login {
    private final Map<String, Register> accounts;
    private final AccountStorage accountStorage;
    private final PasswordManager passwordManager;
    private String username;
    private String password;

    public Login(Map<String, Register> accounts, AccountStorage accountStorage, PasswordManager passwordManager) {
        this.accounts = accounts;
        this.accountStorage = accountStorage;
        this.passwordManager = passwordManager;
        loadAccounts();
    }

    private void loadAccounts() {
        accounts.clear();
        accounts.putAll(AccountLoader.loadAccounts(accountStorage, passwordManager));
    }

    public Register login(Scanner scanner) {
        System.out.println("Enter your username: ");
        username = scanner.nextLine();
        System.out.println("Enter your password: ");
        password = scanner.nextLine();

        Register account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        } else {
            System.out.println("Login failed. Please check your username and password.");
            return null;
        }
    }

    public String getClientIdFromFile() {
        String fileName = "accounts_list.txt";
        return FileOperations.readClientId(fileName, username, password);
    }

    public String getId() {
        return getClientIdFromFile();
    }
}