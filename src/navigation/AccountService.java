package navigation;

import account.AccountLoader;
import account.Login;
import account.storage.AccountStorage;
import validators.PasswordValidator;
import account.Register;
import exceptions.UsernameCannotBeNullException;
import exceptions.account.AccountAlreadyExistsException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountService {
    private final AccountStorage accountStorage;
    private final PasswordValidator passwordValidator;
    private final Map<String, Register> accounts;
    private final Login login;

    public AccountService(AccountStorage accountStorage, PasswordValidator passwordValidator) {
        this.accountStorage = accountStorage;
        this.passwordValidator = passwordValidator;
        this.accounts = new HashMap<>();
        this.login = new Login(accounts, accountStorage, passwordValidator);
        loadAccounts();
    }

    private void loadAccounts() {
        accounts.clear();
        accounts.putAll(AccountLoader.loadAccounts(accountStorage, passwordValidator));
    }

    public void register(String username, String password, String confirmPassword) throws AccountAlreadyExistsException, PasswordCannotBeNullException, PasswordsDoNotMatchException, UsernameCannotBeNullException {
        Register register = new Register(accountStorage, passwordValidator, username, password, confirmPassword);
        register.saveAccount();
        loadAccounts();
    }

    public Register login(String username, String password) {
        return login.login(username, password);
    }

    public String getLoggedInClientId() {
        return login.getId();
    }

    public boolean accountExists(String username) {
        return accountStorage.accountExists(username);
    }

    public boolean nullPassword(String password) {
        return passwordValidator.nullPassword(password);
    }

    public boolean passwordsMatch(String password, String confirmPassword) {
        return passwordValidator.passwordsMatch(password, confirmPassword);
    }
}
