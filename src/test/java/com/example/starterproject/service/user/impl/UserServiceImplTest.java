package com.example.starterproject.service.user.impl;

import com.example.starterproject.dto.user.request.UserSignupRequest;
import com.example.starterproject.dto.user.request.UserSignupRequestDto;
import com.example.starterproject.dto.user.response.UserSignupResponseDto;
import com.example.starterproject.entity.user.Users;
import com.example.starterproject.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImplTest(@Autowired UserService userService, @Autowired BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Test
    @DisplayName("회원가입을 진행한다.")
    void 회원가입() {
        //given
        UserSignupRequest userSignupRequest = new UserSignupRequest(
                "1234@gmail.com"
                ,"1234"
                ,"이름"
        );
        //when
        String email = userService.signup(userSignupRequest).getEmail();

        //then
        Assertions.assertThat(userSignupRequest.email()).isEqualTo(email);
    }

    @Test
    @DisplayName("패스워드 암호화가 되었는지 확인한다.")
    void 패스워드_인코딩() {
        //given
        UserSignupRequest userSignupRequest = new UserSignupRequest(
                "123@gmail.com"
                ,"1234"
                ,"이름"
        );

        //then
        Users user = userService.signup(userSignupRequest);

        //then
        Assertions.assertThat(true)
                .isEqualTo(bCryptPasswordEncoder.matches(userSignupRequest.password(), user.getPassword()));
    }
}