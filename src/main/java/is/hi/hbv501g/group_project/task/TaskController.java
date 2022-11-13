package is.hi.hbv501g.group_project.task;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.appuser.AppUserService;
import is.hi.hbv501g.group_project.project.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/***
 * This class implements a controller for the tasks.
 */
@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ProjectMembersService projectMembersService;
    private final ProjectService projectService;
    private final AppUserService appUserService;
    private final String pattern = "MM/dd/yyyy";
    private final DateFormat df = new SimpleDateFormat(pattern);

    /**
     * Model and view to display task by id
     * @param projectId the project id of the task
     * @param taskId the id of the task
     * @return
     */
    @RequestMapping(value = {"/{projectId}/{taskId}"}, method = RequestMethod.GET)
    public ModelAndView showTask(@PathVariable("projectId") long projectId, @PathVariable long taskId) {
        ModelAndView modelAndView = new ModelAndView();
        Task task = taskService.findByTaskId(taskId);
        modelAndView.addObject("task", task);
        modelAndView.addObject("deadline", df.format(task.getDeadline()));
        AppUser assigned = appUserService.findById(task.getOwnerUserId());
        modelAndView.addObject("assigned", assigned);
        modelAndView.addObject("project", projectService.findByProjectId(projectId));
        modelAndView.addObject("comments", taskService.findCommentByTaskId(taskId));
        modelAndView.addObject("comment", new Comment());
        modelAndView.setViewName("task");
        return modelAndView;
    }

    @RequestMapping(value = {"/{projectId}/{taskId}/addComment"}, method = RequestMethod.POST)
    public ModelAndView addComment(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId, @ModelAttribute("comment") Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        comment.setUserName(user.getFirstName());
        comment.setTaskId(taskId);
        taskService.saveComment(comment);
        String redirUrl = "/"+projectId+"/"+taskId;
        System.out.println(redirUrl);
        return new ModelAndView("redirect:"+redirUrl);
    }

    @RequestMapping(value = {"/{projectId}/{taskId}/editStatus"}, method = RequestMethod.POST)
    public ModelAndView editStatus(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId, @ModelAttribute("comment") Comment comment) {
        Task task = taskService.findByTaskId(taskId);
        task.setStatus(comment.getText());
        taskService.editTask(task);
        String redirUrl = "/"+projectId+"/"+taskId;
        System.out.println(redirUrl);
        return new ModelAndView("redirect:"+redirUrl);
    }

    @RequestMapping(value = {"/{projectId}/{taskId}/editDescription"}, method = RequestMethod.POST)
    public ModelAndView editTask(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId, @ModelAttribute("comment") Comment comment) {
        Task task = taskService.findByTaskId(taskId);
        task.setName(comment.getText());
        taskService.editTask(task);
        String redirUrl = "/"+projectId+"/"+taskId;
        System.out.println(redirUrl);
        return new ModelAndView("redirect:"+redirUrl);
    }

    /**
     * Model and view to display the add task form
     * @param projectId the id of the project we wish to add a task to
     * @return
     */
    @RequestMapping(value = {"/{projectId}/addTask"}, method = RequestMethod.GET)
    public ModelAndView addTask(@PathVariable("projectId") long projectId){
        ModelAndView modelAndView = new ModelAndView();
        Task task = new Task();
        modelAndView.addObject("task", task);
        modelAndView.addObject("users", projectMembersService.findMembersByProjectId(projectId));
        modelAndView.addObject("project", projectService.findByProjectId(projectId));
        modelAndView.setViewName("addTask"); // resources/template/register.html
        return modelAndView;
    }

    /**
     * Model and view to add member to a task
     * @param projectId the id of the project we wish to add a task to
     * @param request the name, deadline and assignee for this task
     * @param bindingResult
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/{projectId}/addTask"}, method = RequestMethod.POST)
    public ModelAndView addMemberTask(@PathVariable("projectId") long projectId, AddTaskRequest request, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("project", projectService.findByProjectId(projectId));
        if (request.getName().equals("")) {
            modelAndView.addObject("successMessage", "Task must have a name!");
        }
        else if(bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {
            taskService.saveTask(
                    request,
                    projectId
            );
            modelAndView.addObject("successMessage", "Task created!");
        }
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("users", projectMembersService.findMembersByProjectId(projectId));
        modelAndView.setViewName("addTask");
        return modelAndView;
    }
}
