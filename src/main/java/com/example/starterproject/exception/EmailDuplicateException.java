package com.example.starterproject.exception;

import com.example.starterproject.exception.common.ErrorCode;
import lombok.Getter;

@Getter
public class EmailDuplicateException extends RuntimeException{
    private final ErrorCode errorCode;

    public EmailDuplicateException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
