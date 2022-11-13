package is.hi.hbv501g.group_project.home;

import is.hi.hbv501g.group_project.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class HomeController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notLoggedIn");
        return modelAndView;
    }
}
