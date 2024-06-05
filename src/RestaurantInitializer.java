import restaurant.Menu.MenuManagerImpl;
import restaurant.Menu.MenuOptions;
import restaurant.Restaurant;
import restaurant.amenity.Amenity;
import restaurant.amenity.AmenityManagerImpl;
import restaurant.date.DateManagerImpl;
import restaurant.hall.HallManagerImpl;

import java.util.ArrayList;
import java.util.List;

public class RestaurantInitializer {
    public static List<Restaurant> initializeRestaurants() {
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

        DateManagerImpl dateManager1 = new DateManagerImpl();

        Restaurant restaurant1 = new Restaurant("Restaurant 1", amenityManager1, menuManager1, hallManager1, dateManager1,40);
        restaurants.add(restaurant1);

        // Restaurant 2
        AmenityManagerImpl amenityManager2 = new AmenityManagerImpl();
        amenityManager2.addAmenity(Amenity.HOTEL);
        amenityManager2.addAmenity(Amenity.COUNTRYSIDE);
        amenityManager2.addAmenity(Amenity.GARDEN);

        MenuManagerImpl menuManager2 = new MenuManagerImpl();
        menuManager2.addMenuOption(MenuOptions.STANDARD);
        menuManager2.addMenuOption(MenuOptions.VEGAN);

        HallManagerImpl hallManager2 = new HallManagerImpl();
        hallManager2.addHall("Main Hall", 200);
        hallManager2.addHall("Second Hall", 250);
        hallManager2.addHall("Third Hall", 300);

        DateManagerImpl dateManager2 = new DateManagerImpl();

        Restaurant restaurant2 = new Restaurant("Restaurant 2", amenityManager2, menuManager2, hallManager2, dateManager2, 70);
        restaurants.add(restaurant2);

        // Restaurant 3
        AmenityManagerImpl amenityManager3 = new AmenityManagerImpl();
        amenityManager3.addAmenity(Amenity.HOTEL);
        amenityManager3.addAmenity(Amenity.BEACH);
        amenityManager3.addAmenity(Amenity.BALLROOM);
        amenityManager3.addAmenity(Amenity.TERRACE);

        MenuManagerImpl menuManager3 = new MenuManagerImpl();
        menuManager3.addMenuOption(MenuOptions.STANDARD);
        menuManager3.addMenuOption(MenuOptions.VEGETARIAN);
        menuManager3.addMenuOption(MenuOptions.VEGAN);

        HallManagerImpl hallManager3 = new HallManagerImpl();
        hallManager3.addHall("Main Hall", 50);
        hallManager3.addHall("Second Hall", 100);
        hallManager3.addHall("Third Hall", 50);

        DateManagerImpl dateManager3 = new DateManagerImpl();

        Restaurant restaurant3 = new Restaurant("Restaurant 3", amenityManager3, menuManager3, hallManager3, dateManager3, 40);
        restaurants.add(restaurant3);

        // Restaurant 4
        AmenityManagerImpl amenityManager4 = new AmenityManagerImpl();
        amenityManager4.addAmenity(Amenity.HOTEL);
        amenityManager3.addAmenity(Amenity.BEACH);
        amenityManager4.addAmenity(Amenity.COUNTRYSIDE);
        amenityManager4.addAmenity(Amenity.GARDEN);

        MenuManagerImpl menuManager4 = new MenuManagerImpl();
        menuManager4.addMenuOption(MenuOptions.STANDARD);
        menuManager4.addMenuOption(MenuOptions.VEGAN);

        HallManagerImpl hallManager4 = new HallManagerImpl();
        hallManager4.addHall("Main Hall", 300);
        hallManager4.addHall("Second Hall", 450);
        hallManager4.addHall("Third Hall", 500);

        DateManagerImpl dateManager4 = new DateManagerImpl();

        Restaurant restaurant4 = new Restaurant("Restaurant 4", amenityManager4, menuManager4, hallManager4, dateManager4,100);
        restaurants.add(restaurant4);

        return restaurants;
    }
}