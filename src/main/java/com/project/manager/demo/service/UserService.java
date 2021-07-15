package com.project.manager.demo.service;

import com.project.manager.demo.model.User;
import com.project.manager.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            throw new RuntimeException("Wymagana jest nazwa użytkownika");
        }

        return userRepository.findByUsername(s);
    }

    public User addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User currentUser = userRepository.findByUsername(user.getUsername());
        if (currentUser == null)
            return userRepository.save(user);
        else throw new RuntimeException("Użytkownik już istnieje");
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

    public ResponseEntity<Object> changePassword(User user) {
        User currentUser = userRepository.findByUsername(user.getUsername());
        if (currentUser == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono użytkownika o takim loginie");
        currentUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(currentUser);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Poprawnie zmieniono hasło");
        return ResponseEntity.ok(response);
    }
}
