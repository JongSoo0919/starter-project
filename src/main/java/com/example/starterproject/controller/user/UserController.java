package com.example.starterproject.controller.user;

import com.example.starterproject.dto.user.request.UserSignupRequestDto;
import com.example.starterproject.dto.user.response.UserSignupResponseDto;
import com.example.starterproject.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDto> signup(
            @RequestBody @Valid UserSignupRequestDto userSignupRequestDto,
            BindingResult bindingResult) {
        log.info("request signup {}",userSignupRequestDto);

        //TODO : 익셉션 처리
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.status(201).body(UserSignupResponseDto.builder()
                .id(userService.signup(userSignupRequestDto).getId())
                .build());
    }
}
