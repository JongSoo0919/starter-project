package com.example.starterproject.exception.common;

import com.example.starterproject.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<ErrorResponse> emailDuplicateExceptionHandler(EmailDuplicateException e) {
        log.error("Email Duplicated Error", e);
        return ResponseEntity.status(ErrorCode.EMAIL_DUPLICATION.getStatus())
                .body(new ErrorResponse(ErrorCode.EMAIL_DUPLICATION));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(UserNotFoundException e) {
        log.error("User Not Found Error", e);
        return ResponseEntity.status(ErrorCode.USER_NOT_FOUND.getStatus())
                .body(new ErrorResponse(ErrorCode.USER_NOT_FOUND));
    }

    @ExceptionHandler(ParameterNotValidException.class)
    public ResponseEntity<ErrorResponse> parameterNotValidExceptionHandler(ParameterNotValidException e) {
        log.error("Parameter Not Valid Error", e);
        return ResponseEntity.status(ErrorCode.PARAMETER_NOT_VALID.getStatus())
                .body(new ErrorResponse(ErrorCode.PARAMETER_NOT_VALID));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        log.error("Exception", e);
        return ResponseEntity.status(ErrorCode.INTER_SERVER_ERROR.getStatus())
                .body(new ErrorResponse(ErrorCode.INTER_SERVER_ERROR));
    }

}
