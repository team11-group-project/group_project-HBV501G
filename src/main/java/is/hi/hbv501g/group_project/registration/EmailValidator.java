package is.hi.hbv501g.group_project.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate <String> {
    /***
     * Makes sure that email has correct format and can be used for the user's account.
     * @param s Text that user inputs and is to be tested.
     * @return Either correct or incorrect format.
     */
    @Override
    public boolean test(String s){

        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(s).matches();
    }
}
