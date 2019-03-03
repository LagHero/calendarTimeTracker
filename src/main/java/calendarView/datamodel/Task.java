package calendarView.datamodel;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private final LocalDate day;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalTime endTime;
    private Integer id;
    private final String name;
    private final String client;
    private final String project;

    public Task(Integer id, LocalDate day, LocalTime startTime, LocalTime endTime, String name, String client, String project) {
        this.id = id;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.client = client;
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public String getClient() {
        return client;
    }

    public String getProject() {
        return project;
    }
}
