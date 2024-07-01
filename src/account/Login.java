package account;

import account.storage.AccountStorage;
import utils.FileOperations;

import java.util.Map;

public class Login {
    private final Map<String, Register> accounts;
    private final AccountStorage accountStorage;
    private String username;
    private String password;

    public Login(Map<String, Register> accounts, AccountStorage accountStorage) {
        this.accounts = accounts;
        this.accountStorage = accountStorage;
        loadAccounts();
    }

    private void loadAccounts() {
        accounts.clear();
        accounts.putAll(AccountLoader.loadAccounts(accountStorage));
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
