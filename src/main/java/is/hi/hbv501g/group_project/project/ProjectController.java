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

/***
 * This class implements a controller for the Projects.
 */
@RestController
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /***
     * Model and View to display Home
     * @return Returns the Model and View for Home
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof String){
            modelAndView.setViewName("notLoggedIn");
        }
        else {
            AppUser user = (AppUser) authentication.getPrincipal();
            modelAndView.setViewName("home"); // resources/template/home.html
            modelAndView.addObject("user", user);
            modelAndView.addObject("projects", projectService.findByUser(user));
        }
        return modelAndView;
    }

    /***
     * Model and View to display addProject
     * @return Returns the Model and View for addProject
     */
    @RequestMapping(value = {"/addProject"}, method = RequestMethod.GET)
    public ModelAndView addProject(){
        ModelAndView modelAndView = new ModelAndView();
        Project project = new Project();
        modelAndView.addObject("project", project);
        modelAndView.setViewName("addProject"); // resources/template/register.html
        return modelAndView;
    }

    /***
     * Adds Project if name and details are not empty. Otherwise, displays error message.
     * @param request
     * @param bindingResult
     * @param modelMap
     * @return Returns the Model and View for Project and addProject
     */
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
