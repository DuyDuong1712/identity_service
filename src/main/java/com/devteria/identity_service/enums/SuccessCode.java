package com.devteria.identity_service.enums;

public enum SuccessCode {
    SUCCESS(1000, "Success"),
    CREATED(1001, "Created successfully"),
    UPDATED(1002, "Updated successfully"),
    DELETED(1003, "Deleted successfully"),
    RETRIEVED(1004, "Retrieved successfully"),
    PROCESSED(1005, "Processed successfully");

    private int code;
    private String message;

    SuccessCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
