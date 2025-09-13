package com.example.spring_notes_explorer.repository;

import com.example.spring_notes_explorer.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
