package is.hi.hbv501g.group_project.project;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/***
 * This class implements a service for the project members. The service adds a user as a member to the repository of project members.
 */
@Service
@AllArgsConstructor
public class ProjectMembersService {

    private final ProjectMembersRepository projectMembersRepository;

    /***
     * Adds a user as a member to the project.
     * @param projectMembers User that is to be made a member.
     */
    public void addMember(ProjectMembers projectMembers){
        projectMembersRepository.save(projectMembers);
    }

}