package com.project.manager.demo.repository;

import com.project.manager.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
    List<User> findAll();
}
