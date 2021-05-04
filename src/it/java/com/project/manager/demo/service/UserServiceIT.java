package com.project.manager.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceIT {

    @Autowired
    private UserService userService;

    /***
     * This one just for testing purpose.
     * Here we rely on real 'repository'.
     *
     * e.g.: PostgreSQL is required to be setup.
     */
    @Test
    void shouldLoadAdmin() {
        // given
        String userName = "admin";

        // when
        UserDetails userDetails = userService.loadUserByUsername(userName);

        // then
        assertEquals("admin", userDetails.getUsername());
        assertEquals("admin1234", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }
}
