package account;

import account.storage.AccountStorage;
import client.Client;
import exceptions.UsernameCannotBeNullException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;
import utils.FileOperations;
import validators.PasswordValidator;
import validators.UsernameValidator;

import static utils.FileOperations.updateAccountInfo;

public class Register {
    private final AccountStorage accountStorage;
    private String username;
    private String password;
    private String confirmPassword;
    private Client client;
    private String clientId;

    public Register(AccountStorage accountStorage, String username, String password, String confirmPassword) {
        this.accountStorage = accountStorage;
        setUsername(username);
        setPassword(password);
        setConfirmPassword(confirmPassword);
    }

    public void saveAccount() {
        if (clientId != null) {
            String fileName = "accounts_list.txt";
            String oldLine = getUsername() + " : " + getPassword() + " : null";
            String newLine = getUsername() + " : " + getPassword() + " : " + clientId;
            updateAccountInfo(fileName, oldLine, newLine);
        } else {
            String usernameInfo = getUsername().trim();
            String accountInfo = usernameInfo + " : " + getPassword() + " : " + "null";
            accountStorage.saveAccount(accountInfo);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (UsernameValidator.nullUsername(username)) {
            throw new UsernameCannotBeNullException("Username cannot be null.");
        } else {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (PasswordValidator.nullPassword(password)){
            throw new PasswordCannotBeNullException("Password cannot be null.");
        } else {
            this.password = password;
        }
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        if (PasswordValidator.passwordsMatch(password,confirmPassword)){
            this.confirmPassword = confirmPassword;
        } else {
            throw new PasswordsDoNotMatchException("Passwords do not match.");
        }
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