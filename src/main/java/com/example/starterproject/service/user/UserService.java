package com.example.starterproject.service.user;

import com.example.starterproject.dto.user.request.UserSignupRequestDto;
import com.example.starterproject.dto.user.response.UserSignupResponseDto;
import com.example.starterproject.entity.user.Users;

public interface UserService {
    Users signup(UserSignupRequestDto userSignupRequestDto);
}
