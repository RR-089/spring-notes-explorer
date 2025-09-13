package com.example.spring_notes_explorer.service;

import com.example.spring_notes_explorer.dto.note.CreateNoteRequest;
import com.example.spring_notes_explorer.dto.note.NoteDto;

public interface NoteService {
    NoteDto createNote(CreateNoteRequest request);

    void deleteNote(Long id);
}
