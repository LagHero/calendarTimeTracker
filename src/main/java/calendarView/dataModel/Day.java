package calendarView.dataModel;

import java.util.Date;
import java.util.List;

public class Day {

    private final Date date;
    private final List<Task> assignments;

    public Day(Date date, List<Task> assignments) {
        this.date = date;
        this.assignments = assignments;
    }

    public Date getDate() {
        return date;
    }

    public List<Task> getAssignments() {
        return assignments;
    }
}
