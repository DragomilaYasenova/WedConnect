package test.account;

import account.InMemoryAccountStorage;
import account.PasswordManager;
import account.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    Register register;
    InMemoryAccountStorage accountStorage;
    PasswordManager passwordManager;

    @BeforeEach
    void setUp() {
        accountStorage = new InMemoryAccountStorage();
        passwordManager = new PasswordManager();
        String email = "test@example.com";
        String password = "password123";
        String confirmPassword = "password123";
        register = new Register(accountStorage, passwordManager, email, password, confirmPassword);
    }

    @Test
    public void testSaveAccount() {
        register.saveAccount();

        Set<String> accounts = accountStorage.loadAccounts();
        assertTrue(accounts.contains("test@example.com : password123"));
    }
}