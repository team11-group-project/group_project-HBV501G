package is.hi.hbv501g.group_project.project;

import is.hi.hbv501g.group_project.appuser.AppUser;

import javax.persistence.*;

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



}
