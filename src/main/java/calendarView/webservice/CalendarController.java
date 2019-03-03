package calendarView.webservice;

import calendarView.application.ITaskService;
import calendarView.datamodel.Day;
import calendarView.datamodel.Task;
import calendarView.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/task")
public class CalendarController {

    private static final String TASK_NOT_FOUND_FOR_ID = "Unable to find a task for the ID: '%s'";
    private static final String TASKS_NOT_FOUND_FOR_DATE_RANGE = "Unable to find a task for startDate: '%s' , and endDate: '%s'";
    private static final String START_DATE_AFTER_END_DATE = "Invalid dates: startDate: '%s' must be on or before endDate: '%s'";
    private static final String ID_TASK_MISMATCH = "The given ID doesn't match the given task";
    private static final String MISSING_TASK = "Task cannot be null";
    private static final String MISSING_DATE = "%s cannot be null";
    private static final String START_DATE_LABEL = "Start Date";
    private static final String END_DATE_LABEL = "End Date";

    @Autowired
    private ITaskService taskService;

    @GetMapping()
    public List<Day> getTasks(@RequestParam(value = "startDate")
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                              @RequestParam(value = "endDate")
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        RestPreconditions.checkNotNull(startDate, String.format(MISSING_DATE, START_DATE_LABEL));
        RestPreconditions.checkNotNull(endDate, String.format(MISSING_DATE, END_DATE_LABEL));
        RestPreconditions.checkArgument(!startDate.isAfter(endDate),
                String.format(START_DATE_AFTER_END_DATE, startDate, endDate));
        return RestPreconditions.checkNotFound(taskService.find(startDate, endDate),
                String.format(TASKS_NOT_FOUND_FOR_DATE_RANGE, startDate, endDate));
    }

    @GetMapping("/{id}")
    public Task findTask(@PathVariable("id") Integer id) {
        return RestPreconditions.checkNotFound(taskService.find(id), String.format(TASK_NOT_FOUND_FOR_ID, id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Task task) {
        RestPreconditions.checkNotNull(task, MISSING_TASK);
        LocalTime startDate = task.getStartTime();
        LocalTime endDate = task.getEndTime();
        RestPreconditions.checkNotNull(startDate, String.format(MISSING_DATE, START_DATE_LABEL));
        RestPreconditions.checkNotNull(endDate, String.format(MISSING_DATE, END_DATE_LABEL));
        RestPreconditions.checkArgument(!startDate.isAfter(endDate),
                String.format(START_DATE_AFTER_END_DATE, startDate, endDate));
        return taskService.create(task);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Integer id, @RequestBody Task task) {
        RestPreconditions.checkNotNull(task, MISSING_TASK);
        RestPreconditions.checkArgument(id == task.getId(), String.format(ID_TASK_MISMATCH));
        RestPreconditions.checkNotFound(taskService.find(id), String.format(TASK_NOT_FOUND_FOR_ID, id));
        LocalTime startDate = task.getStartTime();
        LocalTime endDate = task.getEndTime();
        RestPreconditions.checkArgument(!startDate.isAfter(endDate),
                String.format(START_DATE_AFTER_END_DATE, startDate, endDate));
        taskService.update(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        RestPreconditions.checkNotFound(taskService.find(id), String.format(TASK_NOT_FOUND_FOR_ID, id));
        taskService.delete(id);
    }

}