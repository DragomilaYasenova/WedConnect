package restaurant;

import restaurant.Menu.MenuManager;
import restaurant.amenity.AmenityManager;
import restaurant.date.DateManager;
import restaurant.hall.HallManager;

public class Restaurant {
    private String name;
    private AmenityManager amenityManager;
    private MenuManager menuManager;
    private HallManager hallManager;
    private DateManager dateManager;

    public Restaurant(String name, AmenityManager amenityManager, MenuManager menuManager, HallManager hallManager, DateManager dateManager) {
        this.name = name;
        this.amenityManager = amenityManager;
        this.menuManager = menuManager;
        this.hallManager = hallManager;
        this.dateManager = dateManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AmenityManager getAmenityManager() {
        return amenityManager;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public HallManager getHallManager() {
        return hallManager;
    }

    public DateManager getDateManager() {
        return dateManager;
    }

    @Override
    public String toString() {
        return "Restaurant '" + name + "'\n" +
                "Amenities: " + amenityManager.getAmenities() + "\n" +
                "Halls Capacity: " + hallManager.getHallsMaxCapacity() + "\n" +
                "Menu Options: " + menuManager.getMenuOptions() + "\n";
    }
}