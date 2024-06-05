package restaurant;

import client.CustomerPreferences;
import restaurant.Menu.MenuOptions;
import restaurant.amenity.Amenity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.FileOperations.extractBudgetFromFile;

public class RestaurantOfferAlgorithm {
    private static final int AMENITY_SCORE = 3;
    private static final int DATE_SCORE = 2;
    private static final int MENU_SCORE = 1;

    public List<Restaurant> offerRestaurants(CustomerPreferences preferences, List<Restaurant> allRestaurants) {
        Map<Restaurant, Integer> restaurantScores = new HashMap<>();

        for (Restaurant restaurant : allRestaurants) {
            int score = calculateScore(restaurant, preferences);
            if (score > 0) {
                restaurantScores.put(restaurant, score);
            }
        }

        if (restaurantScores.isEmpty()) {
            return new ArrayList<>();
        }

        List<Restaurant> suitableRestaurants = new ArrayList<>(restaurantScores.keySet());

        suitableRestaurants.sort((r1, r2) -> restaurantScores.get(r2) - restaurantScores.get(r1));

        return suitableRestaurants;
    }

    private int calculateScore(Restaurant restaurant, CustomerPreferences preferences) {
        int score = 0;

        for (Amenity amenity : preferences.getAmenityPreferences()) {
            if (restaurant.getAmenityManager().getAmenities().contains(amenity)) {
                score += AMENITY_SCORE;
            }
        }

        for (MenuOptions menuOption : preferences.getMenuPreferences()) {
            if (restaurant.getMenuManager().getMenuOptions().contains(menuOption)) {
                score += MENU_SCORE;
            }
        }

        int maxCapacity = 0;
        for (Integer capacity : restaurant.getHallManager().getHallsMaxCapacity().values()) {
            if (capacity > maxCapacity) {
                maxCapacity = capacity;
            }
        }

        if (maxCapacity >= preferences.getNumberOfGuests()) {
            score += DATE_SCORE;
        }

        int totalCost = preferences.getNumberOfGuests() * restaurant.getPricePerPerson();
        if (totalCost > (extractBudgetFromFile("./clients/" + preferences.getId() + ".txt") * 0.4)) {
            score = 0;
        }

        return score;
    }
}