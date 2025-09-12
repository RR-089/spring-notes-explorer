package com.example.spring_notes_explorer.config;

import com.example.spring_notes_explorer.dto.common.ApiResponseDto;
import com.example.spring_notes_explorer.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleCustomException(
            HttpServletRequest request,
            CustomException ex
    ) {
        log.info("Custom error at: {}", request.getRequestURL());
        log.info("Instance error name: {}", ex.getClass().getSimpleName());

        ApiResponseDto<Object> response = ApiResponseDto.builder()
                                                        .status(ex.getStatus().value())
                                                        .message(ex.getMessage())
                                                        .data(ex.getData())
                                                        .build();

        return ResponseEntity.status(ex.getStatus()).body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Map<String, String>>> handleMethodArgumentNotValidException(
            HttpServletRequest request,
            MethodArgumentNotValidException ex
    ) {
        log.info("Method argument not valid error at: {}", request.getRequestURL());

        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, String> errorData = new HashMap<>();

        for (FieldError fieldError : ex.getFieldErrors()) {
            errorData.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ApiResponseDto<Map<String, String>> response = ApiResponseDto.<Map<String,
                                                                             String>>builder()
                                                                     .status(status.value())
                                                                     .message(("One or " +
                                                                             "more fields contain invalid values"))
                                                                     .data(errorData)
                                                                     .build();

        return ResponseEntity.status(status).body(response);
    }
}
