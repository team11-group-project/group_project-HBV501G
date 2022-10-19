package is.hi.hbv501g.group_project.task;


import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.project.AddProjectRequest;
import is.hi.hbv501g.group_project.project.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
//public interface TaskService {
public class TaskService {

    private final TaskRepository taskRepository;

    private final Project project;

    private final TaskService taskService;

    public void saveTask(Task task){
        taskRepository.save(task);
    }


    public void saveTask(TaskServiceRequest request, AppUser user, Project project, Task task) {
        taskService.saveTask(
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
