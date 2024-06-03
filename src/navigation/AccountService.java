package navigation;

import account.AccountLoader;
import account.Login;
import account.storage.AccountStorage;
import account.PasswordManager;
import account.Register;
import utils.ColorManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AccountService {
    private final AccountStorage accountStorage;
    private final PasswordManager passwordManager;
    private final Map<String, Register> accounts;
    private final Login login;

    public AccountService(AccountStorage accountStorage, PasswordManager passwordManager) {
        this.accountStorage = accountStorage;
        this.passwordManager = passwordManager;
        this.accounts = new HashMap<>();
        this.login = new Login(accounts, accountStorage, passwordManager);
        loadAccounts();
    }

    private void loadAccounts() {
        accounts.clear();
        accounts.putAll(AccountLoader.loadAccounts(accountStorage, passwordManager));
    }

    public void register(String email, String password, String confirmPassword) {
        Register register = new Register(accountStorage, passwordManager, email, password, confirmPassword);
        register.saveAccount();
        loadAccounts();
    }

    public Register login(Scanner scanner) {
        return login.login(scanner);
    }
}