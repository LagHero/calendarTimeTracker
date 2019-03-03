package calendarView.persistence;

import calendarView.datamodel.Task;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class uses a map internally.  <br/>
 * TODO: Implement a persistence layer that actually saves the data between executions
 */
@Component("TaskDao")
public class TaskDao implements DAO<Task> {

    private Map<Integer, Task> persistedTasks = Maps.newHashMap();

    @Override
    public Optional<Task> get(int id) {
        return Optional.ofNullable(persistedTasks.get(id));
    }

    @Override
    public Collection<Task> getAll() {
        return persistedTasks.values();
    }

    public Collection<Task> getAll(LocalDate day) {
        List<Task> returnList = Lists.newArrayList();
        for (Task task : persistedTasks.values()) {
            if (day.isEqual(task.getDay())) {
                returnList.add(task);
            }
        }
        return returnList;
    }

    @Override
    public void save(Task task) {
        persistedTasks.put(task.getId(), task);
    }

    @Override
    public void update(Task task) {
        persistedTasks.put(task.getId(), task);
    }

    @Override
    public void delete(Task task) {
        persistedTasks.remove(task.getId());
    }
}
