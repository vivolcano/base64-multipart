package ru.sbererp.multipart.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sbererp.multipart.config.properties.FileUploadClientProperties;
import ru.sbererp.multipart.dto.FileUploadResponse;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FileUploadClient {
    private final WebClient webClient;
    private final FileUploadClientProperties dataClientProperties;

    public List<FileUploadResponse> sendFiles(MultiValueMap<String, Object> multipartData) {
        return webClient.post()
                .uri(dataClientProperties.baseUrl())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(multipartData))
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<FileUploadResponse>>() {})
                .mapNotNull(HttpEntity::getBody)
                .block();
    }
}