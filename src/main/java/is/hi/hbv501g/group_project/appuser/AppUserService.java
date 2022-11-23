package is.hi.hbv501g.group_project.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 * This class implements a service for the app users. The service adds tasks to the repository of tasks.
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /***
     * Loads the user using the user's email if it is a valid email. Otherwise, gives error.
     * @param email The user's email.
     * @return Returns User if email is valid, otherwise, gives error.
     * @throws UsernameNotFoundException Tells the user that the given email does not exist.
     */
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND, email)));
    }

    /***
     * Creates a new account with an email and password that has been assigned to the user.
     * @param appUser User that is being signed up
     */
    public void signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists){
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);
    }

    /**
     *
     * @param id of the appUser
     * @return appUser
     */
    public AppUser findById(long id){
        return appUserRepository.findById(id).get();
    }

    public List <String> getEmailsByUser(List <AppUser> users){
        List <String> emails = new ArrayList<String>();
        for (AppUser user : users){
            emails.add(user.getEmail());
        }
        return emails;
    }
}
