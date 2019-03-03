package calendarView;

import calendarView.dataModel.Day;
import calendarView.dataModel.Task;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("ITaskService")
public interface ITaskService {

    Task find(int id);

    List<Day> find(Date startDate, Date endDate);

    int create(Task task);

    void update(Task task);

    void delete(int id);
}
