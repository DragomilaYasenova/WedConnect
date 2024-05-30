package account;

import java.util.Set;

public abstract class AccountStorage {
    public abstract Set<String> loadAccounts();
    public abstract void saveAccount(String accountInfo);
}
