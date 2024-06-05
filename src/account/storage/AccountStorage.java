package account.storage;

import java.util.Set;

public abstract class AccountStorage {
    public abstract Set<String> loadAccounts();
    public abstract void saveAccount(String accountInfo);

    public boolean accountExists(String username) {
        Set<String> accounts = loadAccounts();
        String usernameInfo = username.trim();
        for (String account : accounts) {
            String existingUsername = account.split(":")[0].trim();
            if (existingUsername.equals(usernameInfo)) {
                return true;
            }
        }
        return false;
    }
}
