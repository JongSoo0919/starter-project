package com.example.starterproject.service.user;

import com.example.starterproject.dto.user.request.UserSignupRequest;
import com.example.starterproject.entity.user.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users signup(UserSignupRequest userSignupRequest);
}
