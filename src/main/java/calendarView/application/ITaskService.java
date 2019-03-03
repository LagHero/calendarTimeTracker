package calendarView.application;

import calendarView.datamodel.Day;
import calendarView.datamodel.Task;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component("ITaskService")
public interface ITaskService {

    Task find(Integer id);

    List<Day> find(LocalDate startDate, LocalDate endDate);

    Integer create(Task task);

    void update(Task task);

    void delete(Integer id);
}
