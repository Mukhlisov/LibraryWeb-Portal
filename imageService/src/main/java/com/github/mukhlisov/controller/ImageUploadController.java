package com.github.mukhlisov.controller;

import com.github.mukhlisov.fileSystemImageStorage.StorageException;
import com.github.mukhlisov.fileSystemImageStorage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@Slf4j
@RequestMapping("/upload")
@RequiredArgsConstructor
public class ImageUploadController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam(value = "file") MultipartFile file,
                                              @RequestParam(value = "fileName") String fileName) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try{
            storageService.upload(file, fileName);
            return ResponseEntity.ok().body("File uploaded successfully");
        } catch (StorageException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
