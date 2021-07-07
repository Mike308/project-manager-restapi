package com.project.manager.demo.controller;

import com.project.manager.demo.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    User addUser(User user);
    User updateUser(long id, User user);
    User findUserById(long id);
    List<User> findAllUsers();
    void deleteUser(long id);
    ResponseEntity<Object> changePassword(User user);
}
