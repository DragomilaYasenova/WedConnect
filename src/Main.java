import account.Register;
import client.Client;
import client.ContactInformation;
import client.Person;
import restaurant.MenuOptions;
import restaurant.Restaurant;
import restaurant.Amenity;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Person bride = new Person("Alice", "Smith");
        Person groom = new Person("Bob", "Johnson");

        ContactInformation contactInfoBride = new ContactInformation(bride, "alice@example.com", "0800001234", "Sofia");
        ContactInformation contactInfoGroom = new ContactInformation(groom, "bob@example.com", "0800000987", "Sofia");

        BigDecimal budget = new BigDecimal("15000.00");
        Client client = new Client(bride, groom, contactInfoBride, contactInfoGroom, budget);

        System.out.println(client);

        Register register = new Register("alice@example.com", "alice", "alice");
        register.saveAccount();
        


        Restaurant restaurant = new Restaurant("Black Mamba");
        restaurant.addHall("Main Hall", 300);
        restaurant.addHall("Conference Room", 50);
        restaurant.removeHall("Conference Room");
        restaurant.addHall("Banquet Hall", 200);

        restaurant.addAmenity(Amenity.HOTEL);
        restaurant.addAmenity(Amenity.BANQUET_HALL);
        restaurant.removeAmenity(Amenity.BANQUET_HALL);
        restaurant.addAmenity(Amenity.BEACH);

        restaurant.addMenuOptions(MenuOptions.STANDARD);
        restaurant.addMenuOptions(MenuOptions.GLUTEN_FREE);
        restaurant.removeMenuOptions(MenuOptions.GLUTEN_FREE);
        restaurant.addMenuOptions(MenuOptions.VEGAN);

        System.out.println(restaurant);
        restaurant.printTakenDates();
    }
}
