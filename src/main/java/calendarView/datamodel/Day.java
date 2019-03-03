package calendarView.datamodel;

import java.time.LocalDate;
import java.util.List;

public class Day {

    private final LocalDate date;
    private final List<Task> tasks;

    public Day(LocalDate date, List<Task> tasks) {
        this.date = date;
        this.tasks = tasks;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
