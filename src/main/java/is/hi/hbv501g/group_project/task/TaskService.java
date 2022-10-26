package is.hi.hbv501g.group_project.task;


import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.project.AddProjectRequest;
import is.hi.hbv501g.group_project.project.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
     * @param request The name, starting time, deadline, and status of the task.
     * @param user The ID of the user.
     * @param project The ID of the project.
     * @param task The ID of the task.
     */
    public void saveTask(TaskServiceRequest request, AppUser user, Project project, Task task) {
        taskRepository.save(
                new Task(
                        task.getId(),
                        project.getId(),
                        request.getName(),
                        user.getId(),
                        request.getStart(),
                        request.getDeadline(),
                        request.getStatus())
        );
    }

}
