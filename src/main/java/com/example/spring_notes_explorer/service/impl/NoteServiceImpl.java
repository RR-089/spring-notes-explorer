package com.example.spring_notes_explorer.service.impl;

import com.example.spring_notes_explorer.dto.note.CreateNoteRequest;
import com.example.spring_notes_explorer.dto.note.NoteDto;
import com.example.spring_notes_explorer.model.Note;
import com.example.spring_notes_explorer.repository.FolderRepository;
import com.example.spring_notes_explorer.repository.NoteRepository;
import com.example.spring_notes_explorer.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NoteServiceImpl implements NoteService {
    private final FolderRepository folderRepository;
    private final NoteRepository noteRepository;

    public static NoteDto mapToDto(Note note) {
        return NoteDto.builder()
                      .id(note.getId())
                      .title(note.getTitle())
                      .content(note.getContent())
                      .build();
    }

    @Override
    public NoteDto createNote(CreateNoteRequest request) {
        return null;
    }

    @Override
    public void deleteNote(Long id) {

    }
}
