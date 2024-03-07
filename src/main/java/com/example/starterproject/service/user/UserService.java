package com.example.starterproject.service.user;

import com.example.starterproject.dto.user.request.UserSignupRequestDto;

public interface UserService {
    Long signup(UserSignupRequestDto userSignupRequestDto);
}
