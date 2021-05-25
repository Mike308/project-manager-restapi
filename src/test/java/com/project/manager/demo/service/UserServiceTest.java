package com.project.manager.demo.service;

import com.project.manager.demo.model.User;
import com.project.manager.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository = mock(UserRepository.class);

    static User mockedUser;

    @BeforeAll
    static void setup() {
        mockedUser = prepareMockedUser();
    }

    @Test
    void shouldTestUserServiceWhenCorrectUsernameIsSet() {
        // given
        String userName = "adminLocal";
        when(userRepository.findByUsername(eq("adminLocal"))).thenReturn(mockedUser);
        UserService userService = new UserService(userRepository);

        // when
        UserDetails userDetails = userService.loadUserByUsername(userName);

        // then
        assertEquals("adminLocal", userDetails.getUsername());
        assertEquals("T0ps3cred11!!", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertFalse(userDetails.isEnabled());
        // verify checks 'mocked' objects only so:
        verify(userRepository, times(1)).findByUsername(eq("adminLocal"));
    }

    @Test
    void shouldTestUserServiceWhenUserNameIsNull() {
        // given
        when(userRepository.findByUsername(any())).thenReturn(mockedUser);
        UserService userService = new UserService(userRepository);

        // when
        assertThrows(RuntimeException.class, () -> userService.loadUserByUsername(null));

        // then
        // verify checks 'mocked' objects only so:
        verify(userRepository, never()).findByUsername(any());
    }

    @Test
    void shouldTestUserServiceWhenUserNameIsEmpty() {
        // given
        when(userRepository.findByUsername(eq("adminLocal"))).thenReturn(mockedUser);
        UserService userService = new UserService(userRepository);

        // when
        assertThrows(RuntimeException.class, () -> userService.loadUserByUsername(""));

        // then
        // verify checks 'mocked' objects only so:
        verify(userRepository, never()).findByUsername(any());
    }

    @Test
    void shouldTestUserServiceWhenUserNameIsBlank() {
        // given
        when(userRepository.findByUsername(eq("adminLocal"))).thenReturn(mockedUser);
        UserService userService = new UserService(userRepository);

        // when
        assertThrows(RuntimeException.class, () -> userService.loadUserByUsername("   "));

        // then
        // verify checks 'mocked' objects only so:
        verify(userRepository, never()).findByUsername(any());
    }

    private static User prepareMockedUser() {
        User adminUser = new User();
        adminUser.setUsername("adminLocal");
        adminUser.setPassword("T0ps3cred11!!");
        adminUser.setAccountNonExpired(true);
        adminUser.setCredentialsNonExpired(true);
        adminUser.setAccountNonLocked(true);
        adminUser.setEnabled(false);
        adminUser.setAuthorities(Collections.emptyList());
        return adminUser;
    }
}
