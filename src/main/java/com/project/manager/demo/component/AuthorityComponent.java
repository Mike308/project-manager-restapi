package com.project.manager.demo.component;

import com.project.manager.demo.model.Authority;
import com.project.manager.demo.repository.AuthorityRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AuthorityComponent {
    private final AuthorityRepository authorityRepository;

    public AuthorityComponent(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @PostConstruct
    private void insertNewAuthorities() {
        List<Authority> authorities = authorityRepository.findAll();
        if (!authorities.parallelStream().anyMatch(v -> v.getAuthority().equals("ROLE_ADMIN")))
            authorityRepository.save(new Authority("ROLE_ADMIN"));
        if (!authorities.parallelStream().anyMatch(v -> v.getAuthority().equals("ROLE_STUDENT")))
            authorityRepository.save(new Authority("ROLE_STUDENT"));
    }
}
