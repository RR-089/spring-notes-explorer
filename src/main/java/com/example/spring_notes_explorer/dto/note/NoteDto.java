package com.example.spring_notes_explorer.dto.note;

import lombok.Builder;

@Builder
public record NoteDto(
        Long id,
        String title,
        String content
) {
}
