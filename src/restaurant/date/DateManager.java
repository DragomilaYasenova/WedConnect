package restaurant.date;

import java.util.List;

public interface DateManager {
    List<String> getTakenDates();
    void setTakenDates(List<String> takenDates);
}
