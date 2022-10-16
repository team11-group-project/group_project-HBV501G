package is.hi.hbv501g.group_project.project;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectMembersService {

    private final ProjectMembersRepository projectMembersRepository;
    public void addMember(ProjectMembers projectMembers){
        projectMembersRepository.save(projectMembers);
    }

}