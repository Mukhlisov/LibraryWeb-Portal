package com.github.mukhlisov;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class TelegramNotificationApp {

    public static void main(String[] args) {
        SpringApplication.run(TelegramNotificationApp.class, args);
    }
}