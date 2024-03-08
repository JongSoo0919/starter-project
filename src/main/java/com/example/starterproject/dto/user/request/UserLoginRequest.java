package com.example.starterproject.dto.user.request;

public record UserLoginRequest(
        String email
        ,String password
) {
    @Override
    public String password() {
        return password;
    }

    @Override
    public String email() {
        return email;
    }
}
