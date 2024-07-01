package account;

import account.storage.AccountStorage;
import utils.FileOperations;
import validators.PasswordValidator;

import java.util.Map;

public class Login {
    private final Map<String, Register> accounts;
    private final AccountStorage accountStorage;
    private final PasswordValidator passwordValidator;
    private String username;
    private String password;

    public Login(Map<String, Register> accounts, AccountStorage accountStorage, PasswordValidator passwordValidator) {
        this.accounts = accounts;
        this.accountStorage = accountStorage;
        this.passwordValidator = passwordValidator;
        loadAccounts();
    }

    private void loadAccounts() {
        accounts.clear();
        accounts.putAll(AccountLoader.loadAccounts(accountStorage, passwordValidator));
    }

    public Register login(String username, String password) {
        this.username = username;
        this.password = password;
        Register account = accounts.get(username);
        if (account != null && account.getPassword().equals(password)) {
            return account;
        } else {
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
