package test.restaurant;

import client.CustomerPreferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.Menu.MenuManagerImpl;
import restaurant.Menu.MenuOptions;
import restaurant.Restaurant;
import restaurant.RestaurantOfferAlgorithm;
import restaurant.amenity.Amenity;
import restaurant.amenity.AmenityManagerImpl;
import restaurant.date.DateManagerImpl;
import restaurant.hall.HallManagerImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestaurantOfferAlgorithmTest {
    private RestaurantOfferAlgorithm offerAlgorithm;
    private List<Restaurant> allRestaurants;
    private CustomerPreferences preferences1;
    private CustomerPreferences preferences2;

    @BeforeEach
    void setUp() {
        offerAlgorithm = new RestaurantOfferAlgorithm();
        allRestaurants = createMockRestaurants();
        preferences1 = createMockPreferences().get(0);
        preferences2 = createMockPreferences().get(1);
    }

    @Test
    void testOfferRestaurants() {
        List<Restaurant> suitableRestaurants = offerAlgorithm.offerRestaurants(preferences1, allRestaurants);
        assertEquals(1, suitableRestaurants.size());
        assertTrue(suitableRestaurants.stream().anyMatch(restaurant -> restaurant.getName().equals("Restaurant 1")));
    }

    @Test
    void testOfferRestaurantsClientCannotAffordTheRestaurant() {
        List<Restaurant> suitableRestaurants = offerAlgorithm.offerRestaurants(preferences2, allRestaurants);
        assertEquals(0, suitableRestaurants.size());
    }

    private List<Restaurant> createMockRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();

        // Restaurant 1
        AmenityManagerImpl amenityManager1 = new AmenityManagerImpl();
        amenityManager1.addAmenity(Amenity.HOTEL);
        amenityManager1.addAmenity(Amenity.BEACH);
        amenityManager1.addAmenity(Amenity.POOL);

        MenuManagerImpl menuManager1 = new MenuManagerImpl();
        menuManager1.addMenuOption(MenuOptions.STANDARD);
        menuManager1.addMenuOption(MenuOptions.VEGETARIAN);

        HallManagerImpl hallManager1 = new HallManagerImpl();
        hallManager1.addHall("Main Hall", 100);
        hallManager1.addHall("Second Hall", 200);

        DateManagerImpl dateManager1 = new DateManagerImpl();

        Restaurant restaurant1 = new Restaurant("Restaurant 1", amenityManager1, menuManager1, hallManager1, dateManager1,40);
        restaurants.add(restaurant1);

        // Restaurant 2
        AmenityManagerImpl amenityManager2 = new AmenityManagerImpl();
        amenityManager2.addAmenity(Amenity.HOTEL);
        amenityManager2.addAmenity(Amenity.COUNTRYSIDE);
        amenityManager2.addAmenity(Amenity.GARDEN);
        amenityManager2.addAmenity(Amenity.POOL);

        MenuManagerImpl menuManager2 = new MenuManagerImpl();
        menuManager2.addMenuOption(MenuOptions.STANDARD);
        menuManager2.addMenuOption(MenuOptions.VEGAN);

        HallManagerImpl hallManager2 = new HallManagerImpl();
        hallManager2.addHall("Main Hall", 200);
        hallManager2.addHall("Second Hall", 150);
        hallManager2.addHall("Third Hall", 300);

        DateManagerImpl dateManager2 = new DateManagerImpl();

        Restaurant restaurant2 = new Restaurant("Restaurant 2", amenityManager2, menuManager2, hallManager2, dateManager2, 80);
        restaurants.add(restaurant2);

        return restaurants;
    }


    private List<CustomerPreferences> createMockPreferences() {
        List<CustomerPreferences> customers = new ArrayList<>();

        // Client 1
        Set<MenuOptions> chosenMenuPreferences1 = new HashSet<>();
        chosenMenuPreferences1.add(MenuOptions.STANDARD);
        chosenMenuPreferences1.add(MenuOptions.VEGAN);

        Set<Amenity> chosenAmenityPreferences1 = new HashSet<>();
        chosenAmenityPreferences1.add(Amenity.HOTEL);
        chosenAmenityPreferences1.add(Amenity.BEACH);
        chosenAmenityPreferences1.add(Amenity.POOL);

        CustomerPreferences preferences1 = new CustomerPreferences("test", chosenMenuPreferences1, chosenAmenityPreferences1, "10102025", 100);
        customers.add(preferences1);

        // Client 2
        Set<MenuOptions> chosenMenuPreferences2 = new HashSet<>();
        chosenMenuPreferences2.add(MenuOptions.STANDARD);

        Set<Amenity> chosenAmenityPreferences2 = new HashSet<>();
        chosenAmenityPreferences2.add(Amenity.HOTEL);
        chosenAmenityPreferences2.add(Amenity.POOL);

        CustomerPreferences preferences2 = new CustomerPreferences("test", chosenMenuPreferences2, chosenAmenityPreferences2, "10102025", 200);
        customers.add(preferences2);


        return customers;
    }
}
