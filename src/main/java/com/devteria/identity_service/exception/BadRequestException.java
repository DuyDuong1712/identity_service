package com.devteria.identity_service.exception;

import com.devteria.identity_service.enums.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a bad request is made by the client.
 * This typically indicates that the request could not be understood
 * or was missing required parameters.
 */

public class BadRequestException extends ApplicationException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode, HttpStatus.BAD_REQUEST);
    }
}
