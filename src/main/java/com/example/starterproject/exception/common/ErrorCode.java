package com.example.starterproject.exception.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404, "COMMON-ERR-404", "PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500, "COMMON-ERR-500", "INTER SERVER ERROR"),
    EMAIL_DUPLICATION(400, "USER-ERR-400", "EMAIL DUPLICATED"),
    USER_NOT_FOUND(400, "USER-ERR-400", "USER NOT FOUND"),
    PARAMETER_NOT_VALID(400, "USER-ERR-400", "PARAMETER NOT VALID"),
    TOKEN_NOT_FOUND(401, "USER-ERR-401", "TOKEN NOT FOUND"),
    TOKEN_NOT_VALID(401, "USER-ERR-401", "TOKEN NOT VALID")
    ;


    private final int status;
    private final String code;
    private final String message;
}
