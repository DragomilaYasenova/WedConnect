package restaurant.date;

import java.util.List;

public interface DateManager {
    boolean reserveHall(String date, String hallName, int numberOfGuests);
    List<String> getAvailableHallsForDate(String date, int numberOfGuests);
}
