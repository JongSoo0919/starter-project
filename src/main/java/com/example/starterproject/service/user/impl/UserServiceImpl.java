package com.example.starterproject.service.user.impl;

import com.example.starterproject.dto.user.request.UserSignupRequestDto;
import com.example.starterproject.entity.user.Users;
import com.example.starterproject.repository.user.UserRepository;
import com.example.starterproject.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Users signup(UserSignupRequestDto userSignupRequestDto) {
        //TODO : 익셉션 처리
        if (userRepository.existsByEmail(userSignupRequestDto.getEmail())) {
            throw new RuntimeException("중복 회원가입 입니다.");
        }
        return userRepository.save(Users.builder()
                .email(userSignupRequestDto.getEmail())
                .name(userSignupRequestDto.getName())
                .password(bCryptPasswordEncoder.encode(userSignupRequestDto.getPassword()))
                .role("ROLE_USER")
                .build());
    }

}
