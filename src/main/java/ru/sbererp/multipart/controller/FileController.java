package ru.sbererp.multipart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbererp.multipart.dto.FileDto;
import ru.sbererp.multipart.dto.FileUploadResponse;
import ru.sbererp.multipart.service.FileService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/create")
    public ResponseEntity<List<FileUploadResponse>> create(@RequestBody List<FileDto> files) {
        var body = fileService.uploadFiles(files);
        return ResponseEntity.ok(body);
    }
}