package com.example.starterproject.service.user;

import com.example.starterproject.dto.user.request.UserSignupRequest;
import com.example.starterproject.entity.user.Users;

public interface UserService {
    Users signup(UserSignupRequest userSignupRequest);
}
