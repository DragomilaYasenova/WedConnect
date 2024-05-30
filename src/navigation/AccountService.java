package navigation;

import account.AccountStorage;
import account.PasswordManager;
import account.Register;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AccountService {
    private final AccountStorage accountStorage;
    private final PasswordManager passwordManager;
    private final Map<String, Register> accounts;

    public AccountService(AccountStorage accountStorage, PasswordManager passwordManager) {
        this.accountStorage = accountStorage;
        this.passwordManager = passwordManager;
        this.accounts = new HashMap<>();
        loadAccounts();
    }

    private void loadAccounts() {
        Set<String> accountInfo = accountStorage.loadAccounts();
        for (String info : accountInfo) {
            String[] parts = info.split(":");
            if (parts.length == 2) {
                String email = parts[0].trim();
                String password = parts[1].trim();
                accounts.put(email, new Register(accountStorage, passwordManager, email, password, password));
            }
        }
    }

    public void register(String email, String password, String confirmPassword) {
        Register register = new Register(accountStorage, passwordManager, email, password, confirmPassword);
        register.saveAccount();
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
            return null;
        }
    }
}
