package is.hi.hbv501g.group_project.project;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class ProjectMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;
    private long userId;

    public ProjectMembers() {
    }

    public ProjectMembers(long projectId, long userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
