package com.example.starterproject.exception;

import com.example.starterproject.exception.common.ErrorCode;
import lombok.Getter;

@Getter
public class TokenNotValidException extends RuntimeException{
    private final ErrorCode errorCode;

    public TokenNotValidException(ErrorCode errorCode) {
//        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
