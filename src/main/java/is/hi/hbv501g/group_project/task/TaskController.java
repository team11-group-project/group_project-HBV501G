package is.hi.hbv501g.group_project.task;

import is.hi.hbv501g.group_project.project.ProjectService;
import is.hi.hbv501g.group_project.project.AddProjectRequest;
import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.project.Project;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @RequestMapping
    public ModelAndView saveTask(){
        ModelAndView modelAndView = new ModelAndView();
        Task task = new Task();
        modelAndView.addObject("task", task);
        modelAndView.setViewName("saveTask"); // resources/template/register.html
        return modelAndView;
    }

    /*@RequestMapping(value = "/saveTask", method = RequestMethod.POST)
    public ModelAndView saveTask(TaskServiceRequest request, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        if(request.getName().isEmpty()){
            modelAndView.addObject("successMessage", "Please specify name of given task!");
        }
        if(request.getStatus().isEmpty()){
            modelAndView.addObject("successMessage", "Please specify status of given task!");
        }
        if(request.getStart().equals(0) || request.getStart().equals("")){
            modelAndView.addObject("successMessage", "Please add task start time!");
        }
        if(request.getDeadline().equals(0) || request.getDeadline().equals("")){
            modelAndView.addObject("successMessage", "Please add task deadline!");
        }
        else if(bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }else {
            taskService.saveTask(request, user, project, task);
            modelAndView.addObject("successMessage", "Task added!");
        }
        modelAndView.addObject("task", new Task());
        modelAndView.setViewName("saveTask");
        return modelAndView;
    }*/
}
