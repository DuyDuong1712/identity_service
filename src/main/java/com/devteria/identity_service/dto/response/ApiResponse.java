package com.devteria.identity_service.dto.response;

import com.devteria.identity_service.enums.ErrorCode;
import com.devteria.identity_service.enums.SuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code = 1000;
    private String message;
    private T result;
    private String timestamp;
    private Map<String, String> errorDetails; //Chi tiết lỗi (đặc biệt validation)

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(Map<String, String> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
