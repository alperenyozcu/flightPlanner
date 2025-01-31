package com.alperen.flightplanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username cannot be null!")
    @NotBlank(message = "Username cannot be blank!")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters!")
    private String username;

    @NotNull(message = "Password cannot be null!")
    @NotBlank(message = "Password cannot be blank!")
    @Size(min = 8, message = "Password must be at least 8 characters!")
    private String password;

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @Size(max = 100, message = "Name must not exceed 100 characters!")
    private String name;

    @NotNull(message = "Surname cannot be null!")
    @NotBlank(message = "Surname cannot be blank!")
    @Size(max = 100, message = "Surname must not exceed 100 characters!")
    private String surname;

    @NotNull(message = "City cannot be null!")
    @NotBlank(message = "City cannot be blank!")
    @Size(max = 100, message = "City must not exceed 100 characters!")
    private String city;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

}
