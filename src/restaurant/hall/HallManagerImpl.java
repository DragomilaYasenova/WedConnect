package restaurant.hall;

import restaurant.hall.HallManager;

import java.util.LinkedHashMap;
import java.util.Map;

public class HallManagerImpl implements HallManager {
    private Map<String, Integer> hallsMaxCapacity = new LinkedHashMap<>();

    @Override
    public void addHall(String hallName, int maxCapacity) {
        if (maxCapacity > 10){
            hallsMaxCapacity.put(hallName, maxCapacity);
        }
    }

    @Override
    public void removeHall(String hallName) {
        hallsMaxCapacity.remove(hallName);
    }

    @Override
    public Map<String, Integer> getHallsMaxCapacity() {
        return hallsMaxCapacity;
    }
}
