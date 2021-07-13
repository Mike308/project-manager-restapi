package com.project.manager.demo.component;

import com.project.manager.demo.model.Authority;
import com.project.manager.demo.model.User;
import com.project.manager.demo.service.StudentService;
import com.project.manager.demo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserComponent {
    private final UserService userService;
    private final StudentService studentService;

    public UserComponent(UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }


    @PostConstruct
    private void addAdminUser() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(1, "ADMIN"));
        List<User> users = userService.findAllUsers();
        if (users.parallelStream().anyMatch(v -> v.getUsername().equals("admin")))
            return;
        userService.addUser(new User("admin", "passcode8989", true, true,
                true, true, authorities));

    }
}
