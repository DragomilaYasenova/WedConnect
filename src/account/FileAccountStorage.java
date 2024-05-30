package account;

import utils.FileOperations;

import java.util.Set;

public class FileAccountStorage extends AccountStorage {
    private static final String ACCOUNTS_FILE = "accounts_list.txt";

    @Override
    public Set<String> loadAccounts() {
        return FileOperations.loadLines(ACCOUNTS_FILE);
    }

    @Override
    public void saveAccount(String accountInfo) {
        FileOperations.saveLine(ACCOUNTS_FILE, accountInfo);
    }
}
