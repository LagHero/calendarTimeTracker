package calendarView;

import calendarView.dataModel.Day;
import calendarView.dataModel.Task;
import calendarView.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
public class CalendarController {

    private static final String TASK_NOT_FOUND_FOR_ID = "Unable to find a task for the ID: '%s'";
    private static final String TASKS_NOT_FOUND_FOR_DATE_RANGE = "Unable to find a task for startDate: '%s' , and endDate: '%s'";
    private static final String ID_TASK_MISMATCH = "The given ID doesn't match the given task";
    private static final String MISSING_TASK = "Task cannot be null";
    private static final String MISSING_DATE = "%s cannot be null";
    private static final String ERROR_PARSING_DATE = "Unable to parse '%s', valid format is '%s'";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String START_DATE_LABEL = "Start Date";
    private static final String END_DATE_LABEL = "End Date";

    @Autowired
    private ITaskService taskService;

    @GetMapping()
    public List<Day> getTasks(@RequestParam(value = "startDate") String startDate,
                              @RequestParam(value = "endDate") String endDate) {

        RestPreconditions.checkNotNull(startDate, String.format(MISSING_DATE, START_DATE_LABEL));
        Date startDateValue = RestPreconditions.parseDate(startDate, DATE_FORMAT,
                String.format(ERROR_PARSING_DATE, START_DATE_LABEL, DATE_FORMAT));

        RestPreconditions.checkNotNull(startDate, String.format(MISSING_DATE, END_DATE_LABEL));
        Date endDateValue = RestPreconditions.parseDate(endDate, DATE_FORMAT,
                String.format(ERROR_PARSING_DATE, END_DATE_LABEL, DATE_FORMAT));

        return RestPreconditions.checkNotFound(taskService.find(startDateValue, endDateValue),
                String.format(TASKS_NOT_FOUND_FOR_DATE_RANGE, startDate, endDate));
    }

    @GetMapping("/{id}")
    public Task findTask(@PathVariable("id") int id) {
        return RestPreconditions.checkNotFound(taskService.find(id), String.format(TASK_NOT_FOUND_FOR_ID, id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Task task) {
        RestPreconditions.checkNotNull(task, MISSING_TASK);
        return taskService.create(task);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") int id, @RequestBody Task task) {
        RestPreconditions.checkNotNull(task, MISSING_TASK);
        RestPreconditions.checkArgument(id == task.getId(), String.format(ID_TASK_MISMATCH));
        RestPreconditions.checkNotFound(taskService.find(task.getId()), String.format(TASK_NOT_FOUND_FOR_ID, id));
        taskService.update(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") int id) {
        taskService.delete(id);
    }

}