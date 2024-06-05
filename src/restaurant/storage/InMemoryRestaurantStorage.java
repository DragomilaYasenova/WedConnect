package restaurant.storage;

import java.util.HashSet;
import java.util.Set;

public class InMemoryRestaurantStorage extends RestaurantStorage{
    private final Set<String> restaurants = new HashSet<>();

    @Override
    public Set<String> loadRestaurants() {
        return new HashSet<>(restaurants);
    }

    @Override
    public void saveRestaurant(String restaurantInfo) {
        restaurants.add(restaurantInfo);
    }
}
