package com.example.usersapi.controller;

import com.example.usersapi.exception.ApiExceptionHandler;
import com.example.usersapi.model.User;
import com.example.usersapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for the UserController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        User user1 = new User("user1", LocalDate.of(1990, 1, 1), "France", null, null);
        userController.registerUser(user1);

        objectMapper.registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    /**
     * Test for successful registration of a user with valid input.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    public void testRegisterUser_Success() throws Exception {
        User user = new User("salah", LocalDate.of(1990, 1, 1), "France", null, null);
        String requestJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());
    }

    /**
     * Test for registration of a user with invalid input, expecting a bad request response.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    public void testRegisterUser_InvalidInput() throws Exception {
        User user = new User("", LocalDate.of(2005, 1, 1), "USA", null, null);
        String requestJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    /**
     * Test for retrieving a user by ID.
     *
     * @throws Exception if an exception occurs during the test
     */
    @Test
    public void testGetUserById() throws Exception {
        User user = new User("user1", LocalDate.of(1990, 1, 1), "France", null, null);
        given(userService.getUserById(eq(1L))).willReturn(user);
        mockMvc.perform(get("/api/users/{userId}", 1L))
                .andExpect(status().isOk());
    }
}
