package com.example.usersapi.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the User class.
 */
public class UserTest {

    /**
     * Test for isAdult() method when the user is an adult.
     */
    @Test
    void isAdultTest() {
        User user = new User("test", LocalDate.of(1990, 1, 1), "France", null, null);
        assertThat(user.isAdult()).isTrue();
    }

    /**
     * Test for isAdult() method when the user is not an adult.
     */
    @Test
    void isNotAdultTest() {
        User user = new User("test", LocalDate.of(2010, 1, 1), "France", null, null);
        assertThat(user.isAdult()).isFalse();
    }

    /**
     * Test for isFrench() method when the user is from France.
     */
    @Test
    void isFrenchTest() {
        User user = new User("test", LocalDate.of(1990, 1, 1), "france", null, null);
        assertThat(user.isAdult()).isTrue();
    }
}
