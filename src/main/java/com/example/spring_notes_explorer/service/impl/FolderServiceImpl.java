package com.example.spring_notes_explorer.service.impl;

import com.example.spring_notes_explorer.dto.folder.CreateFolderRequest;
import com.example.spring_notes_explorer.dto.folder.FolderDto;
import com.example.spring_notes_explorer.model.Folder;
import com.example.spring_notes_explorer.repository.FolderRepository;
import com.example.spring_notes_explorer.service.FolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FolderServiceImpl implements FolderService {
    private final FolderRepository folderRepository;

    public static FolderDto mapToDto(Folder folder) {
        return FolderDto.builder()
                        .id(folder.getId())
                        .name(folder.getName())
                        .subFolders(folder.getSubFolders().stream().map(FolderServiceImpl::mapToDto).toList())
                        .notes(folder.getNotes().stream().map(NoteServiceImpl::mapToDto).toList())
                        .build();
    }

    @Override
    public FolderDto createFolder(CreateFolderRequest request) {
        return null;
    }

    @Override
    public FolderDto getFolder(Long id) {
        return null;
    }

    @Override
    public void deleteFolder(Long id) {

    }
}
