package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.appuser.AppUserEmail;
import is.hi.hbv501g.group_project.appuser.AppUserRepository;
import is.hi.hbv501g.group_project.appuser.AppUserService;
import is.hi.hbv501g.group_project.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class ProjectViewController {

    //Logger logger = LoggerFactory.getLogger(ProjectViewController.class);

    private final ProjectService projectService;
    private final AppUserRepository appUserRepository;
    private final ProjectMembersService projectMembersService;
    private final AppUserService appUserService;

    @RequestMapping(value = {"/{projectId}"}, method = RequestMethod.GET)
    public ModelAndView showProject(@PathVariable("projectId") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project");
        modelAndView.addObject("project", projectService.findByProjectId(id).get());
        return modelAndView;
    }

    @RequestMapping(value = {"/{projectId}/addMemberToProject"}, method = RequestMethod.GET)
    public ModelAndView addMemberToProject(@PathVariable("projectId") long id){
        ModelAndView modelAndView = new ModelAndView();
        AppUserEmail email = new AppUserEmail();
        modelAndView.addObject("email", email);
        modelAndView.addObject("project", projectService.findByProjectId(id).get());
        modelAndView.setViewName("addMemberToProject"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = {"/{projectId}/addMemberToProject"}, method = RequestMethod.POST)
    public ModelAndView addMemberToProject(@PathVariable("projectId") long projectId, AddProjectMemberRequest request, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        boolean userExists = appUserRepository.findByEmail(request.getEmail()).isPresent();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();
        if (!userExists) {
            modelAndView.addObject("successMessage", "No user with email: " + request.getEmail());
        }
        else if(request.getEmail() == user.getEmail()){
            modelAndView.addObject("successMessage", "You already have access to this project");
        }
        else if(bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {
            projectMembersService.addMember(new ProjectMembers(
                    projectId,
                    ((AppUser) appUserRepository.findByEmail(request.getEmail()).get()).getId(),
                    "Contributor"
            ));
            modelAndView.addObject("successMessage", "User added to project!");
        }
        modelAndView.addObject("email", new AppUserEmail());
        modelAndView.setViewName("addMemberToProject");
        return modelAndView;
    }


}
