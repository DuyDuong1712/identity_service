package com.devteria.identity_service.exception;

import com.devteria.identity_service.enums.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a resource conflict occurs, such as when attempting to create
 * a resource that already exists.
 */
public class ResourceConflictException extends ApplicationException{
    public ResourceConflictException(ErrorCode errorCode) {
        super(errorCode, HttpStatus.CONFLICT);
    }
}
