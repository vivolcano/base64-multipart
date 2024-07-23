package ru.sbererp.multipart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.sbererp.multipart.client.FileUploadClient;
import ru.sbererp.multipart.converter.ByteArrayResourceConverter;
import ru.sbererp.multipart.dto.FileDto;
import ru.sbererp.multipart.dto.FileUploadResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private static final String FILES = "files";
    private static final String METADATA = "metadata";

    private final ByteArrayResourceConverter byteArrayResourceConverter;
    private final FileUploadClient fileUploadClient;

    public List<FileUploadResponse> uploadFiles(List<FileDto> files) {
        MultiValueMap<String, Object> multipartData = new LinkedMultiValueMap<>();
        files.forEach(fileDto -> {
            ByteArrayResource byteArrayResource = byteArrayResourceConverter.convertToResource(fileDto);
            multipartData.add(FILES, byteArrayResource);
        });
        multipartData.add(METADATA, files);
        return fileUploadClient.sendFiles(multipartData);
    }
}