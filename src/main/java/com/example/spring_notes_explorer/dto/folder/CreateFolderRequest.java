package com.example.spring_notes_explorer.dto.folder;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateFolderRequest(
        @NotBlank
        String name,
        Long parentId
) {
}
