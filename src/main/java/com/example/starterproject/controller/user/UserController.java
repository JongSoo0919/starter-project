package com.example.starterproject.controller.user;

import com.example.starterproject.dto.user.request.UserSignupRequest;
import com.example.starterproject.dto.user.response.UserSignupResponse;
import com.example.starterproject.exception.ParameterNotValidException;
import com.example.starterproject.exception.common.ErrorCode;
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
    public ResponseEntity<UserSignupResponse> signup(
            @RequestBody @Valid UserSignupRequest userSignupRequest,
            BindingResult bindingResult) {
        log.info("request signup {}",userSignupRequest);

        if(bindingResult.hasErrors()){
            throw new ParameterNotValidException(ErrorCode.PARAMETER_NOT_VALID);
        }

        return ResponseEntity.status(201)
                .body(new UserSignupResponse(userService.signup(userSignupRequest).getId()));
    }
}
