package com.project.manager.demo.controller.impl;

import com.project.manager.demo.controller.UserController;
import com.project.manager.demo.model.User;
import com.project.manager.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @Override
    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @Override
    @GetMapping("/{id}")
    public User findUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @Override
    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
