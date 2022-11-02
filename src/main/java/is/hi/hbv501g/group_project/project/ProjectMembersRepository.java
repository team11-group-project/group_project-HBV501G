package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * A repository that holds project members for the projects.
 */
@Repository
@Transactional(readOnly = true)
public interface ProjectMembersRepository
        extends JpaRepository<ProjectMembers, Long> {
    List<ProjectMembers> findByUserId(long user);

    List<ProjectMembers> findByProjectId(long id);
}
