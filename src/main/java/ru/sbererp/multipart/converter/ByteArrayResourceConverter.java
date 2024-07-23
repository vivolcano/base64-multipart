package ru.sbererp.multipart.converter;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import ru.sbererp.multipart.dto.FileDto;

import java.util.Base64;

@Component
public class ByteArrayResourceConverter {

    public ByteArrayResource convertToResource(FileDto fileDto) {
        var decodedBytes = Base64.getDecoder().decode(fileDto.file());
        return new ByteArrayResource(decodedBytes) {
            @Override
            public String getFilename() {
                return fileDto.fileName();
            }
        };
    }
}