package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.appuser.AppUserRole;
import is.hi.hbv501g.group_project.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/***
 * This class implements a request to add a project. The request has a name, and description.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AddProjectRequest {
    private final String name;
    private final String description;
}
