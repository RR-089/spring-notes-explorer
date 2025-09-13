package com.example.spring_notes_explorer.dto.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateNoteRequest(
        @NotBlank
        String title,
        
        String content,

        @NotNull
        Long folderId
) {
}
