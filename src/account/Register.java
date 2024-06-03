package account;

import account.storage.AccountStorage;
import client.Client;
import exceptions.AccountAlreadyExistsException;
import exceptions.PasswordsDoNotMatchException;
import utils.ColorManager;
import utils.FileOperations;

import java.util.Set;

import static utils.FileOperations.updateAccountInfo;

public class Register {
    private final AccountStorage accountStorage;
    private final PasswordManager passwordManager;
    private String email;
    private String password;
    private String confirmPassword;
    private Client client;
    private String clientId;

    public Register(AccountStorage accountStorage, PasswordManager passwordManager, String email, String password, String confirmPassword) {
        this.accountStorage = accountStorage;
        this.passwordManager = passwordManager;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public void saveAccount() {
        if(clientId != null){
            String fileName = "accounts_list.txt";
            String oldLine = email + " : " + password + " : null";
            String newLine = email + " : " + password + " : " + clientId;
            updateAccountInfo(fileName, oldLine, newLine);
        } else {
            String emailInfo = email.trim();
            try {
                if (accountStorage.accountExists(emailInfo)) {
                    throw new AccountAlreadyExistsException(emailInfo);
                }

                if (!passwordManager.passwordsMatch(password, confirmPassword)) {
                    throw new PasswordsDoNotMatchException();
                }

                String accountInfo = emailInfo + " : " + password + " : " + clientId;
                accountStorage.saveAccount(accountInfo);
                System.out.println(ColorManager.GREEN + "Account successfully registered." + ColorManager.RESET + "\n");

            } catch (AccountAlreadyExistsException | PasswordsDoNotMatchException e) {
                System.out.println(ColorManager.RED + e.getMessage() + ColorManager.RESET + "\n");
            } catch (Exception e) {
                System.out.println(ColorManager.RED + "Error saving account: " + e.getMessage() + ColorManager.RESET + "\n");
            }
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return FileOperations.readClientId(fileName, email, password);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}