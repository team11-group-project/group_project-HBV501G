package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        modelAndView.addObject("projects",projectService.findByUser(user));
        return modelAndView;
    }



    @RequestMapping(value = {"/addProject"}, method = RequestMethod.GET)
    public ModelAndView addProject(){
        ModelAndView modelAndView = new ModelAndView();
        Project project = new Project();
        modelAndView.addObject("project", project);
        modelAndView.setViewName("addProject"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    public ModelAndView addProject(AddProjectRequest request, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        if(request.getName().isEmpty()){
            modelAndView.addObject("successMessage", "Name cannot be empty!");
        }
        else if(bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }else {
            projectService.addProject(request, user);
            modelAndView.addObject("successMessage", "Project added!");
        }
        modelAndView.addObject("project", new Project());
        modelAndView.setViewName("addProject");
        return modelAndView;
    }
}
