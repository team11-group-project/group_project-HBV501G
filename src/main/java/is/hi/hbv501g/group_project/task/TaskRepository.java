package is.hi.hbv501g.group_project.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/***
 * A repository that holds tasks for the projects.
 */
@Repository
@Transactional(readOnly = true)
public interface TaskRepository
    extends JpaRepository<Task, Long> {
}
