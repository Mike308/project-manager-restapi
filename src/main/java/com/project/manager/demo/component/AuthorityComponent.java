package com.project.manager.demo.component;

import com.project.manager.demo.model.Authority;
import com.project.manager.demo.repository.AuthorityRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AuthorityComponent {
    private final AuthorityRepository authorityRepository;

    public AuthorityComponent(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @PostConstruct
    private void insertNewAuthorities() {
        System.out.println("Inserting data");
        authorityRepository.save(new Authority("ADMIN"));
        authorityRepository.save(new Authority("STUDENT"));
    }
}
