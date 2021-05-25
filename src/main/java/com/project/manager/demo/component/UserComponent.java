package com.project.manager.demo.component;

import com.project.manager.demo.model.Authority;
import com.project.manager.demo.model.User;
import com.project.manager.demo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserComponent {
    private final UserService userService;

    public UserComponent(UserService userService) {
        this.userService = userService;
    }


    @PostConstruct
    private void addAdminUser() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(1, "ADMIN"));
        authorities.add(new Authority(2, "STUDENT"));
        userService.addUser(new User("admin", "passcode8989", true, true,
                true, true, authorities));
        List<Authority> testUserAuthorities = new ArrayList<>();
        testUserAuthorities.add(new Authority(2, "STUDENT"));
        userService.addUser(new User("test", "test1234",
                true, true, true, true, testUserAuthorities));
    }
}
