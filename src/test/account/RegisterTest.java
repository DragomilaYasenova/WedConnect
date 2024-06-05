package test.account;

import account.storage.InMemoryAccountStorage;
import account.PasswordManager;
import account.Register;
import exceptions.UsernameCannotBeNullException;
import exceptions.account.AccountAlreadyExistsException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;
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
    public void nullClientId() throws PasswordsDoNotMatchException, AccountAlreadyExistsException, PasswordCannotBeNullException {
        register.setClientId(null);

        register.saveAccount();

        Set<String> accounts = accountStorage.loadAccounts();
        assertTrue(accounts.stream().anyMatch(account -> account.equals("test@example.com : password123 : null")));
    }

    @Test
    public void successfulRegistration() throws PasswordsDoNotMatchException, AccountAlreadyExistsException, PasswordCannotBeNullException {
        accountStorage.saveAccount("test2@example.com : password123 : clientId");

        register.saveAccount();

        Set<String> accounts = accountStorage.loadAccounts();
        assertTrue(accounts.stream().anyMatch(account -> account.equals("test2@example.com : password123 : clientId")));
    }

    @Test
    public void accountAlreadyExists() {
        accountStorage.saveAccount("test@example.com : password123 : null");

        Exception exception = assertThrows(AccountAlreadyExistsException.class, () -> {
            register.saveAccount();
        });

        assertEquals("An account with username test@example.com is already registered.", exception.getMessage());
    }

    @Test
    public void passwordsDoNotMatch() {
        register = new Register(accountStorage, passwordManager, "test@example.com", "password123", "differentPassword");

        Exception exception = assertThrows(PasswordsDoNotMatchException.class, () -> {
            register.saveAccount();
        });

        assertEquals("Passwords do not match.", exception.getMessage());
    }

    @Test
    public void nullPassword() {
        register = new Register(accountStorage, passwordManager, "test@example.com", "", "");

        Exception exception = assertThrows(PasswordCannotBeNullException.class, () -> {
            register.saveAccount();
        });

        assertEquals("Password cannot be null.", exception.getMessage());
    }

    @Test
    public void nullUsername() {
        register = new Register(accountStorage, passwordManager, "", "password123", "password123");

        Exception exception = assertThrows(UsernameCannotBeNullException.class, () -> {
            register.saveAccount();
        });

        assertEquals("Username cannot be null.", exception.getMessage());
    }

}