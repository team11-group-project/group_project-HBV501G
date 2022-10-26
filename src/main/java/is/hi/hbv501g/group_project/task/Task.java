package is.hi.hbv501g.group_project.task;

import javax.persistence.*;
import java.util.Date;

/***
 * This class implements a task for the projects. The task has an ID, project ID, name, user ID, starting time, deadline, and task status.
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long projectId;
    private String name;
    private Long userId;
    private Date start;
    private Date deadline;
    private String status;

    public Task(){
    }

    /***
     * This Constructs a task with a specific ID, project ID, name, user ID, start time, deadline, and status of the task.
     * @param id The ID of the task.
     * @param projectId The ID of the project.
     * @param name The name of the task.
     * @param userId The ID of the user.
     * @param start The starting time of the task.
     * @param deadline The deadline for the task
     * @param status The status for the task.
     */
    public Task(long id, long projectId, String name, Long userId, Date start, Date deadline, String status) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.userId = userId;
        this.start = start;
        this.deadline = deadline;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
