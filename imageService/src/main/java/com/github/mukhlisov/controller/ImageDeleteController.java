package com.github.mukhlisov.controller;

import com.github.mukhlisov.fileSystemImageStorage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/delete")
@RequiredArgsConstructor
public class ImageDeleteController {

    private final StorageService storageService;

    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> deleteImage(@PathVariable(name = "fileName") String fileName) {
        if (fileName.isEmpty()) {
            return ResponseEntity.badRequest().body("File name cannot be empty");
        }

        try{
            storageService.deleteByName(fileName);
            return ResponseEntity.ok().body("File deleted successfully");
        }catch (IOException e){
            return ResponseEntity.internalServerError().body("An error occurred while deleting image");
        }
    }
}
