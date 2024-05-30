package restaurant.hall;

import java.util.Map;

public interface HallManager {
    void addHall(String hallName, int maxCapacity);
    void removeHall(String hallName);
    Map<String, Integer> getHallsMaxCapacity();
}
