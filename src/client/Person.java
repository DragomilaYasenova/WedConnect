package client;

import exceptions.client.InvalidFirstNameException;
import exceptions.client.InvalidLastNameException;
import validators.StringValidator;

public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (StringValidator.isValidString(firstName)) {
            this.firstName = firstName;
        } else {
            throw new InvalidFirstNameException("Invalid first name format");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (StringValidator.isValidString(lastName)) {
            this.lastName = lastName;
        } else {
            throw new InvalidLastNameException("Invalid last name format");
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}