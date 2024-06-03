package account.storage;

import java.util.Set;

public abstract class AccountStorage {
    public abstract Set<String> loadAccounts();
    public abstract void saveAccount(String accountInfo);

    public boolean accountExists(String email) {
        Set<String> accounts = loadAccounts();
        String emailInfo = email.trim();
        for (String account : accounts) {
            String existingEmail = account.split(":")[0].trim();
            if (existingEmail.equals(emailInfo)) {
                return true;
            }
        }
        return false;
    }
}
