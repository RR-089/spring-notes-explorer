package com.example.spring_notes_explorer.service.impl;

import com.example.spring_notes_explorer.dto.folder.CreateFolderRequest;
import com.example.spring_notes_explorer.dto.folder.FolderDto;
import com.example.spring_notes_explorer.exception.CustomException.NotFoundException;
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
        log.info("Create new folder: {}", request.name());

        Folder parentFolder = request.parentId() != null ?
                this.folderRepository.findById(request.parentId()).orElseThrow(() -> new NotFoundException("Parent folder not found", null)) :
                null;

        Folder newFolder = Folder.builder()
                                 .name(request.name())
                                 .parent(parentFolder)
                                 .build();

        return mapToDto(this.folderRepository.save(newFolder));
    }

    @Override
    public FolderDto getFolder(Long id) {
        log.info("Get folder id: {}", id);
        return mapToDto(this.folderRepository.findById(id).orElseThrow(() -> new NotFoundException("Folder not found", null)));
    }

    @Override
    public void deleteFolder(Long id) {
        Folder foundFolder =
                this.folderRepository.findById(id).orElseThrow(() -> new NotFoundException("Folder not found", null));

        this.folderRepository.delete(foundFolder);
    }
}
