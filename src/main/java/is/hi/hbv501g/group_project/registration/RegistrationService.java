package is.hi.hbv501g.group_project.registration;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.appuser.AppUserRole;
import is.hi.hbv501g.group_project.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public void register(RegistrationRequest request) {
//        boolean isValidEmail = emailValidator.test(request.getEmail());
//        if (!isValidEmail) {
//            throw new IllegalStateException("email not valid");
//        }
        appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER)
        );
    }
}
