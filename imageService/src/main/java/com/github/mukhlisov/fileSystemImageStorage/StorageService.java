package com.github.mukhlisov.fileSystemImageStorage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void init();
    void upload(MultipartFile file, String uniqueFilename);
    void deleteByName(String fileName) throws IOException;
    byte[] getByName(String fileName) throws IOException;
}
