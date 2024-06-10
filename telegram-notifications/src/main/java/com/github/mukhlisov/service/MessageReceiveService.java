package com.github.mukhlisov.service;

import com.github.mukhlisov.model.TelegramNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageReceiveService {

    private final TelegramNotificationBot telegramNotificationBot;

    @KafkaListener(topics = "telegram-notifications", groupId = "telegram-group-1")
    public void listenMessage(TelegramNotification notification){
        telegramNotificationBot.sendMessage(notification.getChatId(), notification.getText());
    }
}
