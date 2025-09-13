package com.example.spring_notes_explorer.repository;

import com.example.spring_notes_explorer.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
