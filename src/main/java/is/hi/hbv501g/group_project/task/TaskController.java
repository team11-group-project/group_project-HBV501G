package is.hi.hbv501g.group_project.task;

import is.hi.hbv501g.group_project.project.*;
import lombok.AllArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/***
 * This class implements a controller for the tasks.
 */
@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ProjectMembersService projectMembersService;
    private final ProjectService projectService;

    /***
     * Model and View to display saveTask
     * @return
     */
    @RequestMapping(value = {"/{projectId}/addTask"}, method = RequestMethod.GET)
    public ModelAndView addMemberToProject(@PathVariable("projectId") long id){
        ModelAndView modelAndView = new ModelAndView();
        Task task = new Task();
        modelAndView.addObject("task", task);
        modelAndView.addObject("users", projectMembersService.findMembersByProjectId(id));
        modelAndView.addObject("project", projectService.findByProjectId(id).get());
        modelAndView.setViewName("addTask"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = {"/{projectId}/addTask"}, method = RequestMethod.POST)
    public ModelAndView addMemberToProject(@PathVariable("projectId") long projectId, AddTaskRequest request, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("project", projectService.findByProjectId(projectId).get());
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
