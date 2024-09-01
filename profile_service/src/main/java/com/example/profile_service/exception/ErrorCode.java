package com.example.profile_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNAUTHENTICATED(1001, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1002, "you do not have permission", HttpStatus.FORBIDDEN),
    USER_EXISTED(1003, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1004, "User not exist", HttpStatus.NOT_FOUND),
    PROFILE_EXISTED(1005, "Profile existed", HttpStatus.BAD_REQUEST),
    PROFILE_NOT_EXIST(1006, "Profile not exit", HttpStatus.NOT_FOUND),
            ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
