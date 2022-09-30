package is.hi.hbv501g.group_project.registration;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private final EmailValidator emailValidator;
    private AppUserRepository appUserRepository;

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        AppUser appUser = new AppUser();
        modelAndView.addObject("appUser", appUser);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(RegistrationRequest request, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        boolean isValidEmail = emailValidator.test(request.getEmail());
        boolean userExists = appUserRepository.findByEmail(request.getEmail()).isPresent();
        if (!isValidEmail) {
            modelAndView.addObject("successMessage", "Email invalid");
        }
        else if(userExists){
            modelAndView.addObject("successMessage", "User with email exists");
        }
        else if(bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }else {
            registrationService.register(request);
            modelAndView.addObject("successMessage", "User registered successfully!");
        }
        modelAndView.addObject("appUser", new AppUser());
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
