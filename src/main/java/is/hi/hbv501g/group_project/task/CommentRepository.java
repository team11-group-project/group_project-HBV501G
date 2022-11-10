package is.hi.hbv501g.group_project.task;

import is.hi.hbv501g.group_project.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * A repository that holds the comments for the tasks.
 */
@Repository
@Transactional(readOnly = true)
public interface CommentRepository
    extends JpaRepository<Comment, Long> {

    List<Comment> findByTaskId(Long taskId);
}
