package com.example.spring_notes_explorer.dto.folder;

import com.example.spring_notes_explorer.dto.note.NoteDto;
import lombok.Builder;

import java.util.List;

@Builder
public record FolderDto(
        Long id,
        String name,
        List<FolderDto> subFolders,
        List<NoteDto> notes
) {
}
