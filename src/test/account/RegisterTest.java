package test.account;

import account.storage.InMemoryAccountStorage;
import validators.PasswordValidator;
import account.Register;
import exceptions.UsernameCannotBeNullException;
import exceptions.password.PasswordCannotBeNullException;
import exceptions.password.PasswordsDoNotMatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    Register register;
    InMemoryAccountStorage accountStorage;
    PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        accountStorage = new InMemoryAccountStorage();
        passwordValidator = new PasswordValidator();
        String email = "test";
        String password = "password123";
        String confirmPassword = "password123";
        register = new Register(accountStorage, passwordValidator, email, password, confirmPassword);
    }

    @Test
    public void nullClientId() {
        register.setClientId(null);

        register.saveAccount();

        Set<String> accounts = accountStorage.loadAccounts();
        assertTrue(accounts.stream().anyMatch(account -> account.equals("test : password123 : null")));
    }

    @Test
    public void successfulRegistration() {
        accountStorage.saveAccount("test : password123 : clientId");

        register.saveAccount();

        Set<String> accounts = accountStorage.loadAccounts();
        assertTrue(accounts.stream().anyMatch(account -> account.equals("test : password123 : clientId")));
    }

    @Test
    void testSetUsernameNull() {
        String emptyUsername = "";
        assertThrows(UsernameCannotBeNullException.class, () -> register.setUsername(emptyUsername));
    }

    @Test
    void testSetPasswordNull() {
        String emptyPassword = "";
        assertThrows(PasswordCannotBeNullException.class, () -> register.setPassword(emptyPassword));
    }

    @Test
    void testSetConfirmPasswordDoNotMatch() {
        register.setPassword("password123");
        assertThrows(PasswordsDoNotMatchException.class, () -> register.setConfirmPassword("mismatchPassword"));
    }
}