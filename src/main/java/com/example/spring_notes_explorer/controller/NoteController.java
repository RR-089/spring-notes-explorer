package com.example.spring_notes_explorer.controller;

import com.example.spring_notes_explorer.dto.common.ApiResponseDto;
import com.example.spring_notes_explorer.dto.note.CreateNoteRequest;
import com.example.spring_notes_explorer.dto.note.NoteDto;
import com.example.spring_notes_explorer.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<NoteDto>> createNote(
            @RequestBody CreateNoteRequest request
    ) {
        NoteDto data = noteService.createNote(request);
        HttpStatus status = HttpStatus.CREATED;

        ApiResponseDto<NoteDto> response = ApiResponseDto.<NoteDto>builder()
                                                         .status(status.value())
                                                         .message("Create note successful")
                                                         .data(data)
                                                         .build();

        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(value = "/{noteId}")
    public ResponseEntity<ApiResponseDto<Object>> deleteNote(
            @PathVariable("noteId") Long noteId
    ) {
        noteService.deleteNote(noteId);

        ApiResponseDto<Object> response = ApiResponseDto.builder()
                                                        .status(HttpStatus.OK.value())
                                                        .message("Delete note successful")
                                                        .data(null)
                                                        .build();

        return ResponseEntity.ok(response);
    }

}
