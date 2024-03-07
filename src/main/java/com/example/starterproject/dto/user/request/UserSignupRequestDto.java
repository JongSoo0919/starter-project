package com.example.starterproject.dto.user.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {
    @NotNull(message = "email is not Empty")
    @Email(message = "please email format")
    private String email;

    @NotNull(message = "password is not Empty")
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")
    private String password;

    @NotNull(message = "name is not Empty")
    @Size(min = 2)
    private String name;

    @Override
    public String toString() {
        return "UserSignupRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
