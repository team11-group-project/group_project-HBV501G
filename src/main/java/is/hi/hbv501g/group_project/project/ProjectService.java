package is.hi.hbv501g.group_project.project;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    public Project addProject(Project project){
        return projectRepository.save(project);
    }

}
