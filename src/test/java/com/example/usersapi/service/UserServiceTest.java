package com.example.usersapi.service;

import com.example.usersapi.exception.DuplicateUsernameException;
import com.example.usersapi.exception.InvalidUserException;
import com.example.usersapi.model.User;
import com.example.usersapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for UserService class.
 */
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test case for registering a user with a duplicate username.
     */
    @Test
    void testRegisterUserWithDuplicateUsername() {
        // Create a user with a duplicate username
        User user = new User("test1", LocalDate.of(1990, 1, 1), "France", null, null);

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
        assertThrows(DuplicateUsernameException.class, () -> {
            userService.registerUser(user);
        });
        verify(userRepository, never()).save(user);
    }

    /**
     * Test case for registering an invalid user (not an adult).
     */
    @Test
    void testRegisterUserWithInvalidUser() {
        // Not an adult user
        User user = new User("test2", LocalDate.of(2010, 1, 1), "France", null, null);

        assertThrows(InvalidUserException.class, () -> {
            userService.registerUser(user);
        });
        verify(userRepository, never()).save(user);
    }

    /**
     * Test case for registering a valid user.
     */
    @Test
    void testRegisterUserWithValidUser() {
        // Create a user who is an adult and French
        User user = new User("test3", LocalDate.of(1990, 1, 1), "France", null, null);

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        User registeredUser = userService.registerUser(user);
        assertEquals(user, registeredUser);

        verify(userRepository, times(1)).existsByUsername(user.getUsername());
        verify(userRepository, times(1)).save(user);

    }

    /**
     * Test case for retrieving a user by ID with an existing user.
     */
    @Test
    void testGetUserByIdWithExistingUser() {
        User user = new User("test4", LocalDate.of(1990, 1, 1), "France", null, null);
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User retrievedUser = userService.getUserById(user.getId());
        assertEquals(user, retrievedUser);
    }
}
