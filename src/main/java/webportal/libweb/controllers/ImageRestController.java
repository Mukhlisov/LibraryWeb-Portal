package webportal.libweb.controllers;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import webportal.libweb.FileUploader.StorageProperties;

import java.nio.file.Paths;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@AllArgsConstructor
public class ImageRestController {

    private final StorageProperties storageProperties;

    @SneakyThrows
    @GetMapping("/covers/{filename:.+}")
    public ResponseEntity<UrlResource> serveFile(@PathVariable String filename) {
        UrlResource resource = new UrlResource(Paths.get(storageProperties.getDir(), filename).toUri());
        return ResponseEntity.ok().body(resource);
    }
    
}
