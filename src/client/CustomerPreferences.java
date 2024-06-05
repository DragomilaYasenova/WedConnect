package client;

import restaurant.Menu.MenuOptions;
import restaurant.amenity.Amenity;

import java.util.List;
import java.util.Set;

public class CustomerPreferences {
    private String id;
    private Set<MenuOptions> menuPreferences;
    private Set<Amenity> amenityPreferences;
    private String date;
    private int numberOfGuests;


    public CustomerPreferences(String id, Set<MenuOptions> menuPreferences, Set<Amenity> amenityPreferences, String date, int numberOfGuests) {
        this.id = id;
        this.menuPreferences = menuPreferences;
        this.amenityPreferences = amenityPreferences;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
    }

    public Set<MenuOptions> getMenuPreferences() {
        return menuPreferences;
    }

    public Set<Amenity> getAmenityPreferences() {
        return amenityPreferences;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public String getId() {
        return id;
    }
}