package account;

import client.Client;
import utils.ColorManager;
import utils.FileOperations;

import java.util.Scanner;
import java.util.Set;

public class Register {
    private static final String ACCOUNTS_FILE = "accounts_list.txt";
    private static final Set<String> accounts = FileOperations.loadLines(ACCOUNTS_FILE);
    private String email;
    private String password;
    private String confirmPassword;
    private Client client;

    public Register(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String enterValidPassword(Scanner scanner) {
        String password;
        String confirmPassword;

        do {
            System.out.println("Enter your password: ");
            password = scanner.nextLine();
            System.out.println("Enter your password again: ");
            confirmPassword = scanner.nextLine();

            if (!password.equals(confirmPassword)) {
                System.out.println(ColorManager.RED + "Passwords do not match. Please try again." + ColorManager.RESET + "\n");
            }
        } while (!password.equals(confirmPassword));

        this.password = password;
        this.confirmPassword = confirmPassword;

        return password;
    }


    public void saveAccount() {
        String emailInfo = email.trim();
        for (String account : accounts) {
            String existingEmail = account.split(":")[0].trim();
            if (existingEmail.equals(emailInfo)) {
                System.out.println(ColorManager.RED + "An account with email " + emailInfo + " is already registered." + ColorManager.RESET + "\n");
                return;
            }
        }

        String accountInfo = emailInfo + " : " + password;
        FileOperations.saveLine(ACCOUNTS_FILE, accountInfo);
        accounts.add(accountInfo);
        System.out.println(ColorManager.GREEN + "Account successfully registered." + ColorManager.RESET + "\n");
    }

    public boolean arePasswordsMatching() {
        return password.equals(confirmPassword);
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

    public String setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return confirmPassword;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static String getAccountsFile() {
        return ACCOUNTS_FILE;
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