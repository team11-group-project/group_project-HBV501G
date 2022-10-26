package is.hi.hbv501g.group_project.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/***
 * This class implements a password encoder for the app.
 */
@Configuration
public class PasswordEncoder {
    /***
     * Encodes given password
     * @return Encoded password
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
