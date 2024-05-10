package com.github.mukhlisov;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public ResponseEntity<byte[]> serveFile(@PathVariable String filename) throws FileNotFoundException {
        UrlResource resource = new UrlResource(Paths.get(source, filename).toUri());
        /*HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.maxAge(5, TimeUnit.MINUTES).cachePublic().getHeaderValue());*/

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        try (InputStream inputStream = resource.getInputStream()) {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        byte[] bytes = outputStream.toByteArray();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFound(FileNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<h1>Файл не найден</h1><h3>"+exc.getLocalizedMessage()+"</h3>");
    }
}
