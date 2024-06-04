package account;

import account.storage.AccountStorage;
import client.Client;
import exceptions.UsernameCannotBeNullException;
import exceptions.account.AccountAlreadyExistsException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;
import utils.ColorManager;
import utils.FileOperations;
import validators.UsernameValidator;

import static utils.FileOperations.updateAccountInfo;

public class Register {
    private final AccountStorage accountStorage;
    private final PasswordManager passwordManager;
    private String username;
    private String password = null;
    private String confirmPassword;
    private Client client;
    private String clientId;

    public Register(AccountStorage accountStorage, PasswordManager passwordManager, String username, String password, String confirmPassword) {
        this.accountStorage = accountStorage;
        this.passwordManager = passwordManager;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public void saveAccount() throws AccountAlreadyExistsException, PasswordsDoNotMatchException, PasswordCannotBeNullException {
        if (clientId != null) {
            String fileName = "accounts_list.txt";
            String oldLine = username + " : " + password + " : null";
            String newLine = username + " : " + password + " : " + clientId;
            updateAccountInfo(fileName, oldLine, newLine);
        } else {
            String usernameInfo = username.trim();

            if (accountStorage.accountExists(usernameInfo)) {
                throw new AccountAlreadyExistsException("An account with username " + username + " is already registered.");
            }

            if (passwordManager.nullPassword(password)) {
                throw new PasswordCannotBeNullException("Password cannot be null.");
            }

            if (UsernameValidator.nullUsername(username)) {
                throw new UsernameCannotBeNullException("Username cannot be null.");
            }


            if (!passwordManager.passwordsMatch(password, confirmPassword)) {
                throw new PasswordsDoNotMatchException("Passwords do not match.");
            }

            String accountInfo = usernameInfo + " : " + password + " : " + clientId;
            accountStorage.saveAccount(accountInfo);
            System.out.println(ColorManager.GREEN + "Account successfully registered." + ColorManager.RESET + "\n");
        }
    }

    public String getuUername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getClientIdFromFile() {
        String fileName = "accounts_list.txt";
        return FileOperations.readClientId(fileName, username, password);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}