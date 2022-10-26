package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;

import javax.persistence.*;

/***
 * This class implements a project for the app. The project has an ID, name, owner, and description.
 */
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Long owner;
    private String description;

    public Project(){

    }

    /***
     * This Constructs a project with a specific project name, project owner, and description of project,
     * @param name Name of the project.
     * @param owner Owner of the project.
     * @param description Description of the project.
     */
    public Project(String name, Long owner, String description) {
        this.name = name;
        this.owner = owner;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }
}
