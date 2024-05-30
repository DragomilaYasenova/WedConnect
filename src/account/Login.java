package account;

import utils.ColorManager;
import utils.FileOperations;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Login {
    private final Map<String, Register> accounts;
    private final AccountStorage accountStorage;

    public Login(Map<String, Register> accounts, AccountStorage accountStorage) {
        this.accounts = accounts;
        this.accountStorage = accountStorage;
        loadAccounts();
    }

    private void loadAccounts() {
        Set<String> accountInfo = accountStorage.loadAccounts();
        for (String info : accountInfo) {
            String[] parts = info.split(":");
            if (parts.length == 2) {
                String email = parts[0].trim();
                String password = parts[1].trim();
                accounts.put(email, new Register(accountStorage, new PasswordManager(), email, password, password));
            }
        }
    }

}
