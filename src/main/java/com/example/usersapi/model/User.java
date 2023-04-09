package com.example.usersapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name="_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username should not be blank")
    private String username;

    @Past
    @NotNull(message = "birthdate should not be null")
    private LocalDate birthdate;

    @NotBlank(message = "countryOfResidence should not be blank")
    private String countryOfResidence;

    private String phoneNumber;

    private String gender;

    public User() {
    }

    /**
     * Constructor for the User class with parameters.
     *
     * @param username           the username of the user
     * @param birthdate          the birthdate of the user
     * @param countryOfResidence the country of residence of the user
     * @param phoneNumber       the phone number of the user
     * @param gender             the gender of the user
     */
    public User(String username, LocalDate birthdate, String countryOfResidence, String phoneNumber, String gender) {
        this.username = username;
        this.birthdate = birthdate;
        this.countryOfResidence = countryOfResidence;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    /**
     * Checks if the user is an adult based on their birthdate.
     *
     * @return true if the user is an adult, false otherwise
     */
    @JsonIgnore
    public boolean isAdult() {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(this.birthdate, currentDate).getYears();
        return age >= 18;
    }

    /**
     * Checks if the user's country of residence is France.
     *
     * @return true if the user's country of residence is France, false otherwise
     */
    @JsonIgnore
    public boolean isFrench() {
        return this.countryOfResidence != null && this.countryOfResidence.equalsIgnoreCase("France");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
