package is.hi.hbv501g.group_project.task;

import javax.persistence.*;

/***
 * This class implements a comment for the tasks. The comment has an ID, Task ID, and some text.
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long taskId;
    private String text;

    public Comment(){
    }

    /***
     * This Constructs a comment with a specific ID, task ID, and comment text.
     * @param id
     * @param taskId
     * @param text
     */
    public Comment(long id, long taskId, String text) {
        this.id = id;
        this.taskId = taskId;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
