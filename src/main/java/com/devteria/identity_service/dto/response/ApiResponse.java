package com.devteria.identity_service.dto.response;

import com.devteria.identity_service.enums.ErrorCode;
import com.devteria.identity_service.enums.SuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    int code = 1000;
    String message;
    T result;
    String timestamp;
    Map<String, String> errorDetails; //Chi tiết lỗi (đặc biệt validation)

    public ApiResponse() {
        this.timestamp = Instant.now().toString();
    }

    // Factory methods
    public static <T> ApiResponse<T> success(SuccessCode successCode, T result) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(successCode.getCode());
        response.setMessage(successCode.getMessage());
        response.setResult(result);
        return response;
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        return response;
    }

    public static <T> ApiResponse<T> validationError(ErrorCode errorCode, Map<String, String> errorDetails) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        response.setErrorDetails(errorDetails);
        return response;
    }
}
