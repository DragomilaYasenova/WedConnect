package validators;

import exceptions.client.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private String date;

    public DateValidator(String date) {
        setDate(date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateStr) {
        validateDate(dateStr);
        this.date = dateStr;
    }

    private void validateDate(String dateStr) {
        if (dateStr.length() != 8) {
            throw new InvalidDateFormatException("Invalid date format. Please enter the date in the format DDMMYYYY.");
        }

        int day = Integer.parseInt(dateStr.substring(0, 2));
        int month = Integer.parseInt(dateStr.substring(2, 4));
        int year = Integer.parseInt(dateStr.substring(4, 8));

        if (month < 1 || month > 12) {
            throw new InvalidDateFormatException("Invalid month. Please enter a valid month between 01 and 12.");
        }

        if (!isValidDayForMonth(day, month, year)) {
            throw new InvalidDateFormatException("Invalid day. Please enter a valid day for the given month and year.");
        }

        LocalDate inputDate = parseDate(dateStr);
        if (inputDate.isBefore(LocalDate.now())) {
            throw new InvalidDateFormatException("Date must be in the future.");
        }
    }

    private boolean isValidDayForMonth(int day, int month, int year) {
        if (day < 1) {
            return false;
        }
        switch (month) {
            case 2:
                return isLeapYear(year) ? day <= 29 : day <= 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return day <= 30;
            default:
                return day <= 31;
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please enter the date in the format DDMMYYYY.");
        }
    }
}