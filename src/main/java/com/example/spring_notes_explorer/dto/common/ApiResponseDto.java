package com.example.spring_notes_explorer.dto.common;

import lombok.Builder;

@Builder
public record ApiResponseDto<T>(
        Integer status,
        String message,
        T data
) {
}
