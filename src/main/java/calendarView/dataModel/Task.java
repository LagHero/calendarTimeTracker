package calendarView.dataModel;

import java.util.Date;

public class Task {

    private final int id;
    private final Date startTime;
    private final Date endTime;
    private final String name;
    private final String client;
    private final String project;

    public Task(int id, Date startTime, Date endTime, String name, String client, String project) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.client = client;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
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
