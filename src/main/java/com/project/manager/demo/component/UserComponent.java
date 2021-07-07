package com.project.manager.demo.component;

import com.project.manager.demo.model.Authority;
import com.project.manager.demo.model.Student;
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
        authorities.add(new Authority(2, "STUDENT"));
        userService.addUser(new User("admin", "passcode8989", true, true,
                true, true, authorities));
        List<Authority> testUserAuthorities = new ArrayList<>();
        testUserAuthorities.add(new Authority(2, "STUDENT"));
        userService.addUser(new User("test", "test1234",
                true, true, true, true, testUserAuthorities));
        Student student = studentService.getStudentByIndexNumber("100234");
        userService.addUser(new User(student, "passcode8989", testUserAuthorities));

    }
}
