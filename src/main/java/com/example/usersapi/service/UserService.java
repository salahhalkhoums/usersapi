package com.example.usersapi.service;

import com.example.usersapi.exception.DuplicateUsernameException;
import com.example.usersapi.exception.InvalidUserException;
import com.example.usersapi.exception.ResourceNotFoundException;
import com.example.usersapi.model.User;
import com.example.usersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The UserService class handles business logic related to User entities.
 * It provides methods for registering a new user and retrieving user details by ID.
 * This class is responsible for processing business rules and interacting with the UserRepository
 * to perform the requested actions.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructor for UserService class.
     *
     * @param userRepository The UserRepository instance to be injected.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Register a new user.
     *
     * @param user The User object to be registered.
     * @return The registered User object.
     * @throws DuplicateUsernameException If the username already exists.
     * @throws InvalidUserException If the user does not meet the registration criteria.
     */
    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateUsernameException("Username already exists!");
        }

        if (!user.isAdult() || !user.isFrench()) {
            throw new InvalidUserException("Only French adults are allowed to register!");
        }

        return userRepository.save(user);
    }

    /**
     * Retrieve user details by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The retrieved User object.
     * @throws ResourceNotFoundException If the user is not found.
     */
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }
}
