package account.storage;

import account.storage.AccountStorage;

import java.util.HashSet;
import java.util.Set;

public class InMemoryAccountStorage extends AccountStorage {
    private final Set<String> accounts = new HashSet<>();

    @Override
    public Set<String> loadAccounts() {
        return new HashSet<>(accounts);
    }

    @Override
    public void saveAccount(String accountInfo) {
        accounts.add(accountInfo);
    }
}
