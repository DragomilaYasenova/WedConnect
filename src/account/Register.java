package account;

import client.Client;
import utils.ColorManager;

import java.util.Set;

public class Register {
    private final AccountStorage accountStorage;
    private final PasswordManager passwordManager;
    private String email;
    private String password;
    private String confirmPassword;
    private Client client;

    public Register(AccountStorage accountStorage, PasswordManager passwordManager, String email, String password, String confirmPassword) {
        this.accountStorage = accountStorage;
        this.passwordManager = passwordManager;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public void saveAccount() {
        Set<String> accounts = accountStorage.loadAccounts();
        String emailInfo = email.trim();
        for (String account : accounts) {
            String existingEmail = account.split(":")[0].trim();
            if (existingEmail.equals(emailInfo)) {
                System.out.println(ColorManager.RED + "An account with email " + emailInfo + " is already registered." + ColorManager.RESET + "\n");
                return;
            }
        }

        if (passwordManager.passwordsMatch(password, confirmPassword)) {
            String accountInfo = emailInfo + " : " + password;
            accountStorage.saveAccount(accountInfo);
            System.out.println(ColorManager.GREEN + "Account successfully registered." + ColorManager.RESET + "\n");
        } else {
            System.out.println(ColorManager.RED + "Passwords do not match." + ColorManager.RESET + "\n");
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

    @Override
    public String toString() {
        return "Register{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", client=" + client +
                '}';
    }
}