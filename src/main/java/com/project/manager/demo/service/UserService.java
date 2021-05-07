package com.project.manager.demo.service;

import com.project.manager.demo.model.User;
import com.project.manager.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || s.trim().isEmpty()) {
            throw new RuntimeException("Username is mandatory!");
        }

        return userRepository.findByUsername(s);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(long id, User newUser) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        oldUser.update(newUser);
        return userRepository.save(oldUser);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
