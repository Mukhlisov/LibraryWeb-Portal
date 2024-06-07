package com.github.mukhlisov.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Service
@Slf4j
@RequiredArgsConstructor
public class ImageManagementService {

    private final WebClient webClient;

    public void logFailedToReadFile(String message){
        log.error("Failed to read file: {}", message);
    }

    public void doPost(MultipartFile file, String fileName) throws IOException {
        MultiValueMap<String, Object> multiPartData = new LinkedMultiValueMap<>();
        multiPartData.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return fileName;
            }
        });
        multiPartData.add("fileName", fileName);

        Mono<ResponseEntity<String>> response = webClient.post()
                .uri("/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(multiPartData))
                .retrieve()
                .toEntity(String.class);

        response.subscribe(
                responseEntity -> {
                    HttpStatusCode status = responseEntity.getStatusCode();
                    log.info("{}: {}", status.value(), responseEntity.getBody());
                },
                error -> {
                    if (error instanceof WebClientResponseException responseException) {
                        HttpStatusCode statusCode = responseException.getStatusCode();
                        log.error("Error: Image wasn't sent with code: {}", statusCode.value());
                    } else {
                        log.error("An unexpected error: {}", error.getMessage());
                    }
                }
        );
    }

    public void doDelete(String fileName){
        Mono<ResponseEntity<String>> response = webClient.delete()
                .uri("/delete/{fileName}", fileName)
                .retrieve()
                .toEntity(String.class);

        response.subscribe(
                responseEntity -> {
                    HttpStatusCode status = responseEntity.getStatusCode();
                    log.info("{}: {}", status.value(), responseEntity.getBody());
                },
                error -> {
                    if (error instanceof WebClientResponseException responseException) {
                        HttpStatusCode statusCode = responseException.getStatusCode();
                        log.error("Error: Image wasn't deleted with code: {}", statusCode.value());
                    } else {
                        log.error("An unexpected error: {}", error.getMessage());
                    }
                }
        );
    }

    public void sendImage(MultipartFile file, String fileName) {
        try {
            doPost(file, fileName);
        } catch (IOException e) {
            logFailedToReadFile(e.getMessage());
        }
    }

    public void updateImage (MultipartFile file, String newFileName, String oldFileName) {
        if (!newFileName.equals(oldFileName)) {
            doDelete(oldFileName);
        }
        try {
            doPost(file, newFileName);
        } catch (IOException e) {
            logFailedToReadFile(e.getMessage());
        }
    }

    public void deleteFile(String fileName) {
        doDelete(fileName);
    }
}
