package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.appuser.AppUserRole;
import is.hi.hbv501g.group_project.registration.RegistrationRequest;
import is.hi.hbv501g.group_project.project.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddProjectService {

    private final ProjectService projectService;
    private final ProjectMembersService projectMembersService;


    public void addProject(AddProjectRequest request, AppUser user) {
        projectMembersService.addMember(
                new ProjectMembers(projectService.addProject(
                        new Project(
                                request.getName(),
                                user.getId(),
                                request.getDescription())
                ).getId(),
                        user.getId(),
                        "owner")
        );
    }
}
