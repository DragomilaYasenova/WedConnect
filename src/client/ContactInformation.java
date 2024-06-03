package client;
import utils.ColorManager;

public class ContactInformation {
    private Person person;
    private String email;
    private String phone;
    private String city;

    public ContactInformation(Person person, String email, String phone, String city) {
        this.person = person;
        this.email = email;
        this.phone = phone;
        this.city = city;
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
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
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