package is.hi.hbv501g.group_project.task;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

/***
 * This class implements a service for the tasks. The service adds tasks to the repository of tasks.
 */
@Service
@AllArgsConstructor
//public interface TaskService {
public class TaskService {


    private final TaskRepository taskRepository;

    /***
     * Adds a new task to the project.
     * @param request The name, deadline, and owner of the task.
     * @param projectId The ID of the project.
     */
    public void saveTask(AddTaskRequest request, Long projectId) {
        taskRepository.save(
                new Task(projectId,
                        request.getName(),
                        request.getOwnerUserId(),
                        new Date(),
                        Date.from(LocalDate.parse(request.getDeadline()).atStartOfDay().toInstant(ZoneOffset.UTC)),
                        "In progress")
        );
    }

}
