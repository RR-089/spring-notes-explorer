package com.example.spring_notes_explorer.service.impl;

import com.example.spring_notes_explorer.dto.note.CreateNoteRequest;
import com.example.spring_notes_explorer.dto.note.NoteDto;
import com.example.spring_notes_explorer.exception.CustomException.NotFoundException;
import com.example.spring_notes_explorer.model.Folder;
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
        Folder folder =
                this.folderRepository.findById(request.folderId()).orElseThrow(() -> new NotFoundException("Folder not found", null));

        Note newNote = Note.builder()
                           .title(request.title())
                           .content(request.content())
                           .folder(folder)
                           .build();

        return mapToDto(this.noteRepository.save(newNote));
    }

    @Override
    public void deleteNote(Long id) {
        Note foundNote =
                this.noteRepository.findById(id).orElseThrow(() -> new NotFoundException("Note not found", null));

        this.noteRepository.delete(foundNote);
    }
}
