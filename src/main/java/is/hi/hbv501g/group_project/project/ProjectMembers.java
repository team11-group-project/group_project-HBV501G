package is.hi.hbv501g.group_project.project;

import javax.persistence.*;

/***
 * This class defines members of projects.
 */
@Entity
@Table(name = "members")
public class ProjectMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long projectId;
    private long userId;
    private String authorities;

    public ProjectMembers() {
    }

    /***
     * This Constructs a project member with a specific project ID, user ID, and authorities.
     * @param projectId The ID of the project
     * @param userId The ID of the user
     * @param authorities All authorities available to that member
     */
    public ProjectMembers(long projectId, long userId, String authorities) {
        this.projectId = projectId;
        this.userId = userId;
        this.authorities = authorities;
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

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
