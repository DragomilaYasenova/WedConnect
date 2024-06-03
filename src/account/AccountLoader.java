package account;

import account.storage.AccountStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AccountLoader {
    public static Map<String, Register> loadAccounts(AccountStorage accountStorage, PasswordManager passwordManager) {
        Map<String, Register> accounts = new HashMap<>();
        Set<String> accountInfo = accountStorage.loadAccounts();
        for (String info : accountInfo) {
            String[] parts = info.split(":");
            if (parts.length == 3) {
                String email = parts[0].trim();
                String password = parts[1].trim();
                accounts.put(email, new Register(accountStorage, passwordManager, email, password, password));
            }
        }
        return accounts;
    }
}