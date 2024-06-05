package client;

import restaurant.Menu.MenuOptions;
import restaurant.amenity.Amenity;

import java.util.List;

public class CustomerPreferences {
    private List<MenuOptions> menuPreferences;
    private List<Amenity> amenityPreferences;
    private String date;
    private int numberOfGuests;


    public CustomerPreferences(List<MenuOptions> menuPreferences, List<Amenity> amenityPreferences, String date, int numberOfGuests) {
        this.menuPreferences = menuPreferences;
        this.amenityPreferences = amenityPreferences;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
    }

    public List<MenuOptions> getMenuPreferences() {
        return menuPreferences;
    }

    public List<Amenity> getAmenityPreferences() {
        return amenityPreferences;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}