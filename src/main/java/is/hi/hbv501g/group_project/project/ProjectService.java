package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMembersRepository projectMembersRepository;
    private final ProjectMembersService projectMembersService;

    public void addProject(AddProjectRequest request, AppUser user){
        Project project = new Project(request.getName(), user.getId(), request.getDescription());
        projectRepository.save(project);
        projectMembersService.addMember(
                new ProjectMembers(project.getId(), user.getId(), "owner")
        );
    }

    public List<Project> findByUser(AppUser user) {
        List<ProjectMembers> projectMembersList = projectMembersRepository.findByUserId(user.getId());
        List<Long> projectIdList = new ArrayList<>();
        for(ProjectMembers pm : projectMembersList){
            projectIdList.add(pm.getProjectId());
        }
        return projectRepository.findByIdIn(projectIdList);
    }

    public Optional<Project> findByProjectId(long id) {
        return projectRepository.findById(id);
    }
}
