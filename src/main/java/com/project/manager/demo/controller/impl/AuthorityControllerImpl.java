package com.project.manager.demo.controller.impl;

import com.project.manager.demo.controller.AuthorityController;
import com.project.manager.demo.model.Authority;
import com.project.manager.demo.service.AuthorityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityControllerImpl implements AuthorityController {
    private final AuthorityService authorityService;

    public AuthorityControllerImpl(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @Override
    @GetMapping("/all")
    public List<Authority> getAllAuthorities() {
        return authorityService.getAllAuthorities();
    }
}
