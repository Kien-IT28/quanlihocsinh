package com.thpt.quanlyhocsinh.exception;

public class AppException extends RuntimeException {

    private final ErrorCode errorCode;

    // Constructor chỉ với ErrorCode
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    // Constructor với ErrorCode và nguyên nhân gốc
    public AppException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
