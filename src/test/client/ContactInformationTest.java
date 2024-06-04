package test.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import client.ContactInformation;
import client.Person;
import org.junit.Before;
import org.junit.Test;

public class ContactInformationTest {

    private ContactInformation contactInfo;
    private Person person;

    @Before
    public void setUp() {
        person = new Person("Jane", "Smith");
    }

    @Test
    public void testValidContactInformation() {
        contactInfo = new ContactInformation(person, "jane@example.com", "0800001234", "Sofia");

        assertEquals("Jane", contactInfo.getPerson().getFirstName());
        assertEquals("Smith", contactInfo.getPerson().getLastName());
        assertEquals("jane@example.com", contactInfo.getEmail());
        assertEquals("0800001234", contactInfo.getPhone());
        assertEquals("Sofia", contactInfo.getCity());
    }

    @Test
    public void testInvalidEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            contactInfo = new ContactInformation(person, "janeexamplecom", "0800001234", "Sofia");
        });

        assertEquals("Invalid email address", exception.getMessage());
    }

    @Test
    public void testInvalidPhoneNumberOnlyLetters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            contactInfo = new ContactInformation(person, "jane@example.com", "invalidphone", "Sofia");
        });

        assertEquals("Invalid phone number", exception.getMessage());
    }

    @Test
    public void testInvalidPhoneNumberShorterThanExpected() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            contactInfo = new ContactInformation(person, "jane@example.com", "080000", "Sofia");
        });

        assertEquals("Invalid phone number", exception.getMessage());
    }

    @Test
    public void testInvalidCityOnlyDigits() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            contactInfo = new ContactInformation(person, "jane@example.com", "0800001234", "12345");
        });

        assertEquals("Invalid city format", exception.getMessage());
    }

    @Test
    public void testInvalidCityEvenOneDigit() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            contactInfo = new ContactInformation(person, "jane@example.com", "0800001234", "So8fia");
        });

        assertEquals("Invalid city format", exception.getMessage());
    }
}