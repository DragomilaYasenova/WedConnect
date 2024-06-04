package client;
import utils.ColorManager;
import validators.EmailValidator;
import validators.PhoneNumberValidator;
import validators.StringValidator;

public class ContactInformation {
    private Person person;
    private String email;
    private String phone;
    private String city;

    public ContactInformation(Person person, String email, String phone, String city) {
        this.person = person;
        setEmail(email);
        setPhone(phone);
        setCity(city);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (EmailValidator.isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (PhoneNumberValidator.isValidPhoneNumber(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (!StringValidator.isValidString(city)) {
            throw new IllegalArgumentException("Invalid city format");
        }
        this.city = city;
    }

    @Override
    public String toString() {
        return "Name: " + person.getFirstName() + " " + person.getLastName() + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "City: " + city + "\n";
    }
}