package com.project.manager.demo.component;

import com.project.manager.demo.model.Authority;
import com.project.manager.demo.model.User;
import com.project.manager.demo.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserComponent {
    private final UserRepository userRepository;

    public UserComponent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void addAdminUser() {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority(1, "ADMIN"));
        authorities.add(new Authority(2, "STUDENT"));
        userRepository.save(new User("admin", "admin1234", true, true,
                true, true, authorities));
        Set<Authority> testUserAuthorities = new HashSet<>();
        testUserAuthorities.add(new Authority(2, "STUDENT"));
        userRepository.save(new User("test", "test1234",
                true, true, true, true, testUserAuthorities));
    }
}
