package com.github.mukhlisov.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.time.Duration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic telegramNotificationsTopic() {
        return TopicBuilder
                .name("telegram-notifications")
                .partitions(1)
                .replicas(1)
                .config(
                        TopicConfig
                                .RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(3).toMillis())
                )
                .build();
    }

    @Bean
    public NewTopic emailNotificationsTopic() {
        return TopicBuilder
                .name("email-notifications")
                .partitions(1)
                .replicas(1)
                .config(
                        TopicConfig
                                .RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(3).toMillis())
                )
                .build();
    }
}
