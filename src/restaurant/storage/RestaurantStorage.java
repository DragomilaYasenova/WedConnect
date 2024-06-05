package restaurant.storage;

import java.util.Set;

public abstract class RestaurantStorage {
    public abstract Set<String> loadRestaurants();
    public abstract void saveRestaurant(String restaurantInfo);
}
