package is.hi.hbv501g.group_project.project;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AddProjectMemberRequest {
    private final String email;

    public String getEmail() {
        return email;
    }
}
