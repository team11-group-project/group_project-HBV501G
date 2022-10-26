package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/***
 * This class implements a service for the projects. The service adds new projects to the project repository, finds all project the user is a member of, and finds all projects by ID.
 */
@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMembersRepository projectMembersRepository;
    private final ProjectMembersService projectMembersService;

    /***
     * Creates a new project and makes the user the owner.
     * @param request Name and description of the project.
     * @param user The ID of the user.
     */
    public void addProject(AddProjectRequest request, AppUser user){
        Project project = new Project(request.getName(), user.getId(), request.getDescription());
        projectRepository.save(project);
        projectMembersService.addMember(
                new ProjectMembers(project.getId(), user.getId(), "owner")
        );
    }

    /***
     * Finds all projects that the user is a member of.
     * @param user The ID of the user.
     * @return A list of projects (that the user is a member of).
     */
    public List<Project> findByUser(AppUser user) {
        List<ProjectMembers> projectMembersList = projectMembersRepository.findByUserId(user.getId());
        List<Long> projectIdList = new ArrayList<>();
        for(ProjectMembers pm : projectMembersList){
            projectIdList.add(pm.getProjectId());
        }
        return projectRepository.findByIdIn(projectIdList);
    }

    /***
     * Finds all projects by ID.
     * @param id The ID of the project
     * @return A list of all projects
     */
    public Optional<Project> findByProjectId(long id) {
        return projectRepository.findById(id);
    }
}
