package com.example.starterproject.service.user.impl;

import com.example.starterproject.dto.user.UserLoginDetails;
import com.example.starterproject.dto.user.request.UserSignupRequest;
import com.example.starterproject.entity.user.Users;
import com.example.starterproject.exception.EmailDuplicateException;
import com.example.starterproject.exception.UserNotFoundException;
import com.example.starterproject.exception.common.ErrorCode;
import com.example.starterproject.repository.user.UserRepository;
import com.example.starterproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Users signup(UserSignupRequest userSignupRequest) {
        if (userRepository.existsByEmail(userSignupRequest.email())) {
            throw new EmailDuplicateException(ErrorCode.EMAIL_DUPLICATION);
        }

        return userRepository.save(Users.builder()
                .email(userSignupRequest.email())
                .name(userSignupRequest.name())
                .password(bCryptPasswordEncoder.encode(userSignupRequest.password()))
                .role("user")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserLoginDetails(userRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)));
    }
}
