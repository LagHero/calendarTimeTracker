package calendarView;

import calendarView.dataModel.Day;
import calendarView.dataModel.Task;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class CalendarController {

    @Autowired
    private ITaskService service;

    @GetMapping
    public List<Day> getTasks(@RequestParam(value = "startDate", required = true) Date startDate,
                                   @RequestParam(value = "endDate", required = true) Date endDate) {
        Preconditions.checkNotNull(startDate);
        Preconditions.checkNotNull(endDate);
        return RestPreconditions.checkFound(service.find(startDate, endDate));
    }

    @GetMapping(value = "/{id}")
    public Task findTask(@PathVariable("id") int id) {
        return RestPreconditions.checkFound(service.find(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Task task) {
        Preconditions.checkNotNull(task);
        return service.create(task);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Task task) {
        Preconditions.checkNotNull(task);
        Preconditions.checkArgument(id == task.getId());
        Preconditions.checkNotNull(service.find(task.getId()));
        service.update(task);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

}