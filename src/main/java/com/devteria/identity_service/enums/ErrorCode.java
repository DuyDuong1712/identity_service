package com.devteria.identity_service.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // Common errors
    UNCATEGORIZED_EXCEPTION(2000, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(2001, "Invalid message key", HttpStatus.BAD_REQUEST),
    INVALID_INPUT(2002, "Invalid input data", HttpStatus.BAD_REQUEST),

    // User errors
    USER_EXISTS(3000, "User with given username already exists", HttpStatus.CONFLICT),
    USER_NAME_IN_USE(3001, "Username is already in use", HttpStatus.CONFLICT),
    USER_NAME_INVALID(3002, "Username must be between 5 and 40 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(3003, "Password must be between 8 and 40 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(3004, "User not found", HttpStatus.NOT_FOUND),
    EMAIL_EXISTS(3005, "Email already exists", HttpStatus.CONFLICT),
    EMAIL_INVALID(3006, "Invalid email format", HttpStatus.BAD_REQUEST),

    // Authentication errors
    UNAUTHENTICATED(4000, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(4001, "Unauthorized access", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(4002, "Invalid token", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(4004, "Token expired", HttpStatus.UNAUTHORIZED),

    // System errors
    INTERNAL_SERVER_ERROR(5000, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
