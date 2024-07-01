package test.account;

import account.Login;
import account.Register;
import account.storage.AccountStorage;
import account.storage.InMemoryAccountStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import validators.PasswordValidator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTest {
    private Login login;
    private Map<String, Register> accounts;
    private AccountStorage accountStorage;
    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        accounts = new HashMap<>();
        accountStorage = new InMemoryAccountStorage();
        passwordValidator = new PasswordValidator();
        login = new Login(accounts, accountStorage, passwordValidator);

        Register testAccount = new Register(accountStorage, passwordValidator, "test_username", "password", "password");
        accounts.put("test_username", testAccount);
    }

    @Test
    void testSuccessfulLogin() {
        String username = "test_username";
        String password = "password";

        Register loggedInAccount = login.login(username, password);

        assertNotNull(loggedInAccount);
        assertEquals("test_username", loggedInAccount.getUsername());
    }

    @Test
    void testFailedLogin() {
        String username = "invalid_user";
        String password = "invalid_password";

        Register loggedInAccount = login.login(username, password);

        assertNull(loggedInAccount);
    }
}
