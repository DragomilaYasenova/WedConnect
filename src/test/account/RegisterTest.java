package test.account;

import account.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class RegisterTest {
    Register register;

    @BeforeEach
    void setUp() {
        String email = "test@example.com";
        String password = "password123";
        String confirmPassword = "password123";
        register = new Register(email, password, confirmPassword);
    }

    @Test
    public void testEnterValidPassword() {
        String input = "password123\npassword123\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        String enteredPassword = register.enterValidPassword(scanner);

        assertEquals("password123", enteredPassword);
    }
}