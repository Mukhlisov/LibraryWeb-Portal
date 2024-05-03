package com.github.mukhlisov;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ImageProvideController {

    @Value("${filesource-dir}")
    private String source;

    @SneakyThrows
    @GetMapping("/covers/{filename:.+}")
    public ResponseEntity<byte[]> serveFile(@PathVariable String filename) {
        UrlResource resource = new UrlResource(Paths.get(source, filename).toUri());
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES).cachePublic().getHeaderValue());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        try (InputStream inputStream = resource.getInputStream()) {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        byte[] bytes = outputStream.toByteArray();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).headers(headers).body(bytes);
    }
    
}
