package is.hi.hbv501g.group_project.appuser;

import lombok.NoArgsConstructor;

/***
 * This class implements an email for the app user. It contains only the email of the user.
 */
@NoArgsConstructor
public class AppUserEmail {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
