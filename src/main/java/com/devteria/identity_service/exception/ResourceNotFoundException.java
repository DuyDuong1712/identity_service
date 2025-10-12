package com.devteria.identity_service.exception;

import com.devteria.identity_service.enums.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a requested resource is not found.
 * This typically indicates that the resource does not exist or is inaccessible.
 */
public class ResourceNotFoundException extends ApplicationException{
    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode, HttpStatus.NOT_FOUND);
    }
}
