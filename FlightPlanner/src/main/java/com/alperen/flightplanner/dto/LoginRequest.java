package com.alperen.flightplanner.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "Username cannot be null!")
    private String username;

    @NotNull(message = "Password cannot be null!")
    private String password;

}