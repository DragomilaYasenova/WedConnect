package test;

import exceptions.client.InvalidDateFormatException;
import org.junit.jupiter.api.Test;
import validators.DateValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DateValidatorTest {

    @Test
    void validDateTest() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String validDate = tomorrow.format(formatter);
        DateValidator validator = new DateValidator(validDate);

        String date = validator.getDate();

        assertEquals(validDate, date);
    }

    @Test
    void invalidDateLengthTest() {
        String invalidDate = "010120";

        assertThrows(InvalidDateFormatException.class, () -> new DateValidator(invalidDate));
    }

    @Test
    void invalidMonthTest() {
        String invalidDate = "01132030";

        assertThrows(InvalidDateFormatException.class, () -> new DateValidator(invalidDate));
    }

    @Test
    void invalidDayTest() {
        String invalidDate = "32012030";

        assertThrows(InvalidDateFormatException.class, () -> new DateValidator(invalidDate));
    }

    @Test
    void pastDateTest() {
        String pastDate = "01012000";

        assertThrows(InvalidDateFormatException.class, () -> new DateValidator(pastDate));
    }
}