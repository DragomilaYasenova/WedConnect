package restaurant.date;

import restaurant.date.DateManager;
import utils.ColorManager;

import java.util.*;



public class DateManagerImpl implements DateManager {
    private Map<String, List<String>> reservations;

    public DateManagerImpl() {
        this.reservations = new HashMap<>();
    }

    @Override
    public boolean reserveHall(String date, String hallName, int numberOfGuests) {
        if (!reservations.containsKey(date)) {
            reservations.put(date, new ArrayList<>());
        }

        List<String> hallsOnDate = reservations.get(date);
        if (!hallsOnDate.contains(hallName)) {
            hallsOnDate.add(hallName);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> getAvailableHallsForDate(String date, int numberOfGuests) {
        List<String> availableHalls = new ArrayList<>();

        if (reservations.containsKey(date)) {
            List<String> hallsOnDate = reservations.get(date);
            availableHalls.addAll(hallsOnDate);
        }

        System.out.println(ColorManager.PINK + availableHalls + ColorManager.RESET);

        return availableHalls;
    }
}
