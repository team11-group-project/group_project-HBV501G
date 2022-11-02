package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 * This class implements a service for the project members. The service adds a user as a member to the repository of project members.
 */
@Service
@AllArgsConstructor
public class ProjectMembersService {

    private final ProjectMembersRepository projectMembersRepository;
    private final AppUserRepository appUserRepository;

    /***
     * Adds a user as a member to the project.
     * @param projectMembers User that is to be made a member.
     */
    public void addMember(ProjectMembers projectMembers){
        projectMembersRepository.save(projectMembers);
    }

    public List <AppUser> findMembersByProjectId(Long projectId){
        List <AppUser> members = new ArrayList<>();
        List <ProjectMembers> projectMembers = projectMembersRepository.findByProjectId(projectId);
        for (ProjectMembers member : projectMembers) {
            members.add(appUserRepository.findById(member.getUserId()).get());
        }
        return members;
    }
}