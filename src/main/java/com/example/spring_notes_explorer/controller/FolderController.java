package com.example.spring_notes_explorer.controller;

import com.example.spring_notes_explorer.dto.common.ApiResponseDto;
import com.example.spring_notes_explorer.dto.folder.CreateFolderRequest;
import com.example.spring_notes_explorer.dto.folder.FolderDto;
import com.example.spring_notes_explorer.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folders")
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<FolderDto>> createFolder(
            @RequestBody CreateFolderRequest request
    ) {

        FolderDto data = folderService.createFolder(request);
        HttpStatus status = HttpStatus.CREATED;

        ApiResponseDto<FolderDto> response = ApiResponseDto.<FolderDto>builder()
                                                           .status(status.value())
                                                           .message("Create folder successful")
                                                           .data(data)
                                                           .build();

        return ResponseEntity.status(status).body(response);
    }

    @GetMapping(value = "/{folderId}")
    public ResponseEntity<ApiResponseDto<FolderDto>> getFolder(
            @PathVariable("folderId") Long folderId
    ) {
        FolderDto data = folderService.getFolder(folderId);

        ApiResponseDto<FolderDto> response = ApiResponseDto.<FolderDto>builder()
                                                           .status(HttpStatus.OK.value())
                                                           .message("Get folder successful")
                                                           .data(data)
                                                           .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{folderId}")
    public ResponseEntity<ApiResponseDto<Object>> deleteFolder(
            @PathVariable("folderId") Long folderId
    ) {
        folderService.deleteFolder(folderId);

        ApiResponseDto<Object> response = ApiResponseDto.builder()
                                                        .status(HttpStatus.OK.value())
                                                        .message("Delete folder " +
                                                                "successful")
                                                        .data(null)
                                                        .build();
        return ResponseEntity.ok(response);
    }


}
