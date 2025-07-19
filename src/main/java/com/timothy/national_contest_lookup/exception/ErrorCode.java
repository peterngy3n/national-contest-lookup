package com.timothy.national_contest_lookup.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "Can not categorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    STUDENT_NOT_FOUND(1000, "Student not found", HttpStatus.NOT_FOUND),
    INVALID_KEY(1001, "Invalid key", HttpStatus.BAD_REQUEST)
    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
