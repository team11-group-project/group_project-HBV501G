package is.hi.hbv501g.group_project.task;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import java.util.Date;

/***
 * This class implements a request to add a task to a project. The request has a name, status of task, start time, and deadline.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TaskServiceRequest {
    private final String name;
    private final String status;
    private final Date start;
    private final Date deadline;
}
