package com.example.usersapi.controller;

import com.example.usersapi.aspect.LogProcessingTime;
import com.example.usersapi.exception.DuplicateUsernameException;
import com.example.usersapi.exception.InvalidUserException;
import com.example.usersapi.exception.ResourceNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The UserController class handles HTTP requests related to User resources.
 * It provides endpoints for registering a new user and retrieving user details by ID.
 * This class is responsible for processing incoming requests and invoking the appropriate methods
 * in the UserService to perform the requested actions.
 *
 * @see UserService
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController class.
     *
     * @param userService The UserService instance to be injected.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST endpoint for registering a new user.
     *
     * @param user The User object to be registered.
     * @return ResponseEntity with the registered User object and HTTP status code 201 (CREATED).
     * @throws DuplicateUsernameException If the username already exists.
     * @throws InvalidUserException If the user is not a French adult.
     * @see User
     * @see UserService#registerUser(User)
     */
    @PostMapping
    @LogProcessingTime
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    /**
     * GET endpoint for retrieving user details by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return ResponseEntity with the retrieved User object and HTTP status code 200 (OK).
     * @throws ResourceNotFoundException If the user is not found.
     * @see User
     * @see UserService#getUserById(Long)
     */
    @GetMapping("/{userId}")
    @LogProcessingTime
    public ResponseEntity<User> getUserById(@Valid @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
