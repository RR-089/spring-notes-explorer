package com.example.spring_notes_explorer.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final Object data;

    public CustomException(HttpStatus status, String message, Object data) {
        super(message);
        this.status = status;
        this.data = data;
    }


    public static class BadRequestException extends CustomException {
        public BadRequestException(String message, Object data) {
            super(HttpStatus.BAD_REQUEST, message, data);
        }
    }

    public static class NotFoundException extends CustomException {
        public NotFoundException(String message, Object data) {
            super(HttpStatus.NOT_FOUND, message, data);
        }
    }
}
