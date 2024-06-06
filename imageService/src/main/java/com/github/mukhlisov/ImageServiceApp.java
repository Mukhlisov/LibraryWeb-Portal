package com.github.mukhlisov;

import com.github.mukhlisov.fileSystemImageStorage.StorageProperties;
import com.github.mukhlisov.fileSystemImageStorage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class ImageServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ImageServiceApp.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}