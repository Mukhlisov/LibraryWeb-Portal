package com.github.mukhlisov.controller;

import com.github.mukhlisov.fileSystemImageStorage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageProvideController {

    private final StorageService storageService;

    @GetMapping("/get/{filename:.+}")
    public ResponseEntity<?> serveImage(@PathVariable(required = false) String filename) {
        return createResponseEntity(filename);
    }

    @GetMapping("/get/")
    public ResponseEntity<?> serveVoidImage(){
       return createResponseEntity("");
    }

    private ResponseEntity<?> createResponseEntity(String fileName){
        try {
            byte[] bytes = storageService.getByName(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Failed to read file or something else");
        }
    }
}
