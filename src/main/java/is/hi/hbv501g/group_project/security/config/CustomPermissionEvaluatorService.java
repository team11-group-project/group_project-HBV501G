package is.hi.hbv501g.group_project.security.config;

import is.hi.hbv501g.group_project.appuser.AppUser;
import is.hi.hbv501g.group_project.project.ProjectMembersService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomPermissionEvaluatorService {

    private final ProjectMembersService projectMembersService;


    public boolean hasPermission(Authentication authentication, Long projectId) {
        List<AppUser> members = projectMembersService.findMembersByProjectId(projectId);
        AppUser user = (AppUser) authentication.getPrincipal();
        return members.contains(user);
    }

}
