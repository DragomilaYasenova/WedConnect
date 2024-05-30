package restaurant.date;

import restaurant.date.DateManager;

import java.util.ArrayList;
import java.util.List;

public class DateManagerImpl implements DateManager {
    private List<String> takenDates = new ArrayList<>();

    @Override
    public List<String> getTakenDates() {
        return takenDates;
    }

    @Override
    public void setTakenDates(List<String> takenDates) {
        this.takenDates = takenDates;
    }
}
