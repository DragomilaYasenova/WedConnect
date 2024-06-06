package test.client;

import client.Person;
import exceptions.client.InvalidFirstNameException;
import exceptions.client.InvalidLastNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person("Jane", "Smith");
    }
    @Test
    void validFirstName() {
        String validFirstName = "Jane";
        person.setFirstName(validFirstName);
        assertEquals(validFirstName, person.getFirstName());
    }

    @Test
    void validLastName() {
        String validLastName = "Smith";
        person.setLastName(validLastName);
        assertEquals(validLastName, person.getLastName());
    }

    @Test
    void invalidFirstNameWithDigits() {
        String invalidFirstName = "123Jane";

        Exception exception = assertThrows(InvalidFirstNameException.class, () -> {
            person.setFirstName(invalidFirstName);
        });

        assertEquals("Invalid first name format", exception.getMessage());
    }

    @Test
    void invalidLastNameWithDigits() {
        String invalidLastName = "Smith123";

        Exception exception = assertThrows(InvalidLastNameException.class, () -> {
            person.setLastName(invalidLastName);
        });

        assertEquals("Invalid last name format", exception.getMessage());
    }
}