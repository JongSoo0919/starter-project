package com.example.starterproject.dto.user.response;

public record UserSignupResponse(
        Long id
) {
    @Override
    public Long id() {
        return id;
    }
}
