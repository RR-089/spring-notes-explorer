package com.example.spring_notes_explorer.service;

import com.example.spring_notes_explorer.dto.folder.CreateFolderRequest;
import com.example.spring_notes_explorer.dto.folder.FolderDto;

public interface FolderService {

    FolderDto createFolder(CreateFolderRequest request);

    FolderDto getFolder(Long id);

    void deleteFolder(Long id);
}
