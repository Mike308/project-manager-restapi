package com.project.manager.demo.validator;

import com.project.manager.demo.model.Project;
import com.project.manager.demo.model.Student;
import com.project.manager.demo.model.User;
import com.project.manager.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ProjectAndTaskPermissionValidator {
    private final UserService userService;
    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    public ProjectAndTaskPermissionValidator(UserService userService) {
        this.userService = userService;
    }

    public void checkPermissionToProject(Project project) {
        if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            checkPermissionToProject(project, user);
        } else {
            String name = authentication.getPrincipal().toString();
            User user = ((User)(userService.loadUserByUsername(name)));
            checkPermissionToProject(project, user);
        }
    }

    private void checkPermissionToProject(Project project, User user) {
        Student student = user.getStudent();
        if (student == null || user.getAuthorities().parallelStream().filter(grantedAuthority -> grantedAuthority.getAuthority() != null)
            .anyMatch(grantedAuthority ->  grantedAuthority.getAuthority().equals("ROLE_ADMIN")))
            return;
        if (project.getStudents()
                .parallelStream()
                .filter(studentInProject -> studentInProject.getIndexNumber() != null).anyMatch(studentInProject -> studentInProject.getIndexNumber().equals(student.getIndexNumber()))) {
            return;
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public boolean limitProjectsList(Project project) {
        System.out.println("Authentication: " + authentication);
        if (authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return limitProjectsList(project, user);
        } else {
            String name = authentication.getPrincipal().toString();
            User user = ((User)(userService.loadUserByUsername(name)));
            return limitProjectsList(project, user);
        }
    }

    private boolean limitProjectsList(Project project, User user) {
        Student student = user.getStudent();
        if (student == null || user.getAuthorities().parallelStream().filter(grantedAuthority -> grantedAuthority.getAuthority() != null)
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN")))
            return true;
        if (project.getStudents()
                .parallelStream()
                .filter(studentInProject -> studentInProject.getIndexNumber() != null).anyMatch(studentInProject -> studentInProject.getIndexNumber().equals(student.getIndexNumber()))) {
            return true;
        } else {
            return false;
        }
    }

}
