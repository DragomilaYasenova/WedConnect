package account;

import utils.ColorManager;
import utils.FileOperations;

import java.util.Set;

public class Register {
    private static final String ACCOUNTS_FILE = "accounts_list.txt";
    private static final Set<String> accounts = FileOperations.loadLines(ACCOUNTS_FILE);
    private String email;
    private String password;
    private String confirmPassword;

    public Register(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public void saveAccount() {
        String accountInfo = email + " : " + password;
        if (FileOperations.checkAndSaveLine(accounts, accountInfo, ACCOUNTS_FILE)) {
            System.out.println(ColorManager.GREEN + "Account successfully registered." + ColorManager.RESET);
        } else {
            System.out.println(ColorManager.RED + "The account with email " + email + " is already registered." + ColorManager.RESET);
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

    @Override
    public String toString() {
        return "Register{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}