package calendarView;

import calendarView.dataModel.Day;
import calendarView.dataModel.Task;

import java.util.Date;
import java.util.List;

public interface ITaskService {

    public Task find(int id);

    public List<Day> find(Date startDate, Date endDate);

    public int create(Task task);

    public void update(Task task);

    public void delete(int id);
}
