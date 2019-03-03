package calendarView.application;

import calendarView.datamodel.Day;
import calendarView.datamodel.Task;
import calendarView.persistence.TaskDao;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component("TaskService")
public class TaskService implements ITaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public Task find(Integer id) {
        return taskDao.get(id).orElse(null);
    }

    @Override
    public List<Day> find(LocalDate startDate, LocalDate endDate) {
        LocalDate dayIndex = startDate;
        List<Day> days = Lists.newArrayList();
        while (!dayIndex.isAfter(endDate)) {
            List<Task> tasks = Lists.newArrayList(taskDao.getAll(dayIndex));
            tasks.sort(new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    return o1.getStartTime().compareTo(o2.getStartTime());
                }
            });
            days.add(new Day(dayIndex, tasks));

            // Increment the day index
            dayIndex = dayIndex.plusDays(1);
        }
        return days;
    }

    @Override
    public Integer create(Task task) {
        // REVIEW: This ID business should probably be handled in the persistence layer?
        Integer newId = task.getId();
        // Make a new ID if one wasn't given
        if (newId == null) {
            Random r = new Random();
            newId = r.nextInt(900000) + 100000;
        }
        Optional<Task> optionalTask = taskDao.get(newId);
        // Keep making new IDs until we make an unique one
        while (optionalTask.isPresent()) {
            Random r = new Random();
            newId = r.nextInt(900000) + 100000;
            optionalTask = taskDao.get(newId);
        }
        // Unique ID created, set it, save it, and return the new ID.
        task.setId(newId);
        taskDao.save(task);
        return newId;
    }

    @Override
    public void update(Task task) {
        Optional<Task> optionalTask = taskDao.get(task.getId());
        if (optionalTask.isPresent()) {
            taskDao.update(optionalTask.get());
        } else {
            taskDao.save(task);
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<Task> optionalTask = taskDao.get(id);
        if (optionalTask.isPresent()) {
            taskDao.delete(optionalTask.get());
        }
    }
}
