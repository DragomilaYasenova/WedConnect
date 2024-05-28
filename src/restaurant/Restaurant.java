package restaurant;
import utils.ColorManager;

import java.util.*;

public class Restaurant {
    private String name;
    private Set<Amenity> amenity;
    private Set<MenuOptions> menuOptions;
    private Map<String, Integer> hallsMaxCapacity;
    private List<String> takenDates;

    public Restaurant(String name) {
        this.name = name;
        this.amenity = new LinkedHashSet<>();
        this.menuOptions = new LinkedHashSet<>();
        this.hallsMaxCapacity = new LinkedHashMap<>();
        this.takenDates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Amenity> getAmenity() {
        return amenity;
    }

    public void setAmenity(Set<Amenity> amenity) {
        this.amenity = amenity;
    }

    public Set<MenuOptions> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(Set<MenuOptions> menuOptions) {
        this.menuOptions = menuOptions;
    }

    public Map<String, Integer> getHallsMaxCapacity() {
        return hallsMaxCapacity;
    }

    public void setHallsMaxCapacity(Map<String, Integer> hallsMaxCapacity) {
        this.hallsMaxCapacity = hallsMaxCapacity;
    }

    public List<String> getTakenDates() {
        return takenDates;
    }

    public void setTakenDates(List<String> takenDates) {
        this.takenDates = takenDates;
    }

    public void addAmenity(Amenity type) {
        amenity.add(type);
    }

    public void removeAmenity(Amenity type) {
        amenity.remove(type);
    }

    public void addHall(String hallName, int maxCapacity) {
        hallsMaxCapacity.put(hallName, maxCapacity);
    }

    public void removeHall(String hallName) {
        hallsMaxCapacity.remove(hallName);
    }

    public void addMenuOptions(MenuOptions options) {
        menuOptions.add(options);
    }

    public void removeMenuOptions(MenuOptions options) {
        menuOptions.remove(options);
    }

    public void printTakenDates() {
        System.out.println("Taken dates: " + takenDates);
    }

    @Override
    public String toString() {
        return "Restaurant '" + ColorManager.CYAN + name + ColorManager.RESET + "'\n" +
                "Amenity: " + ColorManager.CYAN + amenity + ColorManager.RESET + "\n" +
                "Halls Capacity: " + ColorManager.CYAN + hallsMaxCapacity + ColorManager.RESET + "\n" +
                "Menu Options: " + ColorManager.CYAN + menuOptions + ColorManager.RESET;
    }
}