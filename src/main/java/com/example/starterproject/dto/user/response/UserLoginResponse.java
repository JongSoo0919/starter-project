package com.example.starterproject.dto.user.response;

public record UserLoginResponse(
        String accessToken
) {
    @Override
    public String accessToken() {
        return accessToken;
    }
}
