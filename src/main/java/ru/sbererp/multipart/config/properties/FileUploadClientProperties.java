package ru.sbererp.multipart.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file-upload")
public record FileUploadClientProperties(String baseUrl) {
}