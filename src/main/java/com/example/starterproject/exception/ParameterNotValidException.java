package com.example.starterproject.exception;

import com.example.starterproject.exception.common.ErrorCode;
import lombok.Getter;

@Getter
public class ParameterNotValidException extends RuntimeException {
    private final ErrorCode errorCode;

    public ParameterNotValidException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
