package calendarView;

import calendarView.dataModel.Day;
import calendarView.dataModel.Task;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("TaskService")
public class TaskService implements ITaskService {

    @Override
    public Task find(int id) {
        return null;
    }

    @Override
    public List<Day> find(Date startDate, Date endDate) {

        Calendar testCal = Calendar.getInstance();
        testCal.clear();
        testCal.set(2019, Calendar.APRIL, 1);
        Date testDate = testCal.getTime();

        testCal.set(Calendar.HOUR_OF_DAY, 9);
        testCal.set(Calendar.MINUTE, 30);
        Date testStartTime = testCal.getTime();

        testCal.set(Calendar.HOUR_OF_DAY, 11);
        testCal.set(Calendar.MINUTE, 15);
        Date testEndTime = testCal.getTime();


        List<Day> days = Lists.newArrayList();
        List<Task> assignments = Lists.newArrayList();

        String name = "name";
        String client = "client";
        String project = "project";
        assignments.add(new Task(1, testStartTime, testEndTime, name, client, project));
        days.add(new Day(testDate, assignments));
        return days;
    }

    @Override
    public int create(Task task) {
        return 0;
    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(int id) {

    }
}
