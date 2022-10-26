package is.hi.hbv501g.group_project.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * A repository that holds projects for the app.
 */
@Repository
@Transactional(readOnly = true)
public interface ProjectRepository
        extends JpaRepository<Project, Long> {
    List<Project> findByIdIn(List<Long> projectIdList);
}