package com.example.starterproject.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupResponseDto {
    private Long id;

    @Builder
    public UserSignupResponseDto(Long id) {
        this.id = id;
    }
}
