package webportal.libweb.controllers;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import webportal.libweb.LibManagementSys.FileUploader.StorageProperties;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@AllArgsConstructor
public class ImageRestController {

    private final StorageProperties storageProperties;

    @SneakyThrows
    @GetMapping("/covers/{filename:.+}")
    public ResponseEntity<byte[]> serveFile(@PathVariable String filename) {
        UrlResource resource = new UrlResource(Paths.get(storageProperties.getDir(), filename).toUri());
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic().getHeaderValue());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        try (InputStream inputStream = resource.getInputStream()) {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // Получение массива байтов из ByteArrayOutputStream
        byte[] bytes = outputStream.toByteArray();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).headers(headers).body(bytes);
    }
    
}
