package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.appuser.AppUserRole;
import is.hi.hbv501g.group_project.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddProjectService {

    private final ProjectService projectService;
    public void addProject(AddProjectRequest request, AppUser user) {
        projectService.addProject(
                new Project(
                        request.getName(),
                        user.getId(),
                        request.getDescription())
        );
    }
}
