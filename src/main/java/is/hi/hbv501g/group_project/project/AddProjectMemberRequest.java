package is.hi.hbv501g.group_project.project;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/***
 * This class implements a request to add a member to a project. The request has only an email.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AddProjectMemberRequest {
    private final String email;

    public String getEmail() {
        return email;
    }
}
