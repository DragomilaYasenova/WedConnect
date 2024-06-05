package test.account;

import account.Login;
import account.Register;
import account.storage.AccountStorage;
import account.storage.InMemoryAccountStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import account.PasswordManager;

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
    private PasswordManager passwordManager;

    @BeforeEach
    void setUp() {
        accounts = new HashMap<>();
        accountStorage = new InMemoryAccountStorage();
        passwordManager = new PasswordManager();
        login = new Login(accounts, accountStorage, passwordManager);

        Register testAccount = new Register(accountStorage, passwordManager, "test_username", "password", "password");
        accounts.put("test_username", testAccount);
    }

    @Test
    void testSuccessfulLogin() {
        String input = "test_username\npassword\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        Register loggedInAccount = login.login(scanner);

        assertNotNull(loggedInAccount);
        assertEquals("test_username", loggedInAccount.getUsername());
    }

    @Test
    void testFailedLogin() {
        String input = "invalid_user\ninvalid_password\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        Register loggedInAccount = login.login(scanner);

        assertNull(loggedInAccount);
    }
}