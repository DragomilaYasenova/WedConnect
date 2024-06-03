package account;

import account.storage.AccountStorage;

import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class Login {
    private final Map<String, Register> accounts;
    private final AccountStorage accountStorage;
    private final PasswordManager passwordManager;

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
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        Register account = accounts.get(email);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        } else {
            System.out.println("Login failed. Please check your email and password.");
            return null;
        }
    }
}