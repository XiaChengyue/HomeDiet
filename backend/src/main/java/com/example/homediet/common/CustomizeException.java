package com.example.homediet.common;

public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(IErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }
}
