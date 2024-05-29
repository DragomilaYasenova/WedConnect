package account;

import utils.ColorManager;
import utils.FileOperations;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Login {
    private final Map<String, Register> accounts;

    public Login(Map<String, Register> accounts) {
        this.accounts = accounts;
    }

    public Register loginMenu(Scanner scanner) {
        Register account = null;
        Set<String> accountInfo = FileOperations.loadLines(Register.getAccountsFile());
        for (String info : accountInfo) {
            String[] parts = info.split(":");
            if (parts.length == 2) {
                String email = parts[0].trim();
                String password = parts[1].trim();
                accounts.put(email, new Register(email, password, password));
            }
        }

        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        account = accounts.get(email);
        if (account != null && account.getPassword().equals(password)) {
            System.out.println(ColorManager.GREEN + "Login successful for: " + email + ColorManager.RESET + "\n");
            return account;
        } else {
            System.out.println(ColorManager.RED + "Invalid email or password." + ColorManager.RESET + "\n");
            return null;
        }
    }
}