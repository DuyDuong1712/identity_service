package com.devteria.identity_service.exception;

import com.devteria.identity_service.dto.response.ApiResponse;
import com.devteria.identity_service.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
 import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
//        ApiResponse apiResponse = new ApiResponse<>();
//        apiResponse.setCode(1001);
//        apiResponse.setMessage(e.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//
//    @ExceptionHandler(value = ApplicationException.class)
//    ResponseEntity<ApiResponse> handleApplicationException(ApplicationException e) {
//        ApiResponse apiResponse = new ApiResponse<>();
//        ErrorCode errorCode = e.getErrorCode();
//
//        apiResponse.setCode(errorCode.getCode());
//        apiResponse.setMessage(errorCode.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    ResponseEntity<String> handleValidationException(MethodArgumentNotValidException e) {
////        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//        String errorMessage = e.getFieldError().getDefaultMessage();
//        return ResponseEntity.badRequest().body(errorMessage);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex) {
//    //        ApiResponse apiResponse = new ApiResponse<>();
//    //        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
//    //        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//    //        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//
//        ApiResponse apiResponse = ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<Object>> handleApplicationException(ApplicationException ex) {
        ApiResponse<Object> response = ApiResponse.error(ex.getErrorCode());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    /**
     * Xử lý lỗi validation @Valid hoặc @Validated
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();

            try {
                // Try to parse as ErrorCode
                ErrorCode errorCode = ErrorCode.valueOf(defaultMessage);
                errors.put(field, errorCode.getMessage());
            } catch (IllegalArgumentException ex) {
                // Use default validation message
                errors.put(field, defaultMessage);
            }
        }

        ApiResponse<Object> response = ApiResponse.error(ErrorCode.INVALID_INPUT);
        response.setErrorDetails(errors);

        return ResponseEntity.badRequest().body(response);
    }


    /**
     * Xử lý các lỗi chung khác
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleUnknownException(Exception e) {
        ApiResponse<Object> response = ApiResponse.error(ErrorCode.UNCATEGORIZED_EXCEPTION);
        return ResponseEntity.internalServerError().body(response);
    }

}
