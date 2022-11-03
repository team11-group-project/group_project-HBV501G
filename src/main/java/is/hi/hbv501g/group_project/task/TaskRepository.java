package is.hi.hbv501g.group_project.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/***
 * A repository that holds tasks for the projects.
 */
@Repository
@Transactional(readOnly = true)
public interface TaskRepository
    extends JpaRepository<Task, Long> {

    List <Task> findByProjectId(Long projectId);

    @Override
    Optional<Task> findById(Long Id);
}
