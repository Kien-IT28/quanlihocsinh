package com.thpt.quanlyhocsinh.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi chưa phân loại! (Uncategorized error).", HttpStatus.INTERNAL_SERVER_ERROR), // Tuongw duong error 500
    INVALID_KEY(1001, "Key massage không hợp lệ! (Invalid massage key).", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Người dùng đã tồn tại! (User existed).", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "Người dùng không tồn tại! (User not existed).",HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1004, "Tên học sinh phải có ít nhất 6 ký tự! (Student name must be at least 6 character).", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1005, "Mật khẩu phải có ít nhất 8 ký tự! (Password must be at least 8 character).", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Authenticated!.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1009, "You do not have permission",HttpStatus.FORBIDDEN),
    USER_NOT_FOUND(1007, "User not found!"),
    ACCESS_DENIED(1008, "Khong co quyen truy cap"),
    ROLE_NOT_FOUND(1010, "Vai tro khong ton tai."),
    PERMISSION_NOT_FOUND(1011, "Quyen khong ton tai."),
    EMAIL_EXISTED(1012,"Email da ton tai", HttpStatus.NOT_FOUND),
    EMAIL_INVALID(1013, "Email khong hop le")
    ;
    // Constructor

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    final int code;
    final String message;
    HttpStatusCode statusCode;
}
