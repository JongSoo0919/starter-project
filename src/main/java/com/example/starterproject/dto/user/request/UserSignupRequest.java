package com.example.starterproject.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSignupRequest(
        @NotNull(message = "email is not Empty")
        @Email(message = "please email format")
        String email
        ,
        @NotNull(message = "password is not Empty")
        @Size(min = 8)
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")
        String password

        ,
        @NotNull(message = "name is not Empty")
        @Size(min = 2)
        String name
) {
    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String name() {
        return name;
    }
}
